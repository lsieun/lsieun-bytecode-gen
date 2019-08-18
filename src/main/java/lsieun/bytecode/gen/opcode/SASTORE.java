package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * SASTORE - Store into short array
 * <PRE>Stack: ..., arrayref, index, value -&gt; ...</PRE>
 */
public final class SASTORE extends Instruction {

    public SASTORE() {
        super(OpcodeConst.SASTORE, 1);
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
    }
}
