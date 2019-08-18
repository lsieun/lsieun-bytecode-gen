package lsieun.bytecode.gen;

import lsieun.bytecode.core.type.ObjectType;
import lsieun.bytecode.gen.opcode.Instruction;

public class CodeExceptionGen {
    public Instruction start_pc;
    public Instruction end_pc;
    public Instruction handler_pc;
    public ObjectType catch_type;

    public CodeExceptionGen(Instruction start_pc, Instruction end_pc, Instruction handler_pc, ObjectType catch_type) {
        this.start_pc = start_pc;
        this.end_pc = end_pc;
        this.handler_pc = handler_pc;
        this.catch_type = catch_type;
    }
}
