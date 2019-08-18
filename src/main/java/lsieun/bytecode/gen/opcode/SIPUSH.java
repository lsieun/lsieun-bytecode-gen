package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * SIPUSH - Push short
 *
 * <PRE>Stack: ... -&gt; ..., value</PRE>
 */
public final class SIPUSH extends Instruction {

    public short value;

    public SIPUSH(final short value) {
        super(OpcodeConst.SIPUSH, 3);
        this.value = value;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeShort(value);
    }
}
