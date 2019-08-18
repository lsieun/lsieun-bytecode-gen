package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * JSR_W - Jump to subroutine
 */
public final class JSR_W extends Instruction {

    public int branch;

    public JSR_W(final int branch) {
        super(OpcodeConst.JSR_W, 5);
        this.branch = branch;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeInt(branch);
    }
}
