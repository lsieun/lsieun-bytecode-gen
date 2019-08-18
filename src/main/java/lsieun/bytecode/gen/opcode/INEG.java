package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * INEG - Negate int
 * <PRE>Stack: ..., value -&gt; ..., result</PRE>
 */
public final class INEG extends Instruction {

    public INEG() {
        super(OpcodeConst.INEG, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
