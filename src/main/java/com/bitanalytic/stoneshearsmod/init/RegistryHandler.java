package com.bitanalytic.stoneshearsmod.init;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class RegistryHandler {

	public static void register() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModItems.registerItems(eventBus);
	}
}
