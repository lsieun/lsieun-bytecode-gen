package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.GotoInstruction;

/**
 * GOTO - Branch always (to relative offset, not absolute address)
 */
public final class GOTO extends GotoInstruction {

    public GOTO() {
        super(OpcodeConst.GOTO, 3);
    }

    public GOTO(final int branch) {
        super(OpcodeConst.GOTO, 3);
        this.branch = branch;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeShort(branch);
    }
}
