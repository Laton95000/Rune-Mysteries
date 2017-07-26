package com.laton95.runemysteries.init;

import com.laton95.runemysteries.utility.ModConfig;
import com.laton95.runemysteries.world.dimensionProviders.AirDimension;
import com.laton95.runemysteries.world.dimensionProviders.BloodDimension;
import com.laton95.runemysteries.world.dimensionProviders.BodyDimension;
import com.laton95.runemysteries.world.dimensionProviders.ChaosDimension;
import com.laton95.runemysteries.world.dimensionProviders.CosmicDimension;
import com.laton95.runemysteries.world.dimensionProviders.DeathDimension;
import com.laton95.runemysteries.world.dimensionProviders.EarthDimension;
import com.laton95.runemysteries.world.dimensionProviders.FireDimension;
import com.laton95.runemysteries.world.dimensionProviders.LawDimension;
import com.laton95.runemysteries.world.dimensionProviders.MindDimension;
import com.laton95.runemysteries.world.dimensionProviders.NatureDimension;
import com.laton95.runemysteries.world.dimensionProviders.SoulDimension;
import com.laton95.runemysteries.world.dimensionProviders.WaterDimension;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class DimensionRegistry {
	public static int airDimID;
	public static DimensionType AIR;

	public static int bloodDimID;
	public static DimensionType BLOOD;

	public static int bodyDimID;
	public static DimensionType BODY;
	
	public static int chaosDimID;
	public static DimensionType CHAOS;
	
	public static int cosmicDimID;
	public static DimensionType COSMIC;

	public static int deathDimID;
	public static DimensionType DEATH;

	public static int earthDimID;
	public static DimensionType EARTH;
	
	public static int fireDimID;
	public static DimensionType FIRE;
	
	public static int lawDimID;
	public static DimensionType LAW;
	
	public static int mindDimID;
	public static DimensionType MIND;
	
	public static int natureDimID;
	public static DimensionType NATURE;
	
	public static int soulDimID;
	public static DimensionType SOUL;
	
	public static int waterDimID;
	public static DimensionType WATER;

	public static void registerDimensions() {
		airDimID = ModConfig.dimensions.airTempleDimID;
		AIR = DimensionType.register("air_temple", "_air", airDimID, AirDimension.class, false);

		bloodDimID = ModConfig.dimensions.bloodTempleDimID;;
		BLOOD = DimensionType.register("blood_temple", "_blood", bloodDimID, BloodDimension.class, false);

		bodyDimID = ModConfig.dimensions.bodyTempleDimID;
		BODY = DimensionType.register("body_temple", "_body", bodyDimID, BodyDimension.class, false);
		
		chaosDimID = ModConfig.dimensions.chaosTempleDimID;;
		CHAOS = DimensionType.register("chaos_temple", "_chaos", chaosDimID, ChaosDimension.class, false);
		
		cosmicDimID = ModConfig.dimensions.cosmicTempleDimID;
		COSMIC = DimensionType.register("cosmic_temple", "_cosmic", cosmicDimID, CosmicDimension.class, false);

		deathDimID = ModConfig.dimensions.deathTempleDimID;
		DEATH = DimensionType.register("death_temple", "_death", deathDimID, DeathDimension.class, false);

		earthDimID = ModConfig.dimensions.earthTempleDimID;
		EARTH = DimensionType.register("earth_temple", "_earth", earthDimID, EarthDimension.class, false);
		
		fireDimID = ModConfig.dimensions.fireTempleDimID;
		FIRE = DimensionType.register("fire_temple", "_fire", fireDimID, FireDimension.class, false);
		
		lawDimID = ModConfig.dimensions.lawTempleDimID;
		LAW = DimensionType.register("law_temple", "_law", lawDimID, LawDimension.class, false);
		
		mindDimID = ModConfig.dimensions.mindTempleDimID;
		MIND = DimensionType.register("mind_temple", "_mind", mindDimID, MindDimension.class, false);
		
		natureDimID = ModConfig.dimensions.natureTempleDimID;
		NATURE = DimensionType.register("nature_temple", "_nature", natureDimID, NatureDimension.class, false);
		
		soulDimID = ModConfig.dimensions.soulTempleDimID;
		SOUL = DimensionType.register("soul_temple", "soul", soulDimID, SoulDimension.class, false);
		
		waterDimID = ModConfig.dimensions.waterTempleDimID;
		WATER = DimensionType.register("water_temple", "_water", waterDimID, WaterDimension.class, false);

		DimensionManager.registerDimension(airDimID, AIR);
		DimensionManager.registerDimension(bloodDimID, BLOOD);
		DimensionManager.registerDimension(bodyDimID, BODY);
		DimensionManager.registerDimension(chaosDimID, CHAOS);
		DimensionManager.registerDimension(cosmicDimID, COSMIC);
		DimensionManager.registerDimension(deathDimID, DEATH);
		DimensionManager.registerDimension(earthDimID, EARTH);
		DimensionManager.registerDimension(fireDimID, FIRE);
		DimensionManager.registerDimension(lawDimID, LAW);
		DimensionManager.registerDimension(mindDimID, MIND);
		DimensionManager.registerDimension(natureDimID, NATURE);
		DimensionManager.registerDimension(soulDimID, SOUL);
		DimensionManager.registerDimension(waterDimID, WATER);
	}
}