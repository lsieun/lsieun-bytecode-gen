package lsieun.bytecode.gen.cp;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantLong extends Constant {
    public final Long longValue;

    /**
     * @param longValue Data
     */
    public ConstantLong(final long longValue) {
        super(CPConst.CONSTANT_Long);
        this.longValue = longValue;
    }
}
