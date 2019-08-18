package lsieun.bytecode.gen.cp;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantFloat extends Constant {
    public final Float floatValue;

    /**
     * @param floatValue Data
     */
    public ConstantFloat(final float floatValue) {
        super(CPConst.CONSTANT_Float);
        this.floatValue = floatValue;
    }
}
