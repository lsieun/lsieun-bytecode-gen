package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.GotoInstruction;

/**
 * GOTO_W - Branch always (to relative offset, not absolute address)
 */
public final class GOTO_W extends GotoInstruction {

    public GOTO_W(final int branch) {
        super(OpcodeConst.GOTO_W, 5);
        this.branch = branch;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeInt(branch);
    }
}
