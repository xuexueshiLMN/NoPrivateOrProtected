package com.Hileb.all_accessible;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @Project All Accessible
 * @Author Hileb
 * @Date 2023/9/16 22:03
 **/
@SuppressWarnings("unused")
@IFMLLoadingPlugin.Name(AllAccessibleCoreMod.MODID)
public class AllAccessibleCoreMod implements IFMLLoadingPlugin {
    public static final String MODID="all_accessible";
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{
                "com.Hileb.all_accessible.AllAccessibleAccessTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return "com.Hileb.all_accessible.AllAccessibleModContainer";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }
    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
