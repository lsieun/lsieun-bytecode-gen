package lsieun.bytecode.gen.attrs.code;

public class LineNumber {
    /**
     * Program Counter (PC) corresponds to line
     */
    public short start_pc;

    /**
     * number in source file
     */
    public short line_number;

    /**
     * @param start_pc    Program Counter (PC) corresponds to
     * @param line_number line number in source file
     */
    public LineNumber(final int start_pc, final int line_number) {
        this.start_pc = (short) start_pc;
        this.line_number = (short) line_number;
    }
}
