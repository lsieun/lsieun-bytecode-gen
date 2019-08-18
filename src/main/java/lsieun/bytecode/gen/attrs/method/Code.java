package lsieun.bytecode.gen.attrs.method;

import lsieun.bytecode.core.cst.AttrConst;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.attrs.code.LineNumberTable;
import lsieun.bytecode.gen.attrs.code.LocalVariableTable;
import lsieun.bytecode.gen.clazz.ConstantPool;

/**
 * This class represents a chunk of Java byte code contained in a
 * method. It is instantiated by the
 * <em>Attribute.readAttribute()</em> method. A <em>Code</em>
 * attribute contains informations about operand stack, local
 * variables, byte code and the exceptions handled within this
 * method.
 *
 * This attribute has attributes itself, namely <em>LineNumberTable</em> which
 * is used for debugging purposes and <em>LocalVariableTable</em> which
 * contains information about the local variables.
 */
public class Code extends AttributeInfo {
    public final int max_stack; // Maximum size of stack used by this method
    public final int max_locals; // Number of local variables
    public byte[] code; // Actual byte code
    public CodeException[] exception_table; // Table of handled exceptions
    public AttributeInfo[] attributes; // or LocalVariable

    /**
     * @param name_index Index pointing to the name <em>Code</em>
     * @param length Content length in bytes
     * @param max_stack Maximum size of stack
     * @param max_locals Number of local variables
     * @param code Actual byte code
     * @param exception_table Table of handled exceptions
     * @param attributes Attributes of code: LineNumber or LocalVariable
     * @param constant_pool Array of constants
     */
    public Code(final int name_index, final int length, final int max_stack, final int max_locals, final byte[] code,
                final CodeException[] exception_table, final AttributeInfo[] attributes, final ConstantPool constant_pool) {
        super(AttrConst.ATTR_CODE, name_index, length, constant_pool);
        this.max_stack = max_stack;
        this.max_locals = max_locals;
        this.code = code != null ? code : new byte[0];
        this.exception_table = exception_table != null ? exception_table : new CodeException[0];
        this.attributes = attributes != null ? attributes : new AttributeInfo[0];

        super.attribute_length = calculateLength(); // Adjust length
    }

    /**
     * @return the full size of this code attribute, minus its first 6 bytes,
     * including the size of all its contained attributes
     */
    private int calculateLength() {
        int len = 0;
        if (attributes != null) {
            for (final AttributeInfo attribute : attributes) {
                len += attribute.attribute_length + 6 /*attribute header size*/;
            }
        }
        return len + getInternalLength();
    }

    /**
     * @return the internal length of this code attribute (minus the first 6 bytes)
     * and excluding all its attributes
     */
    private int getInternalLength() {
        return 2 /*max_stack*/+ 2 /*max_locals*/+ 4 /*code length*/
                + code.length /*byte-code*/
                + 2 /*exception-table length*/
                + 8 * (exception_table == null ? 0 : exception_table.length) /* exception table */
                + 2 /* attributes count */;
    }

    /**
     * @return LineNumberTable of Code, if it has one
     */
    public LineNumberTable getLineNumberTable() {
        for (final AttributeInfo attribute : attributes) {
            if (attribute instanceof LineNumberTable) {
                return (LineNumberTable) attribute;
            }
        }
        return null;
    }


    /**
     * @return LocalVariableTable of Code, if it has one
     */
    public LocalVariableTable getLocalVariableTable() {
        for (final AttributeInfo attribute : attributes) {
            if (attribute instanceof LocalVariableTable) {
                return (LocalVariableTable) attribute;
            }
        }
        return null;
    }

    public static final class CodeException {
        public final int start_pc; // Range in the code the exception handler is
        public final int end_pc; // active. start_pc is inclusive, end_pc exclusive
        public final int handler_pc; /* Starting address of exception handler, i.e.,
         * an offset from start of code.
         */
        public final int catch_type; /* If this is zero the handler catches any
         * exception, otherwise it points to the
         * exception class which is to be caught.
         */

        /**
         * @param start_pc Range in the code the exception handler is active,
         * start_pc is inclusive while
         * @param end_pc is exclusive
         * @param handler_pc Starting address of exception handler, i.e.,
         * an offset from start of code.
         * @param catch_type If zero the handler catches any
         * exception, otherwise it points to the exception class which is
         * to be caught.
         */
        public CodeException(final int start_pc, final int end_pc, final int handler_pc, final int catch_type) {
            this.start_pc = start_pc;
            this.end_pc = end_pc;
            this.handler_pc = handler_pc;
            this.catch_type = catch_type;
        }
    }

}



