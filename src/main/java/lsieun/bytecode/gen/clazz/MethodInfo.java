package lsieun.bytecode.gen.clazz;

import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.attrs.code.LineNumberTable;
import lsieun.bytecode.gen.attrs.code.LocalVariableTable;
import lsieun.bytecode.gen.attrs.method.Code;
import lsieun.bytecode.gen.attrs.method.ExceptionTable;

/**
 * This class represents the method info structure.
 * A method has access flags, a name, a signature and a number of attributes.
 */
public class MethodInfo {
    // method_info
    public int access_flags;
    public int name_index;
    public int signature_index;
    public int attributes_count;
    public AttributeInfo[] attributes;

    // Auxiliary Tools
    protected ConstantPool constant_pool;

    /**
     * @param access_flags    Access rights of method
     * @param name_index      Points to field name in constant pool
     * @param signature_index Points to encoded signature
     * @param attributes      Collection of attributes
     * @param constant_pool   Array of constants
     */
    public MethodInfo(final int access_flags,
                      final int name_index, final int signature_index,
                      final AttributeInfo[] attributes,
                      final ConstantPool constant_pool) {
        this.access_flags = access_flags;
        this.name_index = name_index;
        this.signature_index = signature_index;
        this.attributes = attributes;
        this.attributes_count = attributes != null ? attributes.length : 0; // init deprecated field
        // Auxiliary Tools
        this.constant_pool = constant_pool;
    }

    /**
     * @return Code attribute of method, if any
     */
    public final Code getCode() {
        for (final AttributeInfo attribute : this.attributes) {
            if (attribute instanceof Code) {
                return (Code) attribute;
            }
        }
        return null;
    }

    /**
     * @return ExceptionTable attribute of method, if any, i.e., list all
     * exceptions the method may throw not exception handlers!
     */
    public final ExceptionTable getExceptionTable() {
        for (final AttributeInfo attribute : this.attributes) {
            if (attribute instanceof ExceptionTable) {
                return (ExceptionTable) attribute;
            }
        }
        return null;
    }

    /** @return LocalVariableTable of code attribute if any, i.e. the call is forwarded
     * to the Code atribute.
     */
    public final LocalVariableTable getLocalVariableTable() {
        final Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLocalVariableTable();
    }


    /** @return LineNumberTable of code attribute if any, i.e. the call is forwarded
     * to the Code atribute.
     */
    public final LineNumberTable getLineNumberTable() {
        final Code code = getCode();
        if (code == null) {
            return null;
        }
        return code.getLineNumberTable();
    }
}
