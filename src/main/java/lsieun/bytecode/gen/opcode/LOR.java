package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * LOR - Bitwise OR long
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public final class LOR extends Instruction {

    public LOR() {
        super(OpcodeConst.LOR, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
