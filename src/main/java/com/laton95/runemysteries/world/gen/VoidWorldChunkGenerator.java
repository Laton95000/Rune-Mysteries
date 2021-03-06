package com.laton95.runemysteries.world.gen;

import com.laton95.runemysteries.world.gen.feature.structure.temple.RuneTempleStructure;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;

public class VoidWorldChunkGenerator extends ChunkGenerator<GenerationSettings> {
	
	public VoidWorldChunkGenerator(IWorld p_i49954_1_, BiomeProvider p_i49954_2_, GenerationSettings p_i49954_3_) {
		super(p_i49954_1_, p_i49954_2_, p_i49954_3_);
	}
	
	@Override
	public void func_225551_a_(WorldGenRegion p_225551_1_, IChunk p_225551_2_) {
	
	}
	
	@Override
	public boolean hasStructure(Biome biomeIn, Structure<? extends IFeatureConfig> structureIn) {
		return structureIn instanceof RuneTempleStructure;
	}
	
	@Override
	public int getGroundHeight() {
		return 0;
	}
	
	@Override
	public void makeBase(IWorld iWorld, IChunk iChunk) {
	
	}
	
	@Override
	public int func_222529_a(int i, int i1, Heightmap.Type type) {
		return 0;
	}
}
