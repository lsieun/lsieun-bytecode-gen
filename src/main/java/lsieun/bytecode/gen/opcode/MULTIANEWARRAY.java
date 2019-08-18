package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.ConstantPoolGen;

/**
 * MULTIANEWARRAY - Create new mutidimensional array of references
 * <PRE>Stack: ..., count1, [count2, ...] -&gt; ..., arrayref</PRE>
 */
public final class MULTIANEWARRAY extends Instruction {
    private int index;
    private int dimensions;

    public MULTIANEWARRAY(final int index, final int dimensions) {
        super(OpcodeConst.MULTIANEWARRAY, 4);

        this.index = index;
        this.dimensions = dimensions;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeShort(index);
        out.writeByte(dimensions);
    }

    @Override
    public int consumeStack(final ConstantPoolGen cpg) {
        return dimensions;
    }
}
