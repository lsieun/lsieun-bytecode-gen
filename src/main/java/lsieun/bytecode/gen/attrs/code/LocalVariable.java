package lsieun.bytecode.gen.attrs.code;

import lsieun.bytecode.gen.clazz.ConstantPool;

public class LocalVariable {
    public final int start_pc; // Range in which the variable is valid
    public final int length;
    public final int name_index; // Index in constant pool of variable name
    public final int signature_index; // Index of variable signature
    public final int index; /* Variable is `index'th local variable on
     * this method's frame.
     */
    // FIXME: 验证这两个变量有没有用呢？
    private ConstantPool constant_pool;
    private int orig_index; // never changes; used to match up with LocalVariableTypeTable entries

    /**
     * @param start_pc Range in which the variable
     * @param length ... is valid
     * @param name_index Index in constant pool of variable name
     * @param signature_index Index of variable's signature
     * @param index Variable is `index'th local variable on the method's frame
     * @param constant_pool Array of constants
     */
    public LocalVariable(final int start_pc, final int length, final int name_index, final int signature_index, final int index,
                         final ConstantPool constant_pool) {
        this.start_pc = start_pc;
        this.length = length;
        this.name_index = name_index;
        this.signature_index = signature_index;
        this.index = index;
        this.constant_pool = constant_pool;
        this.orig_index = index;
    }
}
