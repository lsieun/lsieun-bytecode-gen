package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.opcode.Instruction;

/**
 * Abstract super class for instructions dealing with local variables.
 */
public abstract class LocalVariableInstruction extends Instruction implements TypedInstruction {
    public boolean wide;
    public int index;

    public LocalVariableInstruction(short opcode, int length) {
        super(opcode, length);
    }

    @Override
    public Type getType(ConstantPoolGen cpg) {
        switch (opcode) {
            case OpcodeConst.ILOAD:
            case OpcodeConst.ILOAD_0:
            case OpcodeConst.ILOAD_1:
            case OpcodeConst.ILOAD_2:
            case OpcodeConst.ILOAD_3:
            case OpcodeConst.ISTORE:
            case OpcodeConst.ISTORE_0:
            case OpcodeConst.ISTORE_1:
            case OpcodeConst.ISTORE_2:
            case OpcodeConst.ISTORE_3:
            case OpcodeConst.IINC:
                return TypeUtils.INT;
            case OpcodeConst.LLOAD:
            case OpcodeConst.LLOAD_0:
            case OpcodeConst.LLOAD_1:
            case OpcodeConst.LLOAD_2:
            case OpcodeConst.LLOAD_3:
            case OpcodeConst.LSTORE:
            case OpcodeConst.LSTORE_0:
            case OpcodeConst.LSTORE_1:
            case OpcodeConst.LSTORE_2:
            case OpcodeConst.LSTORE_3:
                return TypeUtils.LONG;
            case OpcodeConst.DLOAD:
            case OpcodeConst.DLOAD_0:
            case OpcodeConst.DLOAD_1:
            case OpcodeConst.DLOAD_2:
            case OpcodeConst.DLOAD_3:
            case OpcodeConst.DSTORE:
            case OpcodeConst.DSTORE_0:
            case OpcodeConst.DSTORE_1:
            case OpcodeConst.DSTORE_2:
            case OpcodeConst.DSTORE_3:
                return TypeUtils.DOUBLE;
            case OpcodeConst.FLOAD:
            case OpcodeConst.FLOAD_0:
            case OpcodeConst.FLOAD_1:
            case OpcodeConst.FLOAD_2:
            case OpcodeConst.FLOAD_3:
            case OpcodeConst.FSTORE:
            case OpcodeConst.FSTORE_0:
            case OpcodeConst.FSTORE_1:
            case OpcodeConst.FSTORE_2:
            case OpcodeConst.FSTORE_3:
                return TypeUtils.FLOAT;
            case OpcodeConst.ALOAD:
            case OpcodeConst.ALOAD_0:
            case OpcodeConst.ALOAD_1:
            case OpcodeConst.ALOAD_2:
            case OpcodeConst.ALOAD_3:
            case OpcodeConst.ASTORE:
            case OpcodeConst.ASTORE_0:
            case OpcodeConst.ASTORE_1:
            case OpcodeConst.ASTORE_2:
            case OpcodeConst.ASTORE_3:
                return TypeUtils.OBJECT;
            default:
                throw new RuntimeException("Oops: unknown case in switch" + opcode);
        }
    }
}
