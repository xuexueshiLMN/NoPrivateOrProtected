package net.mcreator.noprivateorprotected.transformer;

import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.EnumSet;

import static java.lang.reflect.Modifier.*;

public class NPOPLaunchPluginService implements ILaunchPluginService {
    public static Logger LOGGER = LogManager.getLogger(NPOPLaunchPluginService.class);

    @Override
    public String name() {
        return "NPOP LaunchPluginService";
    }

    public NPOPLaunchPluginService(){
        LOGGER.debug("If you have installed NPOP mod(modid:noprivateorprotected),you will see this.");
    }

    @Override
    public EnumSet<Phase> handlesClass(Type classType, final boolean isEmpty) {
        return EnumSet.of(Phase.AFTER);
    }

    @Override
    public boolean processClass(final Phase phase, ClassNode classNode, final Type classType, String reason) {
        if (phase==Phase.BEFORE){
            return false;
        }
        if ("classloading".equals(reason)){
            boolean changed=false;
            if (isPrivate(classNode.access)){
                classNode.access&=~Opcodes.ACC_PRIVATE;
                classNode.access|=Opcodes.ACC_PUBLIC;
                changed=true;
            }
            if (isProtected(classNode.access)){
                classNode.access&=~Opcodes.ACC_PROTECTED;
                classNode.access|=Opcodes.ACC_PUBLIC;
                changed=true;
            }
            if (isFinal(classNode.access)){
                classNode.access&=~Opcodes.ACC_FINAL;
                changed=true;
            }
            for (MethodNode method:classNode.methods){
                if (!method.name.equals("<clinit>")){
                    if (isPrivate(method.access)){
                        method.access&=~Opcodes.ACC_PRIVATE;
                        method.access|=Opcodes.ACC_PUBLIC;
                        changed=true;
                    }
                    if (isProtected(method.access)){
                        method.access&=~Opcodes.ACC_PROTECTED;
                        method.access|=Opcodes.ACC_PUBLIC;
                        changed=true;
                    }
                    if (isFinal(method.access)){
                        method.access&=~Opcodes.ACC_FINAL;
                        changed=true;
                    }
                    if (!isPublic(method.access)){
                        method.access|=Opcodes.ACC_PUBLIC;
                        changed=true;
                    }
                }
            }
            for (FieldNode field:classNode.fields){
                if (!"$VALUES".equals(field.name)){
                    if (isPrivate(field.access)){
                        field.access&=~Opcodes.ACC_PRIVATE;
                        field.access|=Opcodes.ACC_PUBLIC;
                        changed=true;
                    }
                    if (isProtected(field.access)){
                        field.access&=~Opcodes.ACC_PROTECTED;
                        field.access|=Opcodes.ACC_PUBLIC;
                        changed=true;
                    }
                    if (isFinal(field.access)&&!isInterface(classNode.access)){
                        field.access&=~Opcodes.ACC_FINAL;
                        changed=true;
                    }
                    if (!isPublic(field.access)){
                        field.access|=Opcodes.ACC_PUBLIC;
                        changed=true;
                    }
                }
            }
            return changed;
        }
        return false;
    }
}
