package lsieun.bytecode.gen.attrs.method;

import lsieun.bytecode.core.cst.AttrConst;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.clazz.ConstantPool;

/**
 * This class represents the table of exceptions that are thrown by a
 * method. This attribute may be used once per method.  The name of
 * this class is <em>ExceptionTable</em> for historical reasons; The
 * Java Virtual Machine Specification, Second Edition defines this
 * attribute using the name <em>Exceptions</em> (which is inconsistent
 * with the other classes).
 */
public class ExceptionTable extends AttributeInfo {
    public int[] exception_index_table; // constant pool

    /**
     * @param name_index            Index in constant pool
     * @param length                Content length in bytes
     * @param exception_index_table Table of indices in constant pool
     * @param constant_pool         Array of constants
     */
    public ExceptionTable(final int name_index, final int length,
                          final int[] exception_index_table,
                          final ConstantPool constant_pool) {
        super(AttrConst.ATTR_EXCEPTIONS, name_index, length, constant_pool);
        this.exception_index_table = exception_index_table != null ? exception_index_table : new int[0];
    }

    /**
     * @return Length of exception table.
     */
    public final int getNumberOfExceptions() {
        return exception_index_table == null ? 0 : exception_index_table.length;
    }
}
