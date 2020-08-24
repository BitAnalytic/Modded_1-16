package com.bitanalytic.stoneshearsmod.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.IForgeShearable;

import java.util.List;
import java.util.Random;

public class StoneShears extends ShearsItem {
	public StoneShears() {
		super(new Item.Properties().group(ItemGroup.TOOLS).maxDamage(64));
	}

	@Override
	public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity entity, Hand hand) {
		if (entity.world.isRemote) return ActionResultType.FAIL;
		if (entity instanceof IForgeShearable) {
			IForgeShearable target = (IForgeShearable) entity;
			BlockPos pos = new BlockPos(entity.getPosX(), entity.getPosY(), entity.getPosZ());
			if (target.isShearable(stack, entity.world, pos)) {
				List<ItemStack> drops = target.onSheared(playerIn, stack, entity.world, pos,
						EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, stack));
				Random rand = new Random();
				ItemStack drop = drops.get(0);
				ItemEntity ent = entity.entityDropItem(drop, 1.0F);
				ent.setMotion(ent.getMotion().add((double)((rand.nextFloat() - rand.nextFloat()) * 0.1F), (double)(rand.nextFloat() * 0.05F), (double)((rand.nextFloat() - rand.nextFloat()) * 0.1F)));
				stack.damageItem(1, entity, e -> e.sendBreakAnimation(hand));
			}
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.FAIL;
	}
}
