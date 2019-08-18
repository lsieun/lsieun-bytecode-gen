package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.gen.opcode.Instruction;

public abstract class SelectInstruction extends BranchInstruction {

    public int[] matches; // matches, i.e., case 1: ...
    public int[] offsets; // target offsets
    public Instruction[] targets;

    public SelectInstruction(short opcode, int length) {
        super(opcode, length);
    }

    @Override
    public boolean containsTarget(Instruction ins) {
        if (super.target == ins) {
            return true;
        }
        for (final Instruction t : targets) {
            if (t == ins) {
                return true;
            }
        }
        return false;
    }
}
