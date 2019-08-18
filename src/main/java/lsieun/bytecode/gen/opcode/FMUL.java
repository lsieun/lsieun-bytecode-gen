package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * FMUL - Multiply floats
 * <PRE>Stack: ..., value1, value2 -&gt; result</PRE>
 */
public final class FMUL extends Instruction {

    public FMUL() {
        super(OpcodeConst.FMUL, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
