package lsieun.bytecode.gen.opcode.facet;

import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.clazz.ConstantPool;
import lsieun.bytecode.gen.cp.ConstantFieldref;
import lsieun.bytecode.gen.cp.ConstantNameAndType;
import lsieun.bytecode.gen.cp.ConstantUtf8;
import lsieun.bytecode.gen.opcode.Instruction;

public abstract class FieldInstruction extends Instruction {

    public int index;

    public FieldInstruction(short opcode, int length) {
        super(opcode, length);
    }

    /**
     * @return size of field (1 or 2)
     */
    protected int getFieldSize(final ConstantPoolGen cpg) {
        return TypeUtils.size(TypeUtils.getTypeSizeAndCharNum(getSignature(cpg)));
    }

    public String getSignature(final ConstantPoolGen cpg) {
        final ConstantPool cp = cpg.getConstantPool();

        ConstantFieldref cfr = (ConstantFieldref) cp.entries[index];
        int name_and_type_index = cfr.name_and_type_index;

        final ConstantNameAndType cnat = (ConstantNameAndType) cp.entries[name_and_type_index];
        int descriptor_index = cnat.descriptor_index;

        final ConstantUtf8 cutf8 = (ConstantUtf8) cp.entries[descriptor_index];
        String signature = cutf8.utf8Value;

        return signature;
    }
}
