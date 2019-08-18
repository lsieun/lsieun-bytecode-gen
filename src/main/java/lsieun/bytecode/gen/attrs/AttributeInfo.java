package lsieun.bytecode.gen.attrs;

import lsieun.bytecode.gen.clazz.ConstantPool;

public class AttributeInfo {
    public final byte tag;
    public final int attribute_name_index;
    public int attribute_length;
    // Auxiliary Tools
    public ConstantPool constant_pool;

    public AttributeInfo(final byte tag, int attribute_name_index, int attribute_length, final ConstantPool constant_pool) {
        this.tag = tag;
        this.attribute_name_index = attribute_name_index;
        this.attribute_length = attribute_length;
        this.constant_pool = constant_pool;
    }
}
