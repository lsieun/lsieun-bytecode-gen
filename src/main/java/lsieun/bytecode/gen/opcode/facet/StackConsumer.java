package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.gen.ConstantPoolGen;

/**
 * Denote an instruction that may consume a value from the stack.
 */
public interface StackConsumer {

    /**
     * @return how many words are consumed from stack
     */
    int consumeStack(ConstantPoolGen cpg);
}
