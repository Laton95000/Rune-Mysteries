package com.laton95.runemysteries.block;

import com.laton95.runemysteries.RuneMysteries;
import com.laton95.runemysteries.enums.EnumRuneType;
import com.laton95.runemysteries.reference.StringReference;
import com.laton95.runemysteries.util.TeleportHelper;
import com.laton95.runemysteries.util.WorldHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerChunkProvider;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class AltarEntranceBlock extends Block implements IWaterLoggable {
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	
	private static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 13.0D, 16.0D);
	
	private final EnumRuneType runeType;
	
	public AltarEntranceBlock(EnumRuneType runeType) {
		super(Properties.create(Material.ROCK).hardnessAndResistance(-1.0F, 3600000.0F));
		this.runeType = runeType;
		this.setDefaultState(stateContainer.getBaseState().with(WATERLOGGED, false));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(!world.isRemote) {
			if(player.getHeldItemMainhand().getItem() == runeType.getTalisman() || player.getHeldItemOffhand().getItem() == runeType.getTalisman()) {
				BlockPos ruinPos;
				if(player.dimension.isVanilla()) {
					ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, ((ServerChunkProvider) (world.getChunkProvider())).getChunkGenerator());
				}
				else {
					ruinPos = RuneMysteries.ruinManager.getRuinPosition(runeType, (ServerWorld) world);
				}
				if(ruinPos != null && !WorldHelper.isNearby(player.getPosition(), ruinPos, 5) && !player.isCreative()) {
					return ActionResultType.SUCCESS;
				}
				
				player.sendMessage(new TranslationTextComponent(StringReference.BlockInteraction.ALTAR_ENTER));
				
				BlockPos pos1 = RuneMysteries.ruinManager.getRuinPosition(runeType, (ServerWorld) world);
				BlockPos pos2 = runeType.getAltarNorthWestPoint();
				
				double x = pos1.getX() - player.getPosX();
				double y = pos.getY() - player.getPosY();
				double z = pos1.getZ() - player.getPosZ();
				
				TeleportHelper.teleportPlayer((ServerPlayerEntity) player, runeType.getTempleDimension(), pos2.getX() - x, Math.max(pos2.getY(), pos2.getY() - y), pos2.getZ() - z);
			}
			else {
				player.sendMessage(new TranslationTextComponent(StringReference.BlockInteraction.ALTAR_INTERACT));
			}
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		return getDefaultState().with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
	}
	
	@Override
	public IFluidState getFluidState(BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		double blockCenterX = pos.getX() + 0.5D;
		double blockCenterY = pos.getY() + 1D;
		double blockCenterZ = pos.getZ() + 0.5D;
		
		IParticleData particle;
		
		if(!runeType.isOurania()) {
			particle = runeType.getRuneColor();
		}
		else {
			particle = EnumRuneType.getRandomType(rand).getRuneColor();
		}
		
		float xSpeed = 0;
		float ySpeed = 0;
		float zSpeed = 0;
		
		double xOffset = rand.nextFloat() - 0.5f;
		double zOffset = rand.nextFloat() - 0.5f;
		
		world.addParticle(particle, blockCenterX + xOffset, blockCenterY, blockCenterZ + zOffset, xSpeed, ySpeed, zSpeed);
	}
}
