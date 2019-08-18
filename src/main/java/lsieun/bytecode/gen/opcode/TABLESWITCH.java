package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.SelectInstruction;

/**
 * TABLESWITCH - Switch within given range of values, i.e., low..high
 */
public final class TABLESWITCH extends SelectInstruction {

    public int low;
    public int high;

    public TABLESWITCH() {
        super(OpcodeConst.TABLESWITCH, 1);
        // FIXME: TABLESWITCH
        throw new RuntimeException("TABLESWITCH");
    }

    public void calculateLength() {
        int padding = (4 - (position % 4)) % 4; // Compute number of pad bytes
        int match_length = high - low + 1;
        int fixed_length = (13 + match_length * 4);
        length = fixed_length + padding;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        throw new RuntimeException("");
    }
}
