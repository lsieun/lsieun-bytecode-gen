package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.LocalVariableInstruction;

public final class ASTORE_0 extends LocalVariableInstruction {

    public ASTORE_0() {
        super(OpcodeConst.ASTORE_0, 1);
        this.index = 0;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
