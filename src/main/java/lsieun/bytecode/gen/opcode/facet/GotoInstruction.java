package lsieun.bytecode.gen.opcode.facet;

public abstract class GotoInstruction extends BranchInstruction {
    public GotoInstruction(short opcode, int length) {
        super(opcode, length);
    }
}
