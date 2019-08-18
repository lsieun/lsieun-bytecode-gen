package lsieun.bytecode.gen.utils;

import org.junit.Test;

import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.opcode.*;

public class OpcodeEngineTest {

    @Test
    public void testHelloWorld() {
        ConstantPoolGen cpg = new ConstantPoolGen();
        final InstructionList il = new InstructionList();
        il.append(new GETSTATIC(cpg.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;")));
        il.append(new LDC(cpg.addString("Hello World")));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V")));
        il.append(new RETURN());

        OpcodeEngine engine = new OpcodeEngine(cpg, "lsieun.sample.ABC", il);
        engine.dump();
    }

    @Test
    public void testSelect() {
        ConstantPoolGen cpg = new ConstantPoolGen();
        final InstructionList il = new InstructionList();
        il.append(new BIPUSH((byte) 10));
        il.append(new ISTORE_1());
        il.append(new ICONST_0());
        il.append(new ISTORE_2());
        il.append(new ILOAD_1());


        ICONST_4 iconst_4 = new ICONST_4();
        LOOKUPSWITCH lookupswitch = new LOOKUPSWITCH();
        lookupswitch.target = iconst_4;

        ICONST_2 iconst_2 = new ICONST_2();
        ICONST_3 iconst_3 = new ICONST_3();
        ICONST_1 iconst_1 = new ICONST_1();

        lookupswitch.targets = new Instruction[] {iconst_2, iconst_3, iconst_1};
        lookupswitch.matches = new int[] {200, 800, 1000};
        lookupswitch.npairs = 3;

        il.append(lookupswitch);
        il.append(iconst_1);
        il.append(new ISTORE_2());



        int getstatic_index = cpg.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;");
        GETSTATIC getstatic = new GETSTATIC(getstatic_index);

        GOTO goto1 = new GOTO();
        goto1.target = getstatic;
        il.append(goto1);

        il.append(iconst_2);
        il.append(new ISTORE_2());

        GOTO goto2 = new GOTO();
        goto2.target = getstatic;
        il.append(goto2);

        il.append(iconst_3);
        il.append(new ISTORE_2());

        GOTO goto3 = new GOTO();
        goto3.target = getstatic;
        il.append(goto3);

        il.append(iconst_4);
        il.append(new ISTORE_2());
        il.append(getstatic);
        il.append(new ILOAD_2());
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.io.PrintStream", "println", "(I)V")));
        il.append(new RETURN());
        il.caculatePosition();
        il.caculateOffset();
        OpcodeEngine engine = new OpcodeEngine(cpg, "lsieun.sample.ABC", il);
        engine.dump();
    }

}