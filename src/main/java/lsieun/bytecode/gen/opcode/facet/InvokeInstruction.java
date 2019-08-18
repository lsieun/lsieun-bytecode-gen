package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.core.cst.OpcodeConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.clazz.ConstantPool;
import lsieun.bytecode.gen.cp.*;
import lsieun.bytecode.gen.opcode.Instruction;

public abstract class InvokeInstruction extends Instruction {
    public int index;

    public InvokeInstruction(short opcode, int length) {
        super(opcode, length);
    }

    @Override
    public int consumeStack(ConstantPoolGen cpg) {
        int sum;
        if ((opcode == OpcodeConst.INVOKESTATIC) || (opcode == OpcodeConst.INVOKEDYNAMIC)) {
            sum = 0;
        } else {
            sum = 1; // this reference
        }

        final String signature = getSignature(cpg);
        sum += TypeUtils.getArgumentTypesSize(signature);
        return sum;
    }

    @Override
    public int produceStack(ConstantPoolGen cpg) {
        final String signature = getSignature(cpg);
        return TypeUtils.getReturnTypeSize(signature);
    }

    public String getSignature(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();
        final Constant c = cp.entries[index];

        int name_and_type_index = -1;
        if(c instanceof ConstantMethodref) {
            ConstantMethodref item = (ConstantMethodref) c;
            name_and_type_index = item.name_and_type_index;
        }
        else if(c instanceof ConstantInterfaceMethodref) {
            ConstantInterfaceMethodref item = (ConstantInterfaceMethodref) c;
            name_and_type_index = item.name_and_type_index;
        }
        else {
            throw new RuntimeException("Constant is " + c.getClass());
        }

        final ConstantNameAndType cnat = (ConstantNameAndType) cp.entries[name_and_type_index];
        int descriptor_index = cnat.descriptor_index;

        final ConstantUtf8 cutf8 = (ConstantUtf8) cp.entries[descriptor_index];
        String signature = cutf8.utf8Value;

        return signature;
    }
}
