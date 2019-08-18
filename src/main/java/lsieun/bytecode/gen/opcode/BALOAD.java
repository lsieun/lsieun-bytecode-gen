package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * BALOAD - Load byte or boolean from array
 * <PRE>Stack: ..., arrayref, index -&gt; ..., value</PRE>
 */
public final class BALOAD extends Instruction {

    public BALOAD() {
        super(OpcodeConst.BALOAD, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
