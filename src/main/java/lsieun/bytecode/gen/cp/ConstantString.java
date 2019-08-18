package lsieun.bytecode.gen.cp;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantString extends Constant {
    public final int string_index;

    /**
     * @param string_index Index of Constant_Utf8 in constant pool
     */
    public ConstantString(final int string_index) {
        super(CPConst.CONSTANT_String);
        this.string_index = string_index;
    }
}
