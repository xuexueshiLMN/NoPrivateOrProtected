package com.Hileb.all_accessible;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.lang.reflect.Modifier;

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
                cn.access=getTrueAccess(cn.access);
                for(FieldNode fn:cn.fields){
                    if (!Modifier.isInterface(cn.access)){
                        fn.access=getTrueAccess(fn.access);
                    }
                }
                for (MethodNode mn:cn.methods){
                    if (!"<clinit>".equals(mn.name)){
                        mn.access=getTrueAccess(mn.access);
                    }
                }
                ClassWriter classWriter=new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
                cn.accept(classWriter);
                return classWriter.toByteArray();
            }
        }catch (Exception ignore){
            return basicClass;
        }
        return basicClass;
    }
    public static int getTrueAccess(int mod){
        int a=mod;
        if (Modifier.isPrivate(a)){
            a&=~Opcodes.ACC_PRIVATE;
            a|=Opcodes.ACC_PUBLIC;
        }else if (Modifier.isProtected(a)){
            a&=~Opcodes.ACC_PROTECTED;
            a|=Opcodes.ACC_PUBLIC;
        }
        if (Modifier.isFinal(a)){
            a&=~Opcodes.ACC_FINAL;
        }
        if (!Modifier.isPublic(a)){
            a|=Opcodes.ACC_PUBLIC;
        }
        return a;
    }
}
