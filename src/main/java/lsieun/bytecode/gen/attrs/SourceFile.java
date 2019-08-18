package lsieun.bytecode.gen.attrs;


import lsieun.bytecode.core.cst.AttrConst;

public final class SourceFile extends AttributeInfo {
    public int sourcefile_index;

    public SourceFile(final int name_index, final int length, final int sourcefile_index) {
        super(AttrConst.ATTR_SOURCE_FILE, name_index, length, null);
        this.sourcefile_index = sourcefile_index;
    }
}
