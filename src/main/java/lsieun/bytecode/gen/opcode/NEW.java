package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * NEW - Create new object
 * <PRE>Stack: ... -&gt; ..., objectref</PRE>
 */
public final class NEW extends Instruction {

    private int index;

    public NEW(final int index) {
        super(OpcodeConst.NEW, 3);
        this.index = index;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeShort(index);
    }
}
