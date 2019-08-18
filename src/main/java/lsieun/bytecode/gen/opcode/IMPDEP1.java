package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * IMPDEP1 - Implementation dependent
 */
public final class IMPDEP1 extends Instruction {

    public IMPDEP1() {
        super(OpcodeConst.IMPDEP1, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        throw new RuntimeException("");
    }
}
