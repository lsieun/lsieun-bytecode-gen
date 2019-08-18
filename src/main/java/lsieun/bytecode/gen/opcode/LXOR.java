package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * LXOR - Bitwise XOR long
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public final class LXOR extends Instruction {

    public LXOR() {
        super(OpcodeConst.LXOR, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
