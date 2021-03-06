package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.opcode.facet.LocalVariableInstruction;

public final class LSTORE_1 extends LocalVariableInstruction {

    public LSTORE_1() {
        super(OpcodeConst.LSTORE_1, 1);
        this.index = 1;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
