package com.laton95.runemysteries.world;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.laton95.runemysteries.reference.WorldGenReference;
import com.laton95.runemysteries.utility.AltarNBTHelper;
import com.laton95.runemysteries.utility.LogHelper;
import com.laton95.runemysteries.utility.ModConfig;
import com.laton95.runemysteries.utility.WorldHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class AltarTracker {
	private List<RuneAltar> overworldAltars = new ArrayList<>();
	private List<RuneAltar> netherAltars = new ArrayList<>();
	private List<RuneAltar> endAltars = new ArrayList<>();
	private float defaultAltarFlatnessTolerance = 0.8f;
	private int defaultAltarRadius = 4;
	public int warningFailureCount = 12;
	public int panicFailureCount = 81;
	public boolean overworldAltarsFound = false;
	public boolean netherAltarsFound = false;
	public boolean endAltarsFound = false;

	protected World world;
	protected AltarNBTHelper altarNBTHelper;

	public AltarTracker() {
		overworldAltars.add(new RuneAltar("air_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.airAltarBiomes, WorldGenReference.airAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("astral_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.astralAltarBiomes, WorldGenReference.astralAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("death_altar", defaultAltarRadius, 0f, WorldGenReference.deathAltarBiomes,
				WorldGenReference.deathAltarBiomesN, Type.UNDERGROUND, "death_altar_room", 1));
		overworldAltars.add(new RuneAltar("body_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.bodyAltarBiomes, WorldGenReference.bodyAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("blood_altar", defaultAltarRadius, 0f, WorldGenReference.bloodAltarBiomes,
				WorldGenReference.bloodAltarBiomesN, Type.UNDERGROUND, "blood_altar_room", 1));
		overworldAltars.add(new RuneAltar("mind_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.mindAltarBiomes, WorldGenReference.mindAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("earth_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.earthAltarBiomes, WorldGenReference.earthAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("water_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.waterAltarBiomes, WorldGenReference.waterAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("soul_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.soulAltarBiomes, WorldGenReference.soulAltarBiomesN, Type.SOUL, "soul_altar_room",
				0));
		overworldAltars.add(new RuneAltar("law_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.lawAltarBiomes, WorldGenReference.lawAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("fire_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.fireAltarBiomes, WorldGenReference.fireAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("nature_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.natureAltarBiomes, WorldGenReference.natureAltarBiomesN, Type.SURFACE));
		overworldAltars.add(new RuneAltar("ourania_altar", defaultAltarRadius, 0f, WorldGenReference.ouraniaAltarBiomes,
				WorldGenReference.ouraniaAltarBiomesN, Type.UNDERGROUND, "ourania_altar_room", 2));

		netherAltars.add(new RuneAltar("chaos_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.chaosAltarBiomes, WorldGenReference.chaosAltarBiomesN, Type.NETHER));

		endAltars.add(new RuneAltar("cosmic_altar", defaultAltarRadius, defaultAltarFlatnessTolerance,
				WorldGenReference.cosmicAltarBiomes, WorldGenReference.cosmicAltarBiomesN, Type.END));
	}

	public boolean inGenerationRange(ChunkPos pos, int dimID) {
		List<RuneAltar> list = new ArrayList<>();
		switch (dimID) {
		case 0:
			list = overworldAltars;
			break;
		case -1:
			list = netherAltars;
			break;
		case 1:
			list = endAltars;
			break;
		}
		for (RuneAltar altar : list) {
			if (WorldHelper.isNearby(pos, new ChunkPos(altar.getPosition()), altar.getPlacementRadius())) {
				return true;
			}
		}
		return false;
	}

	public RuneAltar getAltar(ChunkPos pos, int dimID) {
		List<RuneAltar> list = new ArrayList<>();
		switch (dimID) {
		case 0:
			list = overworldAltars;
			break;
		case -1:
			list = netherAltars;
			break;
		case 1:
			list = endAltars;
			break;
		}
		List<RuneAltar> nearbyAltars = new ArrayList<>();
		for (RuneAltar altar : list) {
			ChunkPos pos2 = new ChunkPos(altar.getPosition());
			if (WorldHelper.isNearby(pos, pos2, altar.getPlacementRadius())) {
				nearbyAltars.add(altar);
			}
		}
		RuneAltar altar = nearbyAltars.get(new Random().nextInt(nearbyAltars.size()));
		return altar;
	}

	public RuneAltar getAltar(String name) {
		for (RuneAltar altar : overworldAltars) {
			if (altar.getName().equals(name)) {
				return altar;
			}
		}
		for (RuneAltar altar : netherAltars) {
			if (altar.getName().equals(name)) {
				return altar;
			}
		}
		for (RuneAltar altar : endAltars) {
			if (altar.getName().equals(name)) {
				return altar;
			}
		}
		return null;
	}

	public void findOverworldLocations(World world) {
		this.world = world.getMinecraftServer().getWorld(0);
		altarNBTHelper = AltarNBTHelper.get(this.world);

		List<RuneAltar> outStandingAltars = new LinkedList<>();
		List<BiomeDictionary.Type> allowedBiomes = new LinkedList<>();
		Random random = new Random();

		if (!altarNBTHelper.overworldAltarsGenerated) {
			LogHelper.info("Finding overworld altar locations");
			outStandingAltars.addAll(overworldAltars);
			random.setSeed(world.getSeed() * 1984);
			for (RuneAltar altar : outStandingAltars) {
				allowedBiomes.addAll(altar.getBiomes());
			}
			findLocations(outStandingAltars, allowedBiomes, random, world);
			altarNBTHelper.overworldAltarsGenerated = true;
			altarNBTHelper.markDirty();
		} else {
			LogHelper.info("Loading overworld altar locations");
			for (RuneAltar runeAltar : overworldAltars) {
				runeAltar.updatePosition();
			}
		}
		overworldAltarsFound = true;
	}

	public void findNetherLocations(World world) {
		this.world = world.getMinecraftServer().getWorld(0);
		altarNBTHelper = AltarNBTHelper.get(this.world);

		List<RuneAltar> outStandingAltars = new LinkedList<>();
		List<BiomeDictionary.Type> allowedBiomes = new LinkedList<>();
		Random random = new Random();

		if (!altarNBTHelper.netherAltarsGenerated) {
			LogHelper.info("Finding nether altar locations");
			outStandingAltars.addAll(netherAltars);
			random.setSeed(world.getSeed() * 4331);
			for (RuneAltar altar : outStandingAltars) {
				allowedBiomes.addAll(altar.getBiomes());
			}
			findLocations(outStandingAltars, allowedBiomes, random, world);
			altarNBTHelper.netherAltarsGenerated = true;
			altarNBTHelper.markDirty();
		} else {
			LogHelper.info("Loading nether altar locations");
			for (RuneAltar runeAltar : netherAltars) {
				runeAltar.updatePosition();
			}
		}
		netherAltarsFound = true;
	}

	public void findEndLocations(World world) {
		this.world = world.getMinecraftServer().getWorld(0);
		altarNBTHelper = AltarNBTHelper.get(this.world);

		List<RuneAltar> outStandingAltars = new LinkedList<>();
		List<BiomeDictionary.Type> allowedBiomes = new LinkedList<>();
		Random random = new Random();

		if (!altarNBTHelper.endAltarsGenerated) {
			LogHelper.info("Finding end altar locations");
			outStandingAltars.addAll(endAltars);
			random.setSeed(world.getSeed() * 2849);
			for (RuneAltar altar : outStandingAltars) {
				allowedBiomes.addAll(altar.getBiomes());
			}
			findLocations(outStandingAltars, allowedBiomes, random, world);
			altarNBTHelper.endAltarsGenerated = true;
			altarNBTHelper.markDirty();
		} else {
			LogHelper.info("Loading end altar locations");
			for (RuneAltar runeAltar : endAltars) {
				runeAltar.updatePosition();
			}
		}
		endAltarsFound = true;
	}

	public void findLocations(List<RuneAltar> outStandingAltars, List<BiomeDictionary.Type> allowedBiomes,
			Random random, World world) {
		while (!outStandingAltars.isEmpty()) {
			List<BiomeDictionary.Type> outStandingBiomes = new LinkedList<>();
			for (RuneAltar altar : outStandingAltars) {
				outStandingBiomes.addAll(altar.getBiomes());
				altarNBTHelper.placedMap.put(altar.getName(), false);
			}

			BlockPos pos = findBiomePosition(outStandingBiomes, random, ModConfig.world.rune_altars.runeAltarTries,
					ModConfig.world.rune_altars.maxRuneAltarRange / 16, ModConfig.world.rune_altars.minRuneAltarRange / 16);

			if (pos != null) {
				for (RuneAltar altar : outStandingAltars) {
					if (altar.isBiomeViable(world.getBiome(pos))) {
						altar.setPosition(pos);
						LogHelper.info(altar);
						outStandingAltars.remove(altar);
						break;
					}
				}
			} else {
				for (RuneAltar altar : outStandingAltars) {
					pos = findBiomePosition(allowedBiomes, random, ModConfig.world.rune_altars.runeAltarTries,
							ModConfig.world.rune_altars.maxRuneAltarRange / 16, ModConfig.world.rune_altars.minRuneAltarRange / 16);
					if (pos != null) {
						altar.setPosition(pos);
						LogHelper.info(altar);
						outStandingAltars.remove(altar);
						altar.setBiomeDependant(false);
						break;
					} else {
						pos = findBiomePosition(new LinkedList<BiomeDictionary.Type>(), random,
								ModConfig.world.rune_altars.runeAltarTries, ModConfig.world.rune_altars.maxRuneAltarRange / 16,
								ModConfig.world.rune_altars.minRuneAltarRange / 16);
						altar.setPosition(pos);
						LogHelper.info(altar);
						outStandingAltars.remove(altar);
						altar.setBiomeDependant(false);
						break;
					}
				}
			}
		}
	}

	protected BlockPos findBiomePosition(List<BiomeDictionary.Type> biomes, Random rand, int attempts, int radius,
			int min) {
		for (int i = 0; i < attempts; i++) {
			int chunkX = (rand.nextInt(radius - min) + min) * (rand.nextBoolean() ? 1 : -1);
			int chunkZ = (rand.nextInt(radius - min) + min) * (rand.nextBoolean() ? 1 : -1);

			ChunkPos pos = new ChunkPos(chunkX, chunkZ);
			BlockPos pos2 = new BlockPos(pos.getXStart(), 0, pos.getZStart());
			Biome biome = world.getBiome(pos2);
			if (!biomes.isEmpty()) {
				for (BiomeDictionary.Type type : biomes) {
					if (BiomeDictionary.hasType(biome, type)) {
						return pos2;
					}
				}
			} else
				return pos2;
		}
		return null;
	}

	@Override
	public String toString() {
		int placed = 0;
		int total = 0;
		for (RuneAltar altar : overworldAltars) {
			total++;
			if (altar.isPlaced()) {
				placed++;
			}
		}

		return placed + "/" + total;
	}

	public class RuneAltar {
		private final String name;
		private final String room;
		private boolean placed;
		private boolean biomeDependant;
		private int placementRadius;
		private Float flatnessTolerance;
		private int failureCount;
		private BlockPos position;
		private List<BiomeDictionary.Type> biomes;
		private List<BiomeDictionary.Type> biomesN;
		private Type type;
		private int yOffset;

		public RuneAltar(String name, int placementRadius, Float flatnessTolerance, List<BiomeDictionary.Type> biomes,
				List<BiomeDictionary.Type> biomesN, Type type) {
			this.name = name;
			this.placed = false;
			this.biomeDependant = true;
			this.placementRadius = placementRadius;
			this.flatnessTolerance = flatnessTolerance;
			this.failureCount = 0;
			this.biomes = biomes;
			this.type = type;
			this.room = null;
			this.biomesN = biomesN;
			this.yOffset = 0;
		}

		public RuneAltar(String name, int placementRadius, Float flatnessTolerance, List<BiomeDictionary.Type> biomes,
				List<BiomeDictionary.Type> biomesN, Type type, String room, int yOffset) {
			this.name = name;
			this.placed = false;
			this.biomeDependant = true;
			this.placementRadius = placementRadius;
			this.flatnessTolerance = flatnessTolerance;
			this.failureCount = 0;
			this.biomes = biomes;
			this.type = type;
			this.room = room;
			this.biomesN = biomesN;
			this.yOffset = yOffset;
		}

		public boolean isPlaced() {
			return placed;
		}

		public void setPlaced(boolean placed) {
			this.placed = placed;
			if (placed) {
				altarNBTHelper.placedMap.put(name, true);
				altarNBTHelper.markDirty();
			}
		}

		public boolean isBiomeDependant() {
			return biomeDependant;
		}

		public void setBiomeDependant(boolean biomeDependant) {
			this.biomeDependant = biomeDependant;
		}

		public int getPlacementRadius() {
			return placementRadius;
		}

		public void setPlacementRadius(int placementRadius) {
			this.placementRadius = placementRadius;
		}

		public void incrementPlacementRadius(int radius) {
			this.placementRadius += radius;
		}

		public Float getFlatnessTolerance() {
			return flatnessTolerance;
		}

		public void setFlatnessTolerance(Float flatnessTolerance) {
			this.flatnessTolerance = flatnessTolerance;
		}

		public void decrementFlatnessTolerance(Float tolerance) {
			this.flatnessTolerance -= tolerance;
		}

		public int getFailureCount() {
			return failureCount;
		}

		public void incrementFailureCount(int count) {
			this.failureCount += count;
		}

		public String getName() {
			return name;
		}

		public String getRoom() {
			return room;
		}

		public int getYOffset() {
			return yOffset;
		}

		public BlockPos getPosition() {
			return position;
		}

		public void setPosition(BlockPos position) {
			this.position = position;
			altarNBTHelper.posMap.put(name, position);
			altarNBTHelper.markDirty();
		}

		public void updatePosition() {
			this.position = altarNBTHelper.posMap.get(name);
			this.placed = altarNBTHelper.placedMap.get(name);
		}

		public List<BiomeDictionary.Type> getBiomes() {
			return biomes;
		}

		public boolean isBiomeViable(Biome biome) {
			if (biomes.isEmpty()) {
				for (BiomeDictionary.Type typeN : biomesN) {
					if (BiomeDictionary.hasType(biome, typeN)) {
						return false;
					}
				}
				return true;
			}

			for (BiomeDictionary.Type type : biomes) {
				if (!biomesN.isEmpty()) {
					for (BiomeDictionary.Type typeN : biomesN) {
						if (BiomeDictionary.hasType(biome, type) && !BiomeDictionary.hasType(biome, typeN)) {
							return true;
						}
					}
				} else if (BiomeDictionary.hasType(biome, type)) {
					return true;
				}

			}
			return false;
		}

		public Type getType() {
			return this.type;
		}

		@Override
		public String toString() {
			return name + " at " + position;
		}

	}

	public enum Type {
		SURFACE, UNDERGROUND, SOUL, NETHER, END
	}
}