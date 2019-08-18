package lsieun.bytecode.gen.cp;

import java.nio.charset.StandardCharsets;

import lsieun.bytecode.core.cst.CPConst;

public class ConstantUtf8 extends Constant {
    public final int length;
    public final String utf8Value;

    /**
     * @param str Data
     */
    public ConstantUtf8(final String str) {
        super(CPConst.CONSTANT_Utf8);
        if (str == null) {
            throw new IllegalArgumentException("str must not be null!");
        }
        this.utf8Value = str;
        this.length = str.getBytes(StandardCharsets.UTF_8).length;
    }
}
