package lsieun.bytecode.gen.utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lsieun.bytecode.gen.opcode.Instruction;
import lsieun.bytecode.gen.opcode.LOOKUPSWITCH;
import lsieun.bytecode.gen.opcode.TABLESWITCH;
import lsieun.bytecode.gen.opcode.facet.BranchInstruction;
import lsieun.bytecode.gen.opcode.facet.SelectInstruction;

public class InstructionList {
    public int count;
    public Instruction start;
    public Instruction end;

    /**
     * Test for empty list.
     */
    public boolean isEmpty() {
        return start == null;
    } // && end == null

    public void append(Instruction ins) {
        if (isEmpty()) {
            start = end = ins;
            ins.pre = null;
            ins.next = null;
        }
        else {
            end.next = ins;
            ins.pre = end;
            ins.next = null;
            end = ins;
        }
        count++;
    }

    public byte[] getByteCode() {
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            Instruction current = start;
            while(current != null) {
                current.dump(out);

                current = current.next;
            }
            out.flush();
        } catch (final IOException e) {
            System.err.println(e);
            return new byte[0];
        }
        return b.toByteArray();
    }

    public void caculatePosition() {
        start.position = 0;
        for(Instruction current = start.next; current != null; current = current.next) {
            current.position = current.pre.position + current.pre.length;
            if(current instanceof LOOKUPSWITCH) {
                LOOKUPSWITCH lookupswitch = (LOOKUPSWITCH) current;
                lookupswitch.calculateLength();
            }
            else if(current instanceof TABLESWITCH) {
                TABLESWITCH tableswitch = (TABLESWITCH) current;
                tableswitch.calculateLength();
            }
        }
    }

    public void caculateOffset() {
        for(Instruction current = start; current != null; current = current.next) {
            if(current instanceof BranchInstruction) {
                BranchInstruction bi = (BranchInstruction) current;
                bi.branch = bi.target.position - bi.position;

                if(bi instanceof SelectInstruction) {
                    SelectInstruction si = (SelectInstruction) bi;
                    int size = si.targets.length;
                    si.offsets = new int[size];
                    for(int i=0; i<size; i++) {
                        int offset = si.targets[i].position - si.position;
                        si.offsets[i] = offset;
                    }
                }
            }

        }
    }


}
