package com.laton95.runemysteries.world.structureComponents;

import java.util.Random;

import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

public class ComponentSoulTemple extends WorldHelper.ModFeature {

	public ComponentSoulTemple() {
	}

	public ComponentSoulTemple(Random rand, int x, int z) {
		super(rand, x, 79, z, 32, 13, 32);
	}

	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		StructureBoundingBox structureboundingbox = this.getBoundingBox();
		BlockPos blockpos = new BlockPos(structureboundingbox.minX, structureboundingbox.minY,
				structureboundingbox.minZ);

		Template temple = WorldHelper.getTemplate(worldIn, "soul_temple");
		PlacementSettings settings = (new PlacementSettings()).setReplacedBlock(Blocks.STRUCTURE_VOID)
				.setBoundingBox(structureboundingbox);
		WorldHelper.loadStructure(blockpos, worldIn, temple, settings);
		return true;
	}
}