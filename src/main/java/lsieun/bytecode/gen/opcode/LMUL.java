package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * LMUL - Multiply longs
 * <PRE>Stack: ..., value1.word1, value1.word2, value2.word1, value2.word2 -&gt;</PRE>
 * ..., result.word1, result.word2
 */
public final class LMUL extends Instruction {

    public LMUL() {
        super(OpcodeConst.LMUL, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
