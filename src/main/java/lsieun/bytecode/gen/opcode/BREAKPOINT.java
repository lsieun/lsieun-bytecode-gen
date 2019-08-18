package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * BREAKPOINT, JVM dependent, ignored by default
 */
public final class BREAKPOINT extends Instruction {

    public BREAKPOINT() {
        super(OpcodeConst.BREAKPOINT, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        throw new RuntimeException("out.writeByte(opcode);");
    }
}
