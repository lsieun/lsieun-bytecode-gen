package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * FNEG - Negate float
 * <PRE>Stack: ..., value -&gt; ..., result</PRE>
 */
public final class FNEG extends Instruction {

    public FNEG() {
        super(OpcodeConst.FNEG, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
