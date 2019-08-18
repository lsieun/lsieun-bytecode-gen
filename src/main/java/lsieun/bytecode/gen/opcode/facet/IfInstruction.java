package lsieun.bytecode.gen.opcode.facet;

public abstract class IfInstruction extends BranchInstruction {
    public IfInstruction(short opcode, int length) {
        super(opcode, length);
    }
}
