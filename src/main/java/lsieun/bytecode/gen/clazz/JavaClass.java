package lsieun.bytecode.gen.clazz;

import lsieun.bytecode.gen.attrs.AttributeInfo;

public class JavaClass {
    public int major;
    public int minor; // Compiler version
    public ConstantPool constant_pool; // Constant pool
    public int access_flags;
    public int class_name_index;
    public int superclass_name_index;
    public int[] interfaces; // implemented interfaces
    public FieldInfo[] fields; // Fields, i.e., variables of class
    public MethodInfo[] methods; // methods defined in the class
    public AttributeInfo[] attributes; // attributes defined in the class

    public JavaClass(final int class_name_index, final int superclass_name_index, final String file_name, final int major,
                     final int minor, final int access_flags, final ConstantPool constant_pool, int[] interfaces,
                     FieldInfo[] fields, MethodInfo[] methods, AttributeInfo[] attributes) {

        if (interfaces == null) {
            interfaces = new int[0];
        }
        if (attributes == null) {
            attributes = new AttributeInfo[0];
        }
        if (fields == null) {
            fields = new FieldInfo[0];
        }
        if (methods == null) {
            methods = new MethodInfo[0];
        }

        this.major = major;
        this.minor = minor;
        this.constant_pool = constant_pool;
        this.access_flags = access_flags;
        this.class_name_index = class_name_index;
        this.superclass_name_index = superclass_name_index;
        this.interfaces = interfaces;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
    }
}
