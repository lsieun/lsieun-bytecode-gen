package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * RET - Return from subroutine
 *
 * <PRE>Stack: ... -&gt; ...</PRE>
 */
public final class RET extends Instruction {

    public boolean wide;
    private int index; // index to local variable containg the return address

    public RET(final int index) {
        super(OpcodeConst.RET, 2);
        this.index = index;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        if(wide) {
            out.writeShort(index);
        }
        else {
            out.writeByte(index);
        }
    }
}
