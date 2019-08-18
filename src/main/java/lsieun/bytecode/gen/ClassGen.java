package lsieun.bytecode.gen;

import java.util.ArrayList;
import java.util.List;

import lsieun.bytecode.core.cst.JDKConst;
import lsieun.bytecode.core.cst.JVMConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.attrs.SourceFile;
import lsieun.bytecode.gen.clazz.ConstantPool;
import lsieun.bytecode.gen.clazz.FieldInfo;
import lsieun.bytecode.gen.clazz.JavaClass;
import lsieun.bytecode.gen.clazz.MethodInfo;
import lsieun.bytecode.gen.opcode.ALOAD_0;
import lsieun.bytecode.gen.opcode.INVOKESPECIAL;
import lsieun.bytecode.gen.opcode.RETURN;
import lsieun.bytecode.gen.utils.InstructionList;


public class ClassGen {
    /* Corresponds to the fields found in a JavaClass object.
     */
    private String class_name;
    private String super_class_name;
    private String file_name;
    private int access_flags;
    private int class_name_index = -1;
    private int superclass_name_index = -1;
    private int major = JDKConst.JAVA_8;
    private int minor = 0;

    // ArrayLists instead of arrays to gather fields, methods, etc.
    private final List<String> interface_vec = new ArrayList();
    private final List<FieldInfo> field_vec = new ArrayList();
    private final List<MethodInfo> method_vec = new ArrayList();
    private final List<AttributeInfo> attribute_vec = new ArrayList();

    // Auxiliary Tool
    public ConstantPoolGen cpg; // Template for building up constant pool

    public ClassGen(final String class_name, final String super_class_name, final String file_name,
                    final int access_flags, final String[] interfaces,
                    final ConstantPoolGen cpg) {
        this.access_flags = access_flags;
        this.class_name = class_name;
        this.super_class_name = super_class_name;
        this.file_name = file_name;
        this.cpg = cpg;

        // Put everything needed by default into the constant pool and the vectors
        if (file_name != null) {
            addAttribute(new SourceFile(cpg.addUtf8("SourceFile"), 2, cpg.addUtf8(file_name)));
        }


        class_name_index = cpg.addClass(class_name);
        superclass_name_index = cpg.addClass(super_class_name);
        if (interfaces != null) {
            for (final String interface_index : interfaces) {
                addInterface(interface_index);
            }
        }
    }

    // region 第一批方法： getXXXGen，为第二批方法做准备

    public MethodGen getMethodGen(final int access_flags, final Type return_type, final Type[] arg_types, String[] arg_names,
                                  final String method_name, final InstructionList il) {
        MethodGen instance = new MethodGen(access_flags, return_type, arg_types, arg_names, method_name, class_name, il, cpg);
        return instance;
    }

    // endregion

    // region 第二批方法：addXXX，为第三批方法做准备
    public void addInterface(final String name) {
        interface_vec.add(name);
    }

    public void addMethod(final MethodInfo m) {
        method_vec.add(m);
    }

    /**
     * Convenience method.
     * <p>
     * Add an empty constructor to this class that does nothing but calling super().
     *
     * @param access_flags rights for constructor
     */
    public void addEmptyConstructor(final int access_flags) {
        final InstructionList il = new InstructionList();
        il.append(new ALOAD_0()); // Push `this'
        il.append(new INVOKESPECIAL(cpg.addMethodref(super_class_name, JVMConst.CONSTRUCTOR_NAME, "()V")));
        il.append(new RETURN());
        final MethodGen mg = new MethodGen(access_flags, TypeUtils.VOID, TypeUtils.NO_ARGS, null, "<init>",
                class_name, il, cpg);

        addMethod(mg.getMethod());
    }

    public void addAttribute(final AttributeInfo attr) {
        attribute_vec.add(attr);
    }

    // endregion

    // region 第三批方法：getXXX，为第四批方法做准备
    public int[] getInterfaces() {
        final int size = interface_vec.size();
        final int[] interfaces = new int[size];
        for (int i = 0; i < size; i++) {
            interfaces[i] = cpg.addClass(interface_vec.get(i));
        }
        return interfaces;
    }

    public FieldInfo[] getFields() {
        return field_vec.toArray(new FieldInfo[field_vec.size()]);
    }

    public MethodInfo[] getMethods() {
        return method_vec.toArray(new MethodInfo[method_vec.size()]);
    }

    public AttributeInfo[] getAttributes() {
        return attribute_vec.toArray(new AttributeInfo[attribute_vec.size()]);
    }

    // endregion

    // region 第四批方法：getJavaClass，可能进行输出了
    /**
     * @return the (finally) built up Java class object.
     */
    public JavaClass getJavaClass() {
        final int[] interfaces = getInterfaces();
        final FieldInfo[] fields = getFields();
        final MethodInfo[] methods = getMethods();
        AttributeInfo[] attributes = getAttributes();

        // Must be last since the above calls may still add something to it
        final ConstantPool cp = this.cpg.getFinalConstantPool();
        return new JavaClass(class_name_index, superclass_name_index, file_name, major, minor,
                access_flags, cp, interfaces, fields, methods, attributes);
    }
    // endregion
}
