package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.LocalVariableInstruction;

public final class DSTORE_0 extends LocalVariableInstruction {

    public DSTORE_0() {
        super(OpcodeConst.DSTORE_0, 1);
        this.index = 0;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
