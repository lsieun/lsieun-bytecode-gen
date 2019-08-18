package lsieun.bytecode.gen.utils;

import java.io.File;
import java.io.IOException;

import lsieun.bytecode.core.cst.AccessConst;
import lsieun.bytecode.core.type.Type;
import lsieun.bytecode.core.utils.TypeUtils;
import lsieun.bytecode.gen.ClassGen;
import lsieun.bytecode.gen.ConstantPoolGen;
import lsieun.bytecode.gen.MethodGen;
import lsieun.bytecode.gen.clazz.JavaClass;
import lsieun.bytecode.gen.clazz.MethodInfo;
import lsieun.utils.io.FileUtils;

public class OpcodeEngine {
    private ConstantPoolGen cpg;
    private String classname;
    private InstructionList il;

    public OpcodeEngine(ConstantPoolGen cpg, String classname, InstructionList il) {
        this.cpg = cpg;
        this.classname = classname;
        this.il = il;
    }

    private JavaClass getJavaClass() {
        int index = classname.lastIndexOf(".");
        String source_file = ((index > -1) ? classname.substring(index + 1) : classname) + ".java";

        int access_flags = (AccessConst.ACC_PUBLIC | AccessConst.ACC_SUPER);

        ClassGen classGen = new ClassGen(classname, "java.lang.Object",
                source_file, access_flags, new String[0], cpg);

        // EmptyConstructor
        classGen.addEmptyConstructor(AccessConst.ACC_PUBLIC);

        // Main Method
        final MethodGen mg = classGen.getMethodGen(AccessConst.ACC_PUBLIC | AccessConst.ACC_STATIC,
                TypeUtils.VOID, TypeUtils.STR_ARGS, null, "main", il);
        MethodInfo mainMethod = mg.getMethod();
        classGen.addMethod(mainMethod);

        // getJavaClass
        JavaClass javaClass = classGen.getJavaClass();
        return javaClass;
    }

    public void dump() {
        JavaClass javaClass = getJavaClass();
        // Output
        String filename = FileUtils.getFilePath(this.getClass(), classname);

        System.out.println("file://" + filename);

        File file = new File(filename);
        try {
            DumpUtils.dumpJavaClass(javaClass, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
