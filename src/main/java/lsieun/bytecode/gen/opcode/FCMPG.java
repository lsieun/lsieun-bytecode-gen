package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * FCMPG - Compare floats: value1 &gt; value2
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public final class FCMPG extends Instruction {

    public FCMPG() {
        super(OpcodeConst.FCMPG, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
