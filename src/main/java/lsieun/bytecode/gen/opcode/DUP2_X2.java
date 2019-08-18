package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * DUP2_X2 - Duplicate two top operand stack words and put four down
 * <PRE>Stack: ..., word4, word3, word2, word1 -&gt; ..., word2, word1, word4, word3, word2, word1</PRE>
 */
public final class DUP2_X2 extends Instruction {

    public DUP2_X2() {
        super(OpcodeConst.DUP2_X2, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
