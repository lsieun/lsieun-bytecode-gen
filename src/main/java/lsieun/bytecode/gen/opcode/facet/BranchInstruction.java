package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.gen.opcode.Instruction;

public abstract class BranchInstruction extends Instruction implements InstructionTargeter {
    public int branch;
    public Instruction target;

    public BranchInstruction(short opcode, int length) {
        super(opcode, length);
    }

    @Override
    public boolean containsTarget(Instruction ins) {
        return target == ins;
    }
}
