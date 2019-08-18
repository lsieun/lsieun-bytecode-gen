package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.SelectInstruction;

/**
 * LOOKUPSWITCH - Switch with unordered set of values
 */
public final class LOOKUPSWITCH extends SelectInstruction {

    public int npairs;

    public LOOKUPSWITCH() {
        super(OpcodeConst.LOOKUPSWITCH, 9);
    }

    public LOOKUPSWITCH(final int[] match, final int[] targets, final int defaultTarget) {
        super(OpcodeConst.LOOKUPSWITCH, 9);
        // FIXME: LOOKUPSWITCH
        throw new RuntimeException("LOOKUPSWITCH");
    }

    public void calculateLength() {
        int padding = (4 - ((position+1) % 4)) % 4; // Compute number of pad bytes
        int fixed_length = 9 + npairs * 8;
        length = fixed_length + padding;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        int padding = (4 - ((position+1) % 4)) % 4;
        int match_length = this.npairs;
        int[] matches = this.matches;
        int[] offsets = this.offsets;

        for (int i = 0; i < padding; i++) {
            out.writeByte(0);
        }
        out.writeInt(branch);

        out.writeInt(match_length); // npairs
        for (int i = 0; i < match_length; i++) {
            out.writeInt(matches[i]); // match-offset pairs
            out.writeInt(offsets[i]);
        }
    }
}
