package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.gen.ConstantPoolGen;

/**
 * Denote an instruction that may produce a value on top of the stack
 * (this excludes DUP_X1, e.g.)
 */
public interface StackProducer {

    /**
     * @return how many words are produced on stack
     */
    int produceStack(ConstantPoolGen cpg);
}
