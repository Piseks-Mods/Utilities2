
package cz.pisekpiskovec.piseksutilities.block;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

import cz.pisekpiskovec.piseksutilities.itemgroup.PUiiMachinesItemGroup;
import cz.pisekpiskovec.piseksutilities.PiseksUtilitiesIiModElements;

@PiseksUtilitiesIiModElements.ModElement.Tag
public class SafeNetBlock extends PiseksUtilitiesIiModElements.ModElement {
	@ObjectHolder("piseks_utilities_ii:safe_net")
	public static final Block block = null;

	public SafeNetBlock(PiseksUtilitiesIiModElements instance) {
		super(instance, 235);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(PUiiMachinesItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.WOOL).sound(SoundType.CLOTH).hardnessAndResistance(1f, 1f).setLightLevel(s -> 0)
					.jumpFactor(1.2000000000000002f));
			setRegistryName("safe_net");
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 15;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}