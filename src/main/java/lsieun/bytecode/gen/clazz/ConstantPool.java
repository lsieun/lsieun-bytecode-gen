package lsieun.bytecode.gen.clazz;

import lsieun.bytecode.gen.cp.Constant;

public class ConstantPool {
    public int count;
    public Constant[] entries;

    /**
     * @param constants Array of constants
     */
    public ConstantPool(final Constant[] constants) {
        this.entries = constants;
        this.count = constants.length;
    }

}
