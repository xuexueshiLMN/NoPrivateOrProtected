package com.Hileb.all_accessible;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.lang.reflect.Modifier;

import static java.lang.reflect.Modifier.*;

/**
 * @Project All Accessible
 * @Author Hileb
 * @Date 2023/9/16 22:08
 **/
@SuppressWarnings("unused")
public class AllAccessibleAccessTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        try{
            if (basicClass!=null){
                ClassReader classReader=new ClassReader(basicClass);
                ClassNode cn=new ClassNode();
                classReader.accept(cn,0);
                int c1=cn.access;
                if((c1 & 0x00000200) == 0){
                    for(FieldNode fn:cn.fields){
                        fn.access=getTrueAccess(fn.access);
                    }
                    for (MethodNode mn:cn.methods){
                        if (!"<clinit>".equals(mn.name)){
                            mn.access=getTrueAccess(mn.access);
                        }
                    }
                    ClassWriter classWriter=new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                    cn.accept(classWriter);
                    return classWriter.toByteArray();
                }else return basicClass;
            }
            else return basicClass;

        }catch (Exception ignore){
            return basicClass;
        }
    }
    public static int getTrueAccess(int mod){
        int a=mod;
        if ((a & PRIVATE) != 0){
            a&=~Opcodes.ACC_PRIVATE;
            a|=Opcodes.ACC_PUBLIC;
        }else if ((a & PROTECTED) != 0){
            a&=~Opcodes.ACC_PROTECTED;
            a|=Opcodes.ACC_PUBLIC;
        }
        if ((a & FINAL) != 0){
            a&=~Opcodes.ACC_FINAL;
        }
        if ((a & PUBLIC) == 0){
            a|=Opcodes.ACC_PUBLIC;
        }
        return a;
    }
}
