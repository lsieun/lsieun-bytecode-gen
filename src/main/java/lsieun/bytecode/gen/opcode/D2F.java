package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * D2F - Convert double to float
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ..., result</PRE>
 */
public final class D2F extends Instruction {

    public D2F() {
        super(OpcodeConst.D2F, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
