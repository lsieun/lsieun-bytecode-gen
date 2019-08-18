package lsieun.bytecode.gen;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.clazz.JavaClass;
import lsieun.bytecode.gen.clazz.MethodInfo;
import lsieun.bytecode.gen.opcode.GETSTATIC;
import lsieun.bytecode.gen.opcode.INVOKEVIRTUAL;
import lsieun.bytecode.gen.opcode.LDC;
import lsieun.bytecode.gen.opcode.RETURN;
import lsieun.bytecode.gen.utils.DumpUtils;
import lsieun.bytecode.gen.utils.InstructionList;
import lsieun.utils.io.FileUtils;

public class ClassGenTest {
    private static final String CLASSNAME_FQCN = "com.abc.HelloWorld";

    @Test
    public void testHelloWorld() {
        ConstantPoolGen cpg = new ConstantPoolGen();

        int index = CLASSNAME_FQCN.lastIndexOf(".");
        String source_file = ((index > -1) ? CLASSNAME_FQCN.substring(index + 1) : CLASSNAME_FQCN) + ".java";

        int access_flags = (AccessConst.ACC_PUBLIC | AccessConst.ACC_SUPER);

        ClassGen classGen = new ClassGen(CLASSNAME_FQCN, "java.lang.Object",
                source_file, access_flags, new String[0], cpg);

        // EmptyConstructor
        classGen.addEmptyConstructor(AccessConst.ACC_PUBLIC);

        // Main
        MethodInfo mainMethod = getMainMethod(classGen, "Great Job");
        classGen.addMethod(mainMethod);

        // getJavaClass
        JavaClass javaClass = classGen.getJavaClass();

        // Output
        String filename = FileUtils.getFilePath(ClassGenTest.class, CLASSNAME_FQCN);

        System.out.println("file://" + filename);

        File file = new File(filename);
        try {
            DumpUtils.dumpJavaClass(javaClass, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("GAME OVER");
    }

    public static MethodInfo getMainMethod(ClassGen classGen, String str) {
        ConstantPoolGen cpg = classGen.cpg;
        final InstructionList il = new InstructionList();
        il.append(new GETSTATIC(cpg.addFieldref("java.lang.System", "out", "Ljava/io/PrintStream;")));
        il.append(new LDC(cpg.addString(str)));
        il.append(new INVOKEVIRTUAL(cpg.addMethodref("java.io.PrintStream", "println", "(Ljava/lang/String;)V")));
        il.append(new RETURN());

        final MethodGen mg = classGen.getMethodGen(AccessConst.ACC_PUBLIC | AccessConst.ACC_STATIC,
                TypeUtils.VOID, TypeUtils.STR_ARGS, null, "main", il);
        MethodInfo methodInfo = mg.getMethod();
        return methodInfo;
    }

}