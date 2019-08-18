package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.gen.opcode.Instruction;

public interface InstructionTargeter {
    /**
     * Checks whether this targeter targets the specified instruction handle.
     */
    boolean containsTarget(Instruction ins);

}
