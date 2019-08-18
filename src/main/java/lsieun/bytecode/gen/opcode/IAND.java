package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * IAND - Bitwise AND int
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public final class IAND extends Instruction {

    public IAND() {
        super(OpcodeConst.IAND, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
