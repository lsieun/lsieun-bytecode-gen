package lsieun.bytecode.gen.attrs.code;

import lsieun.bytecode.core.cst.AttrConst;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.clazz.ConstantPool;

// The new table is used when generic types are about...

//LocalVariableTable_attribute {
//       u2 attribute_name_index;
//       u4 attribute_length;
//       u2 local_variable_table_length;
//       {  u2 start_pc;
//          u2 length;
//          u2 name_index;
//          u2 descriptor_index;
//          u2 index;
//       } local_variable_table[local_variable_table_length];
//     }

//LocalVariableTypeTable_attribute {
//    u2 attribute_name_index;
//    u4 attribute_length;
//    u2 local_variable_type_table_length;
//    {
//      u2 start_pc;
//      u2 length;
//      u2 name_index;
//      u2 signature_index;
//      u2 index;
//    } local_variable_type_table[local_variable_type_table_length];
//  }
public class LocalVariableTypeTable extends AttributeInfo {
    public LocalVariable[] local_variable_type_table;        // variables

    public LocalVariableTypeTable(final int name_index, final int length, final LocalVariable[] local_variable_table, final ConstantPool constant_pool) {
        super(AttrConst.ATTR_LOCAL_VARIABLE_TYPE_TABLE, name_index, length, constant_pool);
        this.local_variable_type_table = local_variable_table;
    }

    public final int getTableLength() {
        return local_variable_type_table == null ? 0 : local_variable_type_table.length;
    }
}
