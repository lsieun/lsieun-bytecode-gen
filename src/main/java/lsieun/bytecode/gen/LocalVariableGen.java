package lsieun.bytecode.gen;

import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.gen.opcode.Instruction;

/**
 * This class represents a local variable within a method. It contains its
 * scope, name and type. The generated LocalVariable object can be obtained
 * with getLocalVariable which needs the instruction list and the constant
 * pool as parameters.
 */
public class LocalVariableGen {
    private int index;
    private String name;
    private Type type;
    private Instruction start;
    private Instruction end;
    private int orig_index; // never changes; used to match up with LocalVariableTypeTable entries
    private boolean live_to_end;
}
