package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.LocalVariableInstruction;

public final class FLOAD_2 extends LocalVariableInstruction {

    public FLOAD_2() {
        super(OpcodeConst.FLOAD_2, 1);
        this.index = 2;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
