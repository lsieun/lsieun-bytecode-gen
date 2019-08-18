package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * JSR - Jump to subroutine
 */
public final class JSR extends Instruction {

    public int branch;

    public JSR(final int branch) {
        super(OpcodeConst.JSR, 3);
        this.branch = branch;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeShort(branch);
    }
}
