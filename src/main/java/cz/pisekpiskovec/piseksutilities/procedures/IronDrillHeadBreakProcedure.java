package cz.pisekpiskovec.piseksutilities.procedures;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Map;

import cz.pisekpiskovec.piseksutilities.PiseksUtilitiesIiMod;

public class IronDrillHeadBreakProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				PiseksUtilitiesIiMod.LOGGER.warn("Failed to load dependency world for procedure IronDrillHeadBreak!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				PiseksUtilitiesIiMod.LOGGER.warn("Failed to load dependency x for procedure IronDrillHeadBreak!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				PiseksUtilitiesIiMod.LOGGER.warn("Failed to load dependency y for procedure IronDrillHeadBreak!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				PiseksUtilitiesIiMod.LOGGER.warn("Failed to load dependency z for procedure IronDrillHeadBreak!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		if (((world instanceof World) ? ((World) world).isBlockPowered(new BlockPos(x, y, z)) : false) && !world.isAirBlock(new BlockPos(x, y + 1, z))
				&& world.getBlockState(new BlockPos(x, y, z)).getHarvestLevel() <= 2) {
			new Object() {
				private int ticks = 0;
				private float waitTicks;
				private IWorld world;

				public void start(IWorld world, int waitTicks) {
					this.waitTicks = waitTicks;
					MinecraftForge.EVENT_BUS.register(this);
					this.world = world;
				}

				@SubscribeEvent
				public void tick(TickEvent.ServerTickEvent event) {
					if (event.phase == TickEvent.Phase.END) {
						this.ticks += 1;
						if (this.ticks >= this.waitTicks)
							run();
					}
				}

				private void run() {
					world.playEvent(2001, new BlockPos(x, y, z), Block.getStateId((world.getBlockState(new BlockPos(x, y, z)))));
					if (world instanceof World) {
						Block.spawnDrops(world.getBlockState(new BlockPos(x, y + 1, z)), (World) world, new BlockPos(x, y, z));
						world.destroyBlock(new BlockPos(x, y + 1, z), false);
					}
					if (!world.isRemote()) {
						BlockPos _bp = new BlockPos(x, y, z);
						TileEntity _tileEntity = world.getTileEntity(_bp);
						BlockState _bs = world.getBlockState(_bp);
						if (_tileEntity != null)
							_tileEntity.getTileData().putDouble("drillDurability", (new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos(x, y, z), "drillDurability") + 1));
						if (world instanceof World)
							((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
					}
					if (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y, z), "drillDurability") == 255) {
						world.destroyBlock(new BlockPos(x, y + 0, z), false);
					}
					MinecraftForge.EVENT_BUS.unregister(this);
				}
			}.start(world, (int) ((world.getBlockState(new BlockPos(x, y + 1, z)).getBlockHardness(world, new BlockPos(x, y + 1, z)) / 6) * 20));
		}
	}
}
