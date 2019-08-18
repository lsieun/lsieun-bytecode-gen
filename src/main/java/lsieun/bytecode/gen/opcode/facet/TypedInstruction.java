package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.gen.ConstantPoolGen;

public interface TypedInstruction {
    Type getType(ConstantPoolGen cpg);
}
