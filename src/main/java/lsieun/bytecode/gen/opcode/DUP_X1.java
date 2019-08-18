package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * DUP_X1 - Duplicate top operand stack word and put two down
 * <PRE>Stack: ..., word2, word1 -&gt; ..., word1, word2, word1</PRE>
 */
public final class DUP_X1 extends Instruction {

    public DUP_X1() {
        super(OpcodeConst.DUP_X1, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
