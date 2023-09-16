package com.Hileb.all_accessible;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

/**
 * @Project All Accessible
 * @Author Hileb
 * @Date 2023/9/16 22:04
 **/
@SuppressWarnings("unused")
public class AllAccessibleModContainer extends DummyModContainer {
    public AllAccessibleModContainer(){
        super(new ModMetadata());
        ModMetadata metadata=this.getMetadata();
        metadata.modId= AllAccessibleCoreMod.MODID;
        metadata.name="All accessible";
        metadata.description="All accessible is a mod that all access transform.";
        metadata.version="beta.3 for 1.12.2-1.8.8";
        metadata.url="";
        metadata.logoFile="";
        metadata.authorList.add("Hileb");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }
}
