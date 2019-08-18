package lsieun.bytecode.gen.cp;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantDouble extends Constant {
    public final Double doubleValue;

    /**
     * @param doubleValue Data
     */
    public ConstantDouble(final double doubleValue) {
        super(CPConst.CONSTANT_Double);
        this.doubleValue = doubleValue;
    }
}
