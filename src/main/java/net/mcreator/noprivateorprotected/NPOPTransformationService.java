package net.mcreator.noprivateorprotected;

import cpw.mods.modlauncher.LaunchPluginHandler;
import cpw.mods.modlauncher.Launcher;
import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;
import cpw.mods.modlauncher.serviceapi.ILaunchPluginService;
import net.mcreator.noprivateorprotected.transformer.NPOPLaunchPluginService;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NPOPTransformationService implements ITransformationService {
    static {
        try {
            Field field= Launcher.class.getDeclaredField("launchPlugins");
            field.setAccessible(true);
            Object obj=field.get(Launcher.INSTANCE);
            field= LaunchPluginHandler.class.getDeclaredField("plugins");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            Map<String, ILaunchPluginService> map=(Map<String, ILaunchPluginService>)field.get(obj);
            map.put("NPOP LaunchPluginService",new NPOPLaunchPluginService());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    @Nonnull
    @Override
    public String name() {
        return "NPOP TransformationService";
    }
//    @SuppressWarnings("unchecked")
//    public static boolean removeCoremod(String pluginName,String serviceName){
//        try {
//            Field field = Launcher.class.getDeclaredField("launchPlugins");
//            field.setAccessible(true);
//            Object obj=field.get(Launcher.INSTANCE);
//            field= LaunchPluginHandler.class.getDeclaredField("plugins");
//            field.setAccessible(true);
//            Map<String, ILaunchPluginService> map=(Map<String, ILaunchPluginService>)field.get(obj);
//            for (String s:map.keySet()){
//                System.out.println(s);
//            }
//            map.remove(pluginName);
//            field=Launcher.class.getDeclaredField("transformationServicesHandler");
//            field.setAccessible(true);
//            Object obj2=field.get(Launcher.INSTANCE);
//            field=Class.forName("cpw.mods.modlauncher.TransformationServicesHandler").getDeclaredField("serviceLookup");
//            field.setAccessible(true);
//            Map<String, TransformationServiceDecorator> map2=(Map<String, TransformationServiceDecorator>)field.get(obj2);
//            Constructor con=TransformationServiceDecorator.class.getDeclaredConstructor(ITransformationService.class);
//            con.setAccessible(true);
//            Object badobj=con.newInstance(new ITransformationService() {
//                @Nonnull
//                @Override
//                public String name() {return "broken_"+serviceName;}
//
//                @Override
//                public void initialize(IEnvironment environment) {}
//
//                @Override
//                public void beginScanning(IEnvironment environment) {}
//
//                @Override
//                public void onLoad(IEnvironment env, Set<String> otherServices){}
//
//                @Nonnull
//                @Override
//                public List<ITransformer> transformers() {return Collections.emptyList();}
//            });
//            map2.replace(serviceName, (TransformationServiceDecorator)badobj);
//            return true;
//        } catch (Throwable e) {
//            return false;
//        }
//    }

    @Override
    public void initialize(IEnvironment environment) {
    }

    @Override
    public void beginScanning(IEnvironment environment) {
    }

    @Override
    public void onLoad(IEnvironment env, Set<String> otherServices){}

    @Nonnull
    @Override
    public List<ITransformer> transformers() {
        return Collections.emptyList();
    }
}
