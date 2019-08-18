package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.opcode.facet.FieldInstruction;

/**
 * PUTSTATIC - Put static field in class
 * <PRE>Stack: ..., value -&gt; ...</PRE>
 * OR
 * <PRE>Stack: ..., value.word1, value.word2 -&gt; ...</PRE>
 */
public final class PUTSTATIC extends FieldInstruction {

    public PUTSTATIC(final int index) {
        super(OpcodeConst.PUTSTATIC, 3);
        this.index = index;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeShort(index);
    }

    @Override
    public int consumeStack(final ConstantPoolGen cpg) {
        return getFieldSize(cpg);
    }
}
