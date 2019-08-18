package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * BIPUSH - Push byte on stack
 *
 * <PRE>Stack: ... -&gt; ..., value</PRE>
 */
public final class BIPUSH extends Instruction {
    private byte value;

    /**
     * Push byte on stack
     */
    public BIPUSH(final byte value) {
        super(OpcodeConst.BIPUSH, 2);
        this.value = value;
    }

    @Override
    public void dump(final DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeByte(value);
    }

}
