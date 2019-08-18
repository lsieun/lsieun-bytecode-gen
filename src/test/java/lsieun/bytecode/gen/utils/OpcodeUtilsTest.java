package lsieun.bytecode.gen.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import lsieun.bytecode.core.cst.OpcodeConst;

public class OpcodeUtilsTest {

    @Test
    public void testOpcode() {
        for (int i = 0; i < 256; i++) {
            String opcode_name = OpcodeConst.getOpcodeName(i);
            if(OpcodeConst.ILLEGAL_OPCODE.equals(opcode_name)) continue;

            int produceStack = OpcodeConst.getProduceStack(i);
            if(produceStack == OpcodeConst.UNPREDICTABLE || produceStack == OpcodeConst.UNDEFINED) {
                System.out.println(opcode_name);
            }

//            int consumeStack = OpcodeConst.getConsumeStack(i);
//            if(consumeStack == OpcodeConst.UNPREDICTABLE || consumeStack == OpcodeConst.UNDEFINED) {
//                System.out.println(opcode_name);
//            }
        }
    }
}