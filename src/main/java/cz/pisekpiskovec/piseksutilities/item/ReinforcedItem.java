
package cz.pisekpiskovec.piseksutilities.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import cz.pisekpiskovec.piseksutilities.block.ReinforcedWoodBlock;
import cz.pisekpiskovec.piseksutilities.block.ReinforcedStoneBlock;
import cz.pisekpiskovec.piseksutilities.block.ReinforcedRedstoneBlock;
import cz.pisekpiskovec.piseksutilities.block.ReinforcedGlowstoneBlock;
import cz.pisekpiskovec.piseksutilities.block.ReinforcedGlassBlock;
import cz.pisekpiskovec.piseksutilities.PiseksUtilitiesIiModElements;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@PiseksUtilitiesIiModElements.ModElement.Tag
public class ReinforcedItem extends PiseksUtilitiesIiModElements.ModElement {
	@ObjectHolder("piseks_utilities_ii:reinforced_helmet")
	public static final Item helmet = null;
	@ObjectHolder("piseks_utilities_ii:reinforced_chestplate")
	public static final Item body = null;
	@ObjectHolder("piseks_utilities_ii:reinforced_leggings")
	public static final Item legs = null;
	@ObjectHolder("piseks_utilities_ii:reinforced_boots")
	public static final Item boots = null;

	public ReinforcedItem(PiseksUtilitiesIiModElements instance) {
		super(instance, 60);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 1}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 1;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wood.place"));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(ReinforcedWoodBlock.block), new ItemStack(ReinforcedGlowstoneBlock.block),
						new ItemStack(ReinforcedRedstoneBlock.block), new ItemStack(ReinforcedGlassBlock.block),
						new ItemStack(ReinforcedStoneBlock.block));
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "reinforced";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.2f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.TOOLS)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedHead = new hard_hat().Head;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "piseks_utilities_ii:textures/entities/hard_hat.png";
			}
		}.setRegistryName("reinforced_helmet"));
	}

	// Made with Blockbench 4.8.3
	// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
	// Paste this class into your mod and generate all required imports
	public class hard_hat extends EntityModel<Entity> {
		private final ModelRenderer Head;
		private final ModelRenderer left_r1;

		public hard_hat() {
			textureWidth = 64;
			textureHeight = 64;
			Head = new ModelRenderer(this);
			Head.setRotationPoint(0.0F, 0.0F, 0.0F);
			Head.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
			Head.setTextureOffset(7, 7).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
			Head.setTextureOffset(1, 1).addBox(-5.0F, -8.0F, -5.0F, 1.0F, 3.0F, 10.0F, 0.0F, false);
			Head.setTextureOffset(5, 3).addBox(-4.0F, -5.0F, -8.0F, 8.0F, 0.0F, 3.0F, 0.0F, false);
			Head.setTextureOffset(4, 8).addBox(-0.5F, -9.5F, -3.0F, 1.0F, 0.5F, 7.0F, 0.0F, false);
			left_r1 = new ModelRenderer(this);
			left_r1.setRotationPoint(0.0F, 0.0F, 0.0F);
			Head.addChild(left_r1);
			setRotationAngle(left_r1, 0.0F, 3.1416F, 0.0F);
			left_r1.setTextureOffset(1, 1).addBox(-5.0F, -8.0F, -5.0F, 1.0F, 3.0F, 10.0F, 0.0F, false);
			left_r1.setTextureOffset(7, 7).addBox(-4.0F, -8.0F, -5.0F, 8.0F, 3.0F, 1.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			Head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.Head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.Head.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}

}
