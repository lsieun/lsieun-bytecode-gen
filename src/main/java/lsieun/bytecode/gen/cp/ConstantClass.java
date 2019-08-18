package lsieun.bytecode.gen.cp;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantClass extends Constant {
    public final int name_index;

    /**
     * @param name_index Name index in constant pool.  Should refer to a
     * ConstantUtf8.
     */
    public ConstantClass(final int name_index) {
        super(CPConst.CONSTANT_Class);
        this.name_index = name_index;
    }
}
