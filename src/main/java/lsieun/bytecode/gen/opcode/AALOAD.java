package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

public final class AALOAD extends Instruction {

    public AALOAD() {
        super(OpcodeConst.AALOAD, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
