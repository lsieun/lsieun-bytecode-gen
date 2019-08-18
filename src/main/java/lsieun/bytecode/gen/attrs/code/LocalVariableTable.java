package lsieun.bytecode.gen.attrs.code;

import lsieun.bytecode.core.cst.AttrConst;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.clazz.ConstantPool;

/**
 * This class represents colection of local variables in a
 * method. This attribute is contained in the <em>Code</em> attribute.
 */
public class LocalVariableTable extends AttributeInfo {
    private LocalVariable[] local_variable_table; // variables

    /**
     * @param name_index Index in constant pool to `LocalVariableTable'
     * @param length Content length in bytes
     * @param local_variable_table Table of local variables
     * @param constant_pool Array of constants
     */
    public LocalVariableTable(final int name_index, final int length, final LocalVariable[] local_variable_table,
                              final ConstantPool constant_pool) {
        super(AttrConst.ATTR_LOCAL_VARIABLE_TABLE, name_index, length, constant_pool);
        this.local_variable_table = local_variable_table;
    }
}

