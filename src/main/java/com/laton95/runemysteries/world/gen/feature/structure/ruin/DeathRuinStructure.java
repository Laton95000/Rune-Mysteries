package com.laton95.runemysteries.world.gen.feature.structure.ruin;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class DeathRuinStructure extends RuinStructure {
	
	public static ResourceLocation DEATH_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/death_ruin");
	
	public static ResourceLocation DEATH_ROOM = new ResourceLocation(RuneMysteries.MOD_ID, "death_room");
	
	public DeathRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, EnumRuneType.DEATH);
	}
	
	@Override
	protected void addPieces(List<StructurePiece> components, TemplateManager templateManager, int chunkX, int chunkZ, Random rand) {
		BlockPos pos = new BlockPos(chunkX * 16, rand.nextInt(30) + 10, chunkZ * 16);
		UndergroundRuinPieces.addPieces(templateManager, pos, components, DEATH_RUIN, DEATH_ROOM, rune);
	}
}
