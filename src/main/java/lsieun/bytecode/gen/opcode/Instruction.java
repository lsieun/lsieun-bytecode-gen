package lsieun.bytecode.gen.opcode;

import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.opcode.facet.StackConsumer;
import lsieun.bytecode.gen.opcode.facet.StackProducer;

public abstract class Instruction implements StackConsumer, StackProducer {
    // Core Info
    public short opcode = -1; // Opcode number
    public int length = 1; // Length of opcode in bytes
    public int position = -1;

    // Auxiliary Info
    public Instruction pre;
    public Instruction next;

    public Instruction(final short opcode, final int length) {
        this.opcode = opcode;
        this.length = length;
    }

    /**
     * Dump instruction as byte code to stream out.
     *
     * @param out Output stream
     */
    public abstract void dump(final DataOutputStream out) throws IOException;

    @Override
    public int consumeStack(ConstantPoolGen cpg) {
        return OpcodeConst.getConsumeStack(opcode);
    }

    @Override
    public int produceStack(ConstantPoolGen cpg) {
        return OpcodeConst.getProduceStack(opcode);
    }
}
