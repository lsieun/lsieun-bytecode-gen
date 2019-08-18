package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * I2D - Convert int to double
 * <PRE>Stack: ..., value -&gt; ..., result.word1, result.word2</PRE>
 */
public final class I2D extends Instruction {

    public I2D() {
        super(OpcodeConst.I2D, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
