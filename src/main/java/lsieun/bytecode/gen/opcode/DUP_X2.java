package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * DUP_X2 - Duplicate top operand stack word and put three down
 * <PRE>Stack: ..., word3, word2, word1 -&gt; ..., word1, word3, word2, word1</PRE>
 */
public final class DUP_X2 extends Instruction {

    public DUP_X2() {
        super(OpcodeConst.DUP_X2, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
