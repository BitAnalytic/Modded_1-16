package com.bitanalytic.stoneshearsmod.init;

import com.bitanalytic.stoneshearsmod.StoneShearsMod;
import com.bitanalytic.stoneshearsmod.item.StoneShears;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StoneShearsMod.MOD_ID);

	public static final RegistryObject<Item> STONE_SHEARS = ITEMS.register("stone_shears", StoneShears::new);

	public static void registerItems(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
