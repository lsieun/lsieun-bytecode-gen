package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * FSUB - Substract floats
 * <PRE>Stack: ..., value1, value2 -&gt; result</PRE>
 */
public final class FSUB extends Instruction {

    public FSUB() {
        super(OpcodeConst.FSUB, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
