package lsieun.bytecode.gen.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lsieun.bytecode.core.cst.JVMConst;
import lsieun.bytecode.gen.attrs.AttributeInfo;
import lsieun.bytecode.gen.attrs.SourceFile;
import lsieun.bytecode.gen.attrs.method.Code;
import lsieun.bytecode.gen.clazz.ConstantPool;
import lsieun.bytecode.gen.clazz.FieldInfo;
import lsieun.bytecode.gen.clazz.JavaClass;
import lsieun.bytecode.gen.clazz.MethodInfo;
import lsieun.bytecode.gen.cp.*;

@SuppressWarnings("Duplicates")
public class DumpUtils {
    /**
     * Dump class to a file.
     *
     * @param file Output file
     * @throws IOException
     */
    public static void dumpJavaClass(final JavaClass javaClass, final File file) throws IOException {
        final String parent = file.getParent();
        if (parent != null) {
            final File dir = new File(parent);
            if (!dir.mkdirs()) { // either was not created or already existed
                if (!dir.isDirectory()) {
                    throw new IOException("Could not create the directory " + dir);
                }
            }
        }
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dumpJavaClass(javaClass, dos);
        }
    }

    /**
     * Dump Java class to output stream in binary format.
     *
     * @param file Output stream
     * @throws IOException
     */
    public static void dumpJavaClass(final JavaClass javaClass, final DataOutputStream file) throws IOException {

        file.writeInt(JVMConst.JVM_CLASSFILE_MAGIC);
        file.writeShort(javaClass.minor);
        file.writeShort(javaClass.major);
        dumpConstantPool(javaClass.constant_pool, file);

        file.writeShort(javaClass.access_flags);
        file.writeShort(javaClass.class_name_index);
        file.writeShort(javaClass.superclass_name_index);
        file.writeShort(javaClass.interfaces.length);
        for (final int interface_index : javaClass.interfaces) {
            file.writeShort(interface_index);
        }

        file.writeShort(javaClass.fields.length);
        for (final FieldInfo field : javaClass.fields) {
            dumpFieldInfo(field, file);
        }

        file.writeShort(javaClass.methods.length);
        for (final MethodInfo method : javaClass.methods) {
            dumpMethodInfo(method, file);
        }

        if (javaClass.attributes != null) {
            file.writeShort(javaClass.attributes.length);
            for (final AttributeInfo attribute : javaClass.attributes) {
                dumpAttributeInfo(attribute, file);
            }
        } else {
            file.writeShort(0);
        }

        file.flush();
    }

    public static void dumpConstantPool(final ConstantPool constant_pool, final DataOutputStream file) throws IOException {
        file.writeShort(constant_pool.count);
        for (int i = 1; i < constant_pool.count; i++) {
            Constant constant = constant_pool.entries[i];
            if(constant == null) continue;
            if(constant instanceof ConstantUtf8) {
                ConstantUtf8 item = (ConstantUtf8) constant;

                file.writeByte(item.tag);
                file.writeUTF(item.utf8Value);
            }
            else if(constant instanceof ConstantInteger) {
                ConstantInteger item = (ConstantInteger) constant;

                file.writeByte(item.tag);
                file.writeInt(item.intValue);
            }
            else if(constant instanceof ConstantFloat) {
                ConstantFloat item = (ConstantFloat) constant;

                file.writeByte(item.tag);
                file.writeFloat(item.floatValue);
            }
            else if(constant instanceof ConstantLong) {
                ConstantLong item = (ConstantLong) constant;

                file.writeByte(item.tag);
                file.writeLong(item.longValue);
            }
            else if(constant instanceof ConstantDouble) {
                ConstantDouble item = (ConstantDouble) constant;

                file.writeByte(item.tag);
                file.writeDouble(item.doubleValue);
            }
            else if(constant instanceof ConstantClass) {
                ConstantClass item = (ConstantClass) constant;

                file.writeByte(item.tag);
                file.writeShort(item.name_index);
            }
            else if(constant instanceof ConstantString) {
                ConstantString item = (ConstantString) constant;

                file.writeByte(item.tag);
                file.writeShort(item.string_index);
            }
            else if(constant instanceof ConstantFieldref) {
                ConstantFieldref item = (ConstantFieldref) constant;

                file.writeByte(item.tag);
                file.writeShort(item.class_index);
                file.writeShort(item.name_and_type_index);
            }
            else if(constant instanceof ConstantMethodref) {
                ConstantMethodref item = (ConstantMethodref) constant;

                file.writeByte(item.tag);
                file.writeShort(item.class_index);
                file.writeShort(item.name_and_type_index);
            }
            else if(constant instanceof ConstantInterfaceMethodref) {
                ConstantInterfaceMethodref item = (ConstantInterfaceMethodref) constant;

                file.writeByte(item.tag);
                file.writeShort(item.class_index);
                file.writeShort(item.name_and_type_index);
            }
            else if(constant instanceof ConstantNameAndType) {
                ConstantNameAndType item = (ConstantNameAndType) constant;

                file.writeByte(item.tag);
                file.writeShort(item.name_index);
                file.writeShort(item.descriptor_index);
            }
            else {
                throw new RuntimeException("dumpConstantPool Exception: " + constant.getClass());
            }
        }
    }

    public static void dumpFieldInfo(final FieldInfo fieldInfo, final DataOutputStream file) throws IOException {
        file.writeShort(fieldInfo.access_flags);
        file.writeShort(fieldInfo.name_index);
        file.writeShort(fieldInfo.signature_index);
        file.writeShort(fieldInfo.attributes_count);
        for(final AttributeInfo attr : fieldInfo.attributes) {
            dumpAttributeInfo(attr, file);
        }
    }

    public static void dumpMethodInfo(final MethodInfo methodInfo, final DataOutputStream file) throws IOException {
        file.writeShort(methodInfo.access_flags);
        file.writeShort(methodInfo.name_index);
        file.writeShort(methodInfo.signature_index);
        file.writeShort(methodInfo.attributes_count);
        for(final AttributeInfo attr : methodInfo.attributes) {
            dumpAttributeInfo(attr, file);
        }
    }

    public static void dumpAttributeInfo(final AttributeInfo attr, final DataOutputStream file) throws IOException {
        file.writeShort(attr.attribute_name_index);
        file.writeInt(attr.attribute_length);

        if(1 == 2) {
            //
        }
        else if(attr instanceof Code) {
            Code item = (Code) attr;

            file.writeShort(item.max_stack);
            file.writeShort(item.max_locals);
            file.writeInt(item.code.length);
            file.write(item.code, 0, item.code.length);
            file.writeShort(0); // exception_table_length
            file.writeShort(0); // attributes_count
        }
        else if(attr instanceof SourceFile) {
            SourceFile item = (SourceFile) attr;

            file.writeShort(item.sourcefile_index);
        }
        else {
            throw new RuntimeException("dumpAttributeInfo Exception: " + attr.getClass());
        }
    }
}
