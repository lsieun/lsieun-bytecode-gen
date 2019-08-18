package lsieun.bytecode.gen.attrs.code;

import lsieun.bytecode.core.cst.AttrConst;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.clazz.ConstantPool;

/**
 * This class represents a table of line numbers for debugging
 * purposes. This attribute is used by the <em>Code</em> attribute. It
 * contains pairs of PCs and line numbers.
 */
public class LineNumberTable extends AttributeInfo {
    private LineNumber[] line_number_table; // Table of line/numbers pairs

    /**
     * @param name_index        Index of name
     * @param length            Content length in bytes
     * @param line_number_table Table of line/numbers pairs
     * @param constant_pool     Array of constants
     */
    public LineNumberTable(final int name_index, final int length,
                           final LineNumber[] line_number_table,
                           final ConstantPool constant_pool) {
        super(AttrConst.ATTR_LINE_NUMBER_TABLE, name_index, length, constant_pool);
        this.line_number_table = line_number_table;
    }

    public final int getTableLength() {
        return line_number_table == null ? 0 : line_number_table.length;
    }

    /**
     * GOOD_CODE: 用binary search查找
     * Map byte code positions to source code lines.
     *
     * @param pos byte code offset
     * @return corresponding line in source code
     */
    public int getSourceLine(final int pos) {
        int l = 0;
        int r = line_number_table.length - 1;
        if (r < 0) {
            return -1;
        }
        int min_index = -1;
        int min = -1;
        /* Do a binary search since the array is ordered.
         */
        do {
            final int i = (l + r) / 2;
            final int j = line_number_table[i].start_pc;
            if (j == pos) {
                return line_number_table[i].line_number;
            } else if (pos < j) {
                r = i - 1;
            } else {
                l = i + 1;
            }
            /* If exact match can't be found (which is the most common case)
             * return the line number that corresponds to the greatest index less
             * than pos.
             */
            if (j < pos && j > min) {
                min = j;
                min_index = i;
            }
        } while (l <= r);
        /* It's possible that we did not find any valid entry for the bytecode
         * offset we were looking for.
         */
        if (min_index < 0) {
            return -1;
        }
        return line_number_table[min_index].line_number;
    }
}
