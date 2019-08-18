package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * IMPDEP2 - Implementation dependent
 */
public final class IMPDEP2 extends Instruction {

    public IMPDEP2() {
        super(OpcodeConst.IMPDEP2, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        throw new RuntimeException("");
    }
}
