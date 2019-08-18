package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * FCMPL - Compare floats: value1 &lt; value2
 * <PRE>Stack: ..., value1, value2 -&gt; ..., result</PRE>
 */
public final class FCMPL extends Instruction {

    public FCMPL() {
        super(OpcodeConst.FCMPL, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
