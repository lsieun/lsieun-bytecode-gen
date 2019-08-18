package lsieun.bytecode.gen.clazz;

import lsieun.bytecode.gen.attrs.AttributeInfo;

public class FieldInfo {
    // method_info
    public int access_flags;
    public int name_index;
    public int signature_index;
    public int attributes_count;
    public AttributeInfo[] attributes;

    public FieldInfo(final int access_flags,
                      final int name_index, final int signature_index,
                      final AttributeInfo[] attributes) {
        this.access_flags = access_flags;
        this.name_index = name_index;
        this.signature_index = signature_index;
        this.attributes = attributes;
        this.attributes_count = attributes != null ? attributes.length : 0; // init deprecated field
    }
}
