package lsieun.bytecode.gen.cp;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantInteger extends Constant {
    public final Integer intValue;

    /**
     * @param num Data
     */
    public ConstantInteger(final int num) {
        super(CPConst.CONSTANT_Integer);
        this.intValue = num;
    }
}
