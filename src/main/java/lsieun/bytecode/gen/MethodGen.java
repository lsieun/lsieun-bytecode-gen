package lsieun.bytecode.gen;

import java.util.ArrayList;
import java.util.List;

import lsieun.bytecode.core.exceptions.ClassGenException;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.AccessFlagUtils;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.attrs.code.LocalVariableTypeTable;
import lsieun.bytecode.gen.attrs.method.Code;
import lsieun.bytecode.gen.attrs.method.Code.CodeException;
import lsieun.bytecode.gen.clazz.MethodInfo;
import lsieun.bytecode.gen.utils.CodeUtils;
import lsieun.bytecode.gen.utils.InstructionList;

/**
 * Template class for building up a method. This is done by defining exception
 * handlers, adding thrown exceptions, local variables and attributes, whereas
 * the `LocalVariableTable' and `LineNumberTable' attributes will be set
 * automatically for the code. Use stripAttributes() if you don't like this.
 * <p>
 * While generating code it may be necessary to insert NOP operations. You can
 * use the `removeNOPs' method to get rid off them.
 * The resulting method object can be obtained via the `getMethod()' method.
 */
public class MethodGen {
    //Class Info
    private String class_name;

    // Method Info
    private int access_flags;
    private String method_name;
    private Type return_type;
    private Type[] arg_types;
    private String[] arg_names;

    // Method Attribute Info
    private final List<String> throws_vec = new ArrayList();
    private final List<AttributeInfo> attribute_vec = new ArrayList(); // method attribute

    // Code Info
    private InstructionList il;
    private int max_locals;
    private int max_stack;

    // Code Attribute Info
    private final List<AttributeInfo> code_attrs_vec = new ArrayList(); // code attribute
    private final List<LocalVariableGen> variable_vec = new ArrayList<>();
    private LocalVariableTypeTable local_variable_type_table = null;

    // Auxiliary Tool
    private ConstantPoolGen cpg;

    /**
     * Declare method. If the method is non-static the constructor
     * automatically declares a local variable `$this' in slot 0. The
     * actual code is contained in the `il' parameter, which may further
     * manipulated by the user. But he must take care not to remove any
     * instruction (handles) that are still referenced from this object.
     * <p>
     * For example one may not add a local variable and later remove the
     * instructions it refers to without causing havoc. It is safe
     * however if you remove that local variable, too.
     *
     * @param access_flags access qualifiers
     * @param return_type  method type
     * @param arg_types    argument types
     * @param arg_names    argument names (if this is null, default names will be provided
     *                     for them)
     * @param method_name  name of method
     * @param class_name   class name containing this method (may be null, if you don't care)
     * @param il           instruction list associated with this method, may be null only for
     *                     abstract or native methods
     * @param cpg          constant pool
     */
    public MethodGen(final int access_flags, final Type return_type, final Type[] arg_types, String[] arg_names,
                     final String method_name, final String class_name, final InstructionList il, final ConstantPoolGen cpg) {
        //Class Info
        this.class_name = class_name;

        // Method Info
        this.method_name = method_name;
        this.access_flags = access_flags;
        this.return_type = return_type;
        this.arg_types = arg_types;
        this.arg_names = arg_names;

        // Code Info
        this.il = il;

        // Auxiliary Tool
        this.cpg = cpg;

        final boolean is_abstract = AccessFlagUtils.isAbstract(access_flags);
        if (!is_abstract) {
            //FIXME: 添加this
        }

        if (arg_types != null) {
            final int size = arg_types.length;
            for (final Type arg_type : arg_types) {
                if (TypeUtils.VOID == arg_type) {
                    throw new ClassGenException("'void' is an illegal argument type for a method");
                }
            }
            if (arg_names != null) { // Names for variables provided?
                if (size != arg_names.length) {
                    throw new ClassGenException("Mismatch in argument array lengths: " + size
                            + " vs. " + arg_names.length);
                }
            } else { // Give them dummy names
                arg_names = new String[size];
                for (int i = 0; i < size; i++) {
                    arg_names[i] = "arg" + i;
                }
                this.arg_names = arg_names;
            }
            if (!is_abstract) {
                for (int i = 0; i < size; i++) {
                    //FIXME: 添加local variable变量
                    //addLocalVariable(arg_names[i], arg_types[i], start, end);
                }
            }
        }
    }

    /**
     * Get method object. Never forget to call setMaxStack() or setMaxStack(max), respectively,
     * before calling this method (the same applies for max locals).
     *
     * @return method object
     */
    public MethodInfo getMethod() {
        final String signature = getSignature();
        final int name_index = cpg.addUtf8(this.method_name);
        final int signature_index = cpg.addUtf8(signature);

        /* Also updates positions of instructions, i.e., their indices
         */
        byte[] byte_code = null;
        if (il != null) {
            byte_code = il.getByteCode();
        }

        final AttributeInfo[] code_attrs = new AttributeInfo[0];
        /* Each attribute causes 6 additional header bytes
         */
        int attrs_len = 0;
        for (final AttributeInfo code_attr : code_attrs) {
            attrs_len += code_attr.attribute_length + 6;
        }

        final CodeException[] c_exc = new CodeException[0];
        int exc_len = c_exc.length * 8; // Every entry takes 8 bytes

        Code code = null;
        if ((il != null) && !AccessFlagUtils.isAbstract(access_flags) && !AccessFlagUtils.isNative(access_flags)) {
            // Remove any stale code attribute
            final AttributeInfo[] attributes = getAttributes();
            for (final AttributeInfo a : attributes) {
                if (a instanceof Code) {
                    removeAttribute(a);
                }
            }
            max_locals = CodeUtils.computeMaxLocals(access_flags, arg_types, il);
            max_stack = CodeUtils.computeMaxStack(cpg, il, new CodeExceptionGen[0]);
            code = new Code(cpg.addUtf8("Code"), 8 + byte_code.length + // prologue byte code
                    2 + exc_len + // exceptions
                    2 + attrs_len, // attributes
                    max_stack, max_locals, byte_code, c_exc, code_attrs, cpg.getConstantPool());
            addAttribute(code);
        }

        //
        final MethodInfo m = new MethodInfo(access_flags, name_index, signature_index, getAttributes(), cpg.getConstantPool());
        return m;
    }

    public String getSignature() {
        return TypeUtils.getMethodSignature(return_type, arg_types);
    }

    /**
     * @return all attributes of this method.
     */
    public AttributeInfo[] getAttributes() {
        final AttributeInfo[] attributes = new AttributeInfo[attribute_vec.size()];
        attribute_vec.toArray(attributes);
        return attributes;
    }

    /**
     * Add an attribute to this method. Currently, the JVM knows about
     * the `Code', `ConstantValue', `Synthetic' and `Exceptions'
     * attributes. Other attributes will be ignored by the JVM but do no
     * harm.
     *
     * @param attr attribute to be added
     */
    public void addAttribute(final AttributeInfo attr) {
        attribute_vec.add(attr);
    }

    /**
     * Remove an attribute.
     */
    public void removeAttribute(final AttributeInfo attr) {
        attribute_vec.remove(attr);
    }

}
