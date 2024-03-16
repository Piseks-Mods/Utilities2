
package cz.pisekpiskovec.piseksutilities.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.Entity;

import cz.pisekpiskovec.piseksutilities.itemgroup.PUiiCombatItemGroup;
import cz.pisekpiskovec.piseksutilities.PiseksUtilitiesIiModElements;

@PiseksUtilitiesIiModElements.ModElement.Tag
public class SkeletonItem extends PiseksUtilitiesIiModElements.ModElement {
	@ObjectHolder("piseks_utilities_ii:skeleton_helmet")
	public static final Item helmet = null;
	@ObjectHolder("piseks_utilities_ii:skeleton_chestplate")
	public static final Item body = null;
	@ObjectHolder("piseks_utilities_ii:skeleton_leggings")
	public static final Item legs = null;
	@ObjectHolder("piseks_utilities_ii:skeleton_boots")
	public static final Item boots = null;

	public SkeletonItem(PiseksUtilitiesIiModElements instance) {
		super(instance, 306);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 3;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 3, 1}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 5;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.BONE_MEAL));
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "skeleton";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(PUiiCombatItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "piseks_utilities_ii:textures/models/armor/skeleton_armor__layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("skeleton_helmet"));
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(PUiiCombatItemGroup.tab)) {
			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "piseks_utilities_ii:textures/models/armor/skeleton_armor__layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1") + ".png";
			}
		}.setRegistryName("skeleton_chestplate"));
	}

}
