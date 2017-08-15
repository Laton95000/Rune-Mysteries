package com.laton95.runemysteries.spells;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.laton95.runemysteries.init.ItemRegistry;
import com.laton95.runemysteries.item.ItemRune.EnumRuneType;
import com.laton95.runemysteries.reference.ModReference;
import com.laton95.runemysteries.reference.NamesReference;
import com.laton95.runemysteries.spells.Spell.SpellCost;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SnowballSpell extends Spell {
	private final static List<SpellCost> costs = ImmutableList.of(
			new SpellCost(ItemRegistry.RUNE, 2, EnumRuneType.WATER.ordinal()),
			new SpellCost(ItemRegistry.RUNE, 1, EnumRuneType.AIR.ordinal())
			);

	public SnowballSpell() {
		super(costs, 5, NamesReference.Spells.SNOWBALL_SPELL_NAME, NamesReference.Spells.SNOWBALL_SPELL_DESCRIPTION, new ResourceLocation(ModReference.MOD_ID, "textures/spells/gui/explosion.png"));
	}

	@Override
	public void fireSpell(World world, EntityPlayer player) {
		world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_SNOWBALL_THROW,
				SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.rand.nextFloat() * 0.4F + 0.8F));

		if (!world.isRemote) {
			EntitySnowball entitysnowball = new EntitySnowball(world, player);
			entitysnowball.setHeadingFromThrower(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
			world.spawnEntity(entitysnowball);
		}
	}

}
