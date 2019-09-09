package com.laton95.runemysteries.world.gen.feature.structure.altar;

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

public class NatureRuinStructure extends RuinStructure {
	
	public static final ResourceLocation NATURE_RUIN = new ResourceLocation(RuneMysteries.MOD_ID, "ruin/nature_ruin");
	
	public NatureRuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function) {
		super(function, EnumRuneType.NATURE);
	}
	
	@Override
	protected void addPieces(List<StructurePiece> components, TemplateManager templateManager, int chunkX, int chunkZ, Random rand) {
		BlockPos pos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
		SurfaceRuinPieces.addPieces(templateManager, pos, components, NATURE_RUIN, SurfaceRuinPieces.DIRT_ISLAND, rune);
	}
}
