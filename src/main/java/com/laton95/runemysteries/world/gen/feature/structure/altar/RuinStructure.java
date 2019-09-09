package com.laton95.runemysteries.world.gen.feature.structure.altar;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.mojang.datafixers.Dynamic;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public abstract class RuinStructure extends Structure<NoFeatureConfig> {
	
	protected final EnumRuneType rune;
	
	public RuinStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> function, EnumRuneType rune) {
		super(function);
		this.rune = rune;
	}
	
	@Override
	public boolean hasStartAt(ChunkGenerator<?> chunkGenerator, Random random, int chunkPosX, int chunkPosZ) {
		return RuneMysteries.ruinManager.isRuinInChunk(rune, chunkGenerator, new ChunkPos(chunkPosX, chunkPosZ));
		
	}
	
	@Nullable
	@Override
	public BlockPos findNearest(World world, ChunkGenerator<? extends GenerationSettings> chunkGenerator, BlockPos pos, int chunkPosX, boolean chunkPosZ) {
		return RuneMysteries.ruinManager.getRuinPosition(rune, chunkGenerator);
	}
	
	@Override
	public String getStructureName() {
		return "runemysteries:" + rune.name().toLowerCase() + "_ruin";
	}
	
	@Override
	public int getSize() {
		return 1;
	}
	
	protected abstract void addPieces(List<StructurePiece> components, TemplateManager templateManager, int chunkX, int chunkZ, Random rand);
	
	@Override
	public IStartFactory getStartFactory() {
		return AirRuinStructure.Start::new;
	}
	
	public class Start extends StructureStart {
		
		public Start(Structure<?> structure, int chunkX, int chunkZ, Biome biome, MutableBoundingBox boundingBox, int reference, long seed) {
			super(structure, chunkX, chunkZ, biome, boundingBox, reference, seed);
		}
		
		public void init(ChunkGenerator<?> generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome) {
			addPieces(this.components, templateManager, chunkX, chunkZ, rand);
			this.recalculateStructureSize();
		}
	}
}
