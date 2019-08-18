package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;

/**
 * NEWARRAY -  Create new array of basic type (int, short, ...)
 * <PRE>Stack: ..., count -&gt; ..., arrayref</PRE>
 * type must be one of T_INT, T_SHORT, ...
 */
public final class NEWARRAY extends Instruction {
    private byte atype; // array type

    public NEWARRAY(final byte atype) {
        super(OpcodeConst.NEWARRAY, 2);
        this.atype = atype;
    }

    @Override
    public void dump(DataOutputStream out) throws IOException {
        out.writeByte(opcode);
        out.writeByte(atype);
    }
}
