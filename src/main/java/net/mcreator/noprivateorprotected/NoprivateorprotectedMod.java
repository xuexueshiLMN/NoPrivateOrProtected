package net.mcreator.noprivateorprotected;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("noprivateorprotected")
public class NoprivateorprotectedMod {
	public NoprivateorprotectedMod() {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}
}
