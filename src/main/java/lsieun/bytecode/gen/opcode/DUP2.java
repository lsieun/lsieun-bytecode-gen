package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * DUP2 - Duplicate two top operand stack words
 * <PRE>Stack: ..., word2, word1 -&gt; ..., word2, word1, word2, word1</PRE>
 */
public final class DUP2 extends Instruction {

    public DUP2() {
        super(OpcodeConst.DUP2, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
