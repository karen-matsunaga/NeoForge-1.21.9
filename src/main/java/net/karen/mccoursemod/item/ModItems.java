package net.karen.mccoursemod.item;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.datagen.ModEquipmentAssetProvider;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.item.custom.*;
import net.karen.mccoursemod.sound.ModSounds;
import net.karen.mccoursemod.trim.ModTrimMaterials;
import net.karen.mccoursemod.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Unit;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.*;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import static net.karen.mccoursemod.item.custom.OresSmithingTemplateItem.*;
import static net.karen.mccoursemod.util.ChatUtils.*;

public class ModItems {
    // Registry all custom ITEMS
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MccourseMod.MOD_ID);

    // ** CUSTOM items **
    // ** CUSTOM ore items **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH = trimMaterialItem("bismuth", ModTrimMaterials.BISMUTH, bismuthColor);

    public static final DeferredItem<Item> RAW_BISMUTH =
           customItem("raw_bismuth", Item::new, props -> props, bismuthColor);

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE =
           trimMaterialItem("alexandrite", ModTrimMaterials.ALEXANDRITE, alexandriteColor);

    public static final DeferredItem<Item> RAW_ALEXANDRITE =
           customItem("raw_alexandrite", Item::new, props -> props, alexandriteColor);

    // PINK
    public static final DeferredItem<Item> PINK = trimMaterialItem("pink", ModTrimMaterials.PINK, pinkColor);

    // ** CUSTOM advanced items **
    public static final DeferredItem<Item> CHISEL =
           editItem("chisel", props -> new ChiselItem(props.durability(32)), chiselLoreColor);

    public static final DeferredItem<Item> RESTORE =
           customItem("restore", RestoreItem::new, Properties::fireResistant, restoreLoreColor);

    public static final DeferredItem<Item> FARMER =
           customItem("farmer", FarmerItem::new, Properties::fireResistant, farmerLoreColor);

    public static final DeferredItem<Item> CATTAIL =
           customItem("cattail", ModWaxingItem::new, Properties::fireResistant, cattailLoreColor);

    // ** CUSTOM Foods **
    public static final DeferredItem<Item> RADISH =
           foodItem("radish", 3, 0.25F, MobEffects.HEALTH_BOOST, 400, 0.35F, greenColor);

    public static final DeferredItem<Item> KOHLRABI =
           foodItem("kohlrabi", 3, 0.25F, MobEffects.SPEED, 200, 0.1F, darkGreenColor);

    public static final DeferredItem<Item> COFFEE =
           foodItem("coffee", 5, 0.1F, MobEffects.NIGHT_VISION, 600, 0.5F, whiteColor);

    // ** CUSTOM fuels (Custom FURNACE) **
    public static final DeferredItem<Item> FROSTFIRE_ICE = fuelItem("frostfire_ice", 800);

    public static final DeferredItem<Item> STARLIGHT_ASHES = fuelItem("starlight_ashes", 1200);

    public static final DeferredItem<Item> PEAT_BRICK = fuelItem("peat_brick", 200);

    // ** CUSTOM armors (Helmet, Chestplate, Leggings and Boots) **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH_HELMET =
           helmetArmor("bismuth_helmet", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, bismuthColor);

    public static final DeferredItem<Item> BISMUTH_CHESTPLATE =
           chestplateArmor("bismuth_chestplate", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, bismuthColor);

    public static final DeferredItem<Item> BISMUTH_LEGGINGS =
           leggingsArmor("bismuth_leggings", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, bismuthColor);

    public static final DeferredItem<Item> BISMUTH_BOOTS =
           bootsArmor("bismuth_boots", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, bismuthColor);

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE_HELMET =
           helmetArmor("alexandrite_helmet", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, alexandriteColor);

    public static final DeferredItem<Item> ALEXANDRITE_CHESTPLATE =
           chestplateArmor("alexandrite_chestplate", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, alexandriteColor);

    public static final DeferredItem<Item> ALEXANDRITE_LEGGINGS =
           leggingsArmor("alexandrite_leggings", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, alexandriteColor);

    public static final DeferredItem<Item> ALEXANDRITE_BOOTS =
           bootsArmor("alexandrite_boots", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, alexandriteColor);

    // PINK
    public static final DeferredItem<Item> PINK_HELMET =
           helmetArmor("pink_helmet", ModArmorMaterials.PINK_ARMOR_MATERIAL, pinkColor);

    public static final DeferredItem<Item> PINK_CHESTPLATE =
           chestplateArmor("pink_chestplate", ModArmorMaterials.PINK_ARMOR_MATERIAL, pinkColor);

    public static final DeferredItem<Item> PINK_LEGGINGS =
           leggingsArmor("pink_leggings", ModArmorMaterials.PINK_ARMOR_MATERIAL, pinkColor);

    public static final DeferredItem<Item> PINK_BOOTS =
           bootsArmor("pink_boots", ModArmorMaterials.PINK_ARMOR_MATERIAL, pinkColor);

    // COPPER
    public static final DeferredItem<Item> ULTRA_COPPER_HELMET =
           helmetArmor("ultra_copper_helmet", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperLoreColor);

    public static final DeferredItem<Item> ULTRA_COPPER_CHESTPLATE =
           chestplateArmor("ultra_copper_chestplate", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperLoreColor);

    public static final DeferredItem<Item> ULTRA_COPPER_LEGGINGS =
           leggingsArmor("ultra_copper_leggings", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperLoreColor);

    public static final DeferredItem<Item> ULTRA_COPPER_BOOTS =
           bootsArmor("ultra_copper_boots", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperLoreColor);

    // LAPIS LAZULI
    public static final DeferredItem<Item> LAPIS_LAZULI_HELMET =
           helmetArmor("lapis_lazuli_helmet", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_CHESTPLATE =
           chestplateArmor("lapis_lazuli_chestplate", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_LEGGINGS =
           leggingsArmor("lapis_lazuli_leggings", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_BOOTS =
           bootsArmor("lapis_lazuli_boots", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisLoreColor);

    // REDSTONE
    public static final DeferredItem<Item> REDSTONE_HELMET =
           helmetArmor("redstone_helmet", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneLoreColor);

    public static final DeferredItem<Item> REDSTONE_CHESTPLATE =
           chestplateArmor("redstone_chestplate", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneLoreColor);

    public static final DeferredItem<Item> REDSTONE_LEGGINGS =
           leggingsArmor("redstone_leggings", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneLoreColor);

    public static final DeferredItem<Item> REDSTONE_BOOTS =
           bootsArmor("redstone_boots", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneLoreColor);

    // ** CUSTOM Horse armor **
    public static final DeferredItem<Item> BISMUTH_HORSE_ARMOR =
           horseArmorItem("bismuth_horse_armor", ModArmorMaterials.BISMUTH_ARMOR_MATERIAL, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_HORSE_ARMOR =
           horseArmorItem("alexandrite_horse_armor", ModArmorMaterials.ALEXANDRITE_ARMOR_MATERIAL, alexandriteColor);

    // ** CUSTOM tools (SWORD, PICKAXE, SHOVEL, AXE, HOE, HAMMER, PAXEL, BOW, etc.) **
    // ** CUSTOM Bow tools **
    public static final DeferredItem<Item> KAUPEN_BOW =
           bowItem("kaupen_bow", ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> MINER_BOW =
           hammerBowItem("miner_bow", ModTags.Items.BISMUTH_TOOL_MATERIALS, 1, 2, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_BOW =
           bowItem("alexandrite_bow", ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    // ** CUSTOM Fishing Rod tools **
    public static final DeferredItem<Item> MCCOURSE_MOD_FISHING_ROD =
           editItem("mccourse_mod_fishing_rod", properties ->
                    new FishingRodItem(properties.fireResistant().stacksTo(1)) {
                         // DEFAULT METHOD - Appears on name item
                         @Override
                         public @NotNull Component getName(@NotNull ItemStack stack) {
                             return componentTranslatableIntColor(this.getDescriptionId(), fishingRodLoreColor);
                         }
                    }, fishingRodLoreColor);

    // ** CUSTOM Shield tools **
    public static final DeferredItem<Item> ALEXANDRITE_SHIELD =
           shieldItem("alexandrite_shield", ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    // ** CUSTOM Paxel tools **
    public static final DeferredItem<Item> BISMUTH_PAXEL =
           paxelItem("bismuth_paxel", ModToolMaterials.BISMUTH, 1F, -2.8F,
                     ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_PAXEL =
           paxelItem("alexandrite_paxel", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                     ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    public static final DeferredItem<Item> PINK_PAXEL =
           paxelItem("pink_paxel", ModToolMaterials.PINK, 1.0F, 2.0F,
                     ModTags.Items.PINK_TOOL_MATERIALS, pinkColor);

    public static final DeferredItem<Item> COPPER_PAXEL =
           paxelItem("copper_paxel", ModToolMaterials.COPPER, 1.0F, 2.5F,
                     ModTags.Items.COPPER_TOOL_MATERIALS, copperLoreColor);

    public static final DeferredItem<Item> DIAMOND_PAXEL =
           paxelItem("diamond_paxel", ToolMaterial.DIAMOND, 1.0F, 4.5F,
                     ItemTags.DIAMOND_TOOL_MATERIALS, diamondLoreColor);

    public static final DeferredItem<Item> GOLD_PAXEL =
           paxelItem("gold_paxel", ToolMaterial.GOLD, 1.0F, 4.0F,
                     ItemTags.GOLD_TOOL_MATERIALS, goldLoreColor);

    public static final DeferredItem<Item> IRON_PAXEL =
           paxelItem("iron_paxel", ToolMaterial.IRON, 1.0F, 3.0F,
                     ItemTags.IRON_TOOL_MATERIALS, ironLoreColor);

    public static final DeferredItem<Item> STONE_PAXEL =
           paxelItem("stone_paxel", ToolMaterial.STONE, 1.0F, 1.5F,
                     ItemTags.STONE_TOOL_MATERIALS, stoneLoreColor);

    public static final DeferredItem<Item> WOODEN_PAXEL =
           paxelItem("wooden_paxel", ToolMaterial.WOOD, 1.0F, 1.0F,
                     ItemTags.WOODEN_TOOL_MATERIALS, woodenLoreColor);

    public static final DeferredItem<Item> NETHERITE_PAXEL =
           paxelItem("netherite_paxel", ToolMaterial.NETHERITE, 1.0F, 5.0F,
                     ItemTags.NETHERITE_TOOL_MATERIALS, netheriteLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_PAXEL =
           paxelItem("lapis_lazuli_paxel", ModToolMaterials.LAPIS_LAZULI, 1.0F, 3.5F,
                     ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_PAXEL =
           paxelItem("redstone_paxel", ModToolMaterials.REDSTONE, 1.0F, 4.5F,
                     ModTags.Items.REDSTONE_TOOL_MATERIALS, redstoneLoreColor);

    // ** CUSTOM Hammer tools **
    public static final DeferredItem<Item> BISMUTH_HAMMER =
           hammerItem("bismuth_hammer", ModToolMaterials.BISMUTH, 7F, -3.5F,
                      ModTags.Items.BISMUTH_TOOL_MATERIALS, 2, bismuthColor, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_HAMMER =
           hammerItem("alexandrite_hammer", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                      ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 2, alexandriteColor, alexandriteColor);

    public static final DeferredItem<Item> PINK_HAMMER =
           hammerItem("pink_hammer", ModToolMaterials.PINK, 2.0F, 2.0F,
                      ModTags.Items.PINK_TOOL_MATERIALS, 3, pinkColor, pinkColor);

    public static final DeferredItem<Item> COPPER_HAMMER =
           hammerItem("copper_hammer", ModToolMaterials.COPPER, 2.0F, 2.5F,
                      ModTags.Items.COPPER_TOOL_MATERIALS, 2, copperLoreColor, copperLoreColor);

    public static final DeferredItem<Item> DIAMOND_HAMMER =
           hammerItem("diamond_hammer", ToolMaterial.DIAMOND, 2.0F, 4.5F,
                      ItemTags.DIAMOND_TOOL_MATERIALS, 3, diamondLoreColor, diamondLoreColor);

    public static final DeferredItem<Item> GOLD_HAMMER =
           hammerItem("gold_hammer", ToolMaterial.GOLD, 2.0F, 4.0F,
                      ItemTags.GOLD_TOOL_MATERIALS, 2, goldLoreColor, goldLoreColor);

    public static final DeferredItem<Item> IRON_HAMMER =
           hammerItem("iron_hammer", ToolMaterial.IRON, 2.0F, 3.0F,
                      ItemTags.IRON_TOOL_MATERIALS, 2, ironLoreColor, ironLoreColor);

    public static final DeferredItem<Item> STONE_HAMMER =
           hammerItem("stone_hammer", ToolMaterial.STONE, 2.0F, 1.5F,
                      ItemTags.STONE_TOOL_MATERIALS, 1, stoneLoreColor, stoneLoreColor);

    public static final DeferredItem<Item> WOODEN_HAMMER =
           hammerItem("wooden_hammer", ToolMaterial.WOOD, 2.0F, 1.0F,
                      ItemTags.WOODEN_TOOL_MATERIALS, 1, woodenLoreColor, woodenLoreColor);

    public static final DeferredItem<Item> NETHERITE_HAMMER =
           hammerItem("netherite_hammer", ToolMaterial.NETHERITE, 2.0F, 5.0F,
                      ItemTags.NETHERITE_TOOL_MATERIALS, 5, netheriteLoreColor, netheriteLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_HAMMER =
           hammerItem("lapis_lazuli_hammer", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                      ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, 4, lapisLoreColor, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_HAMMER =
           hammerItem("redstone_hammer", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                      ModTags.Items.REDSTONE_TOOL_MATERIALS, 4, redstoneLoreColor, redstoneLoreColor);

    // ** CUSTOM Shovel tools **
    public static final DeferredItem<Item> BISMUTH_SHOVEL =
           shovelItem("bismuth_shovel", ModToolMaterials.BISMUTH, 1.5F, -3.0F,
                      ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_SHOVEL =
           shovelItem("alexandrite_shovel", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                      ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    public static final DeferredItem<Item> PINK_SHOVEL =
           shovelItem("pink_shovel", ModToolMaterials.PINK, 2.0F, 2.0F,
                      ModTags.Items.PINK_TOOL_MATERIALS, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_SHOVEL =
           shovelItem("ultra_copper_shovel", ModToolMaterials.COPPER, 2.0F, 2.5F,
                      ModTags.Items.COPPER_TOOL_MATERIALS, copperLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_SHOVEL =
           shovelItem("lapis_lazuli_shovel", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                      ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_SHOVEL =
           shovelItem("redstone_shovel", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                      ModTags.Items.REDSTONE_TOOL_MATERIALS, redstoneLoreColor);

    // ** CUSTOM Axe tools **
    public static final DeferredItem<Item> BISMUTH_AXE =
           axeItem("bismuth_axe", ModToolMaterials.BISMUTH, 6.0F, -3.2F,
                   ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_AXE =
           axeItem("alexandrite_axe", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                   ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    public static final DeferredItem<Item> PINK_AXE =
           axeItem("pink_axe", ModToolMaterials.PINK, 2.0F, 2.0F,
                   ModTags.Items.PINK_TOOL_MATERIALS, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_AXE =
           axeItem("ultra_copper_axe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                   ModTags.Items.COPPER_TOOL_MATERIALS, copperLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_AXE =
           axeItem("lapis_lazuli_axe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                   ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_AXE =
           axeItem("redstone_axe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                   ModTags.Items.REDSTONE_TOOL_MATERIALS, redstoneLoreColor);

    // ** CUSTOM hoe **
    public static final DeferredItem<Item> BISMUTH_HOE =
           hoeItem("bismuth_hoe", ModToolMaterials.BISMUTH, 0.0F, -3.0F,
                   ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_HOE =
           hoeItem("alexandrite_hoe", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                   ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    public static final DeferredItem<Item> PINK_HOE =
           hoeItem("pink_hoe", ModToolMaterials.PINK, 2.0F, 2.0F,
                   ModTags.Items.PINK_TOOL_MATERIALS, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_HOE =
           hoeItem("ultra_copper_hoe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                   ModTags.Items.COPPER_TOOL_MATERIALS, copperLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_HOE =
           hoeItem("lapis_lazuli_hoe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                   ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_HOE =
           hoeItem("redstone_hoe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                   ModTags.Items.REDSTONE_TOOL_MATERIALS, redstoneLoreColor);

    // ** CUSTOM pickaxe **
    public static final DeferredItem<Item> BISMUTH_PICKAXE =
           pickaxeItem("bismuth_pickaxe", ModToolMaterials.BISMUTH, 1.0F, -2.8F,
                       ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_PICKAXE =
           pickaxeItem("alexandrite_pickaxe", ModToolMaterials.ALEXANDRITE, 1.0F, 2.0F,
                       ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    public static final DeferredItem<Item> PINK_PICKAXE =
           pickaxeItem("pink_pickaxe", ModToolMaterials.PINK, 2.0F, 2.0F,
                       ModTags.Items.PINK_TOOL_MATERIALS, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_PICKAXE =
           pickaxeItem("ultra_copper_pickaxe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                       ModTags.Items.COPPER_TOOL_MATERIALS, copperLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_PICKAXE =
           pickaxeItem("lapis_lazuli_pickaxe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                       ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_PICKAXE =
           pickaxeItem("redstone_pickaxe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                       ModTags.Items.REDSTONE_TOOL_MATERIALS, redstoneLoreColor);

    // ** CUSTOM sword **
    public static final DeferredItem<Item> BISMUTH_SWORD =
           swordItem("bismuth_sword", ModToolMaterials.BISMUTH, 5.0F, -2.4F,
                     ModTags.Items.BISMUTH_TOOL_MATERIALS, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_SWORD =
           swordItem("alexandrite_sword", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                     ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, alexandriteColor);

    public static final DeferredItem<Item> PINK_SWORD =
           swordItem("pink_sword", ModToolMaterials.PINK, 2.0F, 2.0F,
                     ModTags.Items.PINK_TOOL_MATERIALS, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_SWORD =
           swordEffectItem("ultra_copper_sword", ModToolMaterials.COPPER, 2.0F, 2.5F,
                           ModTags.Items.COPPER_TOOL_MATERIALS, copperLoreColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_SWORD =
           swordItem("lapis_lazuli_sword", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                     ModTags.Items.LAPIS_LAZULI_TOOL_MATERIALS, lapisLoreColor);

    public static final DeferredItem<Item> REDSTONE_SWORD =
           swordItem("redstone_sword", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                     ModTags.Items.REDSTONE_TOOL_MATERIALS, redstoneLoreColor);

    // ** CUSTOM Elytra armor **
    public static final DeferredItem<Item> DIAMOND_ELYTRA =
           elytraArmor("diamond_elytra", 10000, ModEquipmentAssetProvider.DIAMOND_ELYTRA,
                       ItemTags.DIAMOND_TOOL_MATERIALS, MobEffects.REGENERATION, 4, elytraLoreColor);

    // ** CUSTOM Armor Trim Smithing Template **
    public static final DeferredItem<Item> KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE =
           oresArmorTrimSmithingTemplate("kaupen_armor_trim_smithing_template", "kaupen",
                                         kaupenTrimLoreColor);

    // ** CUSTOM Ores Smithing Upgrade Template **
    public static final DeferredItem<Item> COPPER_UPGRADE_SMITHING_TEMPLATE =
           oresSmithingTemplate("copper_upgrade_smithing_template", "copper",
                                copperUpgradeSmithingLoreColor);

    // ** CUSTOM Music Disc **
    public static final DeferredItem<Item> BAR_BRAWL_MUSIC_DISC =
           editItem("bar_brawl_music_disc", properties ->
                    new Item(properties.jukeboxPlayable(ModSounds.BAR_BRAWL_KEY)
                                       .stacksTo(1)), barBrawlLoreColor);

    // ** CUSTOM Seeds **
    public static final DeferredItem<Item> RADISH_SEEDS =
           ITEMS.registerItem("radish_seeds", properties ->
                              new BlockItem(ModBlocks.RADISH_CROP.get(), properties));

    public static final DeferredItem<Item> KOHLRABI_SEEDS =
           ITEMS.registerItem("kohlrabi_seeds", properties ->
                              new BlockItem(ModBlocks.KOHLRABI_CROP.get(), properties));

    public static final DeferredItem<Item> CATTAIL_SEEDS =
           ITEMS.registerItem("cattail_seeds", properties ->
                              new BlockItem(ModBlocks.CATTAIL_CROP.get(), properties));

    // ** CUSTOM Bush Crop **
    public static final DeferredItem<Item> GOJI_BERRIES =
           ITEMS.registerItem("goji_berries", properties ->
                              new BlockItem(ModBlocks.GOJI_BERRY_BUSH.get(),
                                            properties.food(new FoodProperties.Builder().nutrition(2)
                                                                                        .saturationModifier(0.15F)
                                                                                        .build())));

    // ** CUSTOM Mob **
    // GECKO
    public static final DeferredItem<Item> GECKO_SPAWN_EGG =
           editItem("gecko_spawn_egg", properties ->
                    new SpawnEggItem(properties.spawnEgg(ModEntities.GECKO.get())), geckoLoreColor);

    // RHINO
    public static final DeferredItem<Item> RHINO_SPAWN_EGG =
           editItem("rhino_spawn_egg", properties ->
                    new SpawnEggItem(properties.spawnEgg(ModEntities.RHINO.get())), rhinoLoreColor);

    // ** CUSTOM Throwable Projectiles **
    public static final DeferredItem<Item> TOMAHAWK =
           customItem("tomahawk", TomahawkItem::new, props -> props.stacksTo(16), tomahawkLoreColor);

    public static final DeferredItem<Item> DICE_ITEM =
           customItem("dice_item", DiceItem::new, props -> props, diceLoreColor);

    // ** CUSTOM Animated Textures **
    public static final DeferredItem<Item> RADIATION_STAFF =
           customItem("radiation_staff", RadiationStaffItem::new,
                      props -> props.stacksTo(1).durability(1024), radiationStaffLoreColor);

    // ** CUSTOM Advanced Items **
    // Level Charger items
    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_PLUS =
           levelChargerItem("level_charger_generic_plus", 1, null, greenColor);

    public static final DeferredItem<Item> LEVEL_CHARGER_GENERIC_MINUS =
           levelChargerItem("level_charger_generic_minus", -1, null, redColor);

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_PLUS_FORTUNE =
           levelChargerItem("level_charger_specif_plus_fortune", 1, Enchantments.FORTUNE, greenColor);

    public static final DeferredItem<Item> LEVEL_CHARGER_SPECIF_MINUS_FORTUNE =
           levelChargerItem("level_charger_specif_minus_fortune", -1, Enchantments.FORTUNE, redColor);

    // ** CUSTOM Compactor items **
    // Ultra Compactor
    public static final DeferredItem<Item> ULTRA_COMPACTOR =
           editItem("ultra_compactor", properties ->
                    new CompactorItem(properties.stacksTo(1).fireResistant(), true,
                                      ModTags.Items.ULTRA_COMPACTOR_ITEMS, ModTags.Items.ULTRA_COMPACTOR_RESULT), pinkColor);

    // Pink Ultra Compactor
    public static final DeferredItem<Item> PINK_ULTRA_COMPACTOR =
           editItem("pink_ultra_compactor", properties ->
                    new CompactorItem(properties.stacksTo(1).fireResistant(), false,
                                      ModTags.Items.PINK_ULTRA_COMPACTOR_ITEMS, ModTags.Items.PINK_ULTRA_COMPACTOR_RESULT),
                                      pinkColor);

    // Mccourse Mod Bottle item
    public static final DeferredItem<Item> MCCOURSE_MOD_BOTTLE =
           editItem("mccourse_mod_bottle", properties ->
                    new MccourseModBottleItem(properties.fireResistant().stacksTo(1), 100000, 1),
                    mccourseModBottleLoreColor);

    // METAL DETECTOR item
    public static final DeferredItem<Item> METAL_DETECTOR =
           ITEMS.registerItem("metal_detector", properties ->
                              new MetalDetectorItem(properties.fireResistant().stacksTo(1),
                                                    ModTags.Blocks.METAL_DETECTOR_VALUABLES));

    // DATA TABLET item
    public static final DeferredItem<Item> DATA_TABLET =
           customItem("data_tablet", DataTabletItem::new, props ->  props.stacksTo(1),
                      dataTabletLoreColor);

    // GROWTH item
    public static final DeferredItem<Item> GROWTH =
           customItem("growth", GrowthItem::new,
                      props -> props.stacksTo(64).fireResistant(), growthLoreColor);

    // ** CUSTOM sign and Hanging sign **
    public static final DeferredItem<Item> WALNUT_SIGN =
           editItem("walnut_sign", properties ->
                    new SignItem(ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get(),
                                 properties.stacksTo(16)
                                           .component(DataComponents.CUSTOM_NAME,
                                                      componentTranslatableIntColor("item.mccoursemod.walnut_sign",
                                                                                    walnutColor))), walnutColor);

    public static final DeferredItem<Item> WALNUT_HANGING_SIGN =
           editItem("walnut_hanging_sign", properties ->
                    new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get(),
                                        properties.stacksTo(16)
                                                  .component(DataComponents.CUSTOM_NAME,
                                                             componentTranslatableIntColor(
                                                             "item.mccoursemod.walnut_hanging_sign", walnutColor))),
                                                             walnutColor);

    // TORCH BALL item
    public static final DeferredItem<Item> TORCH_BALL =
           customItem("torch_ball", TorchBallItem::new,
                      props -> props.fireResistant().stacksTo(1), torchBallLoreColor);

    public static final DeferredItem<Item> BOUNCY_BALLS =
           customItem("bouncy_balls", BouncyBallsItem::new,
                      props -> props.fireResistant().stacksTo(1), bouncyBallsLoreColor);

    public static final DeferredItem<Item> BOUNCY_BALLS_PARTICLES =
           customItem("bouncy_balls_particles", Item::new,
                      props -> props.stacksTo(64).fireResistant(), bouncyBallsLoreColor);

    // ** CUSTOM boats **
    public static final DeferredItem<Item> WALNUT_BOAT =
           editItem("walnut_boat", properties ->
                    new ModBoatItem(ModEntities.MOD_BOAT.get(), properties.stacksTo(1), walnutColor), walnutColor);

    public static final DeferredItem<Item> WALNUT_CHEST_BOAT =
           editItem("walnut_chest_boat", properties ->
                    new ModBoatItem(ModEntities.MOD_CHEST_BOAT.get(), properties.stacksTo(1), walnutColor), walnutColor);

    // ** Luck items **
    public static final DeferredItem<Item> LUCK_GENERAL =
           luckItem("luck_general", 3, 3, 0, 0,
                    ModTags.Enchantments.ALL_ENCHANTMENTS, luckGeneralLoreColor, null);

    public static final DeferredItem<Item> LUCK_PICKAXE =
           luckItem("luck_pickaxe", 1, 2, 10, 1,
                    ModTags.Enchantments.MINING_ENCHANTMENTS, luckPickaxeLoreColor, "pickaxe");

    public static final DeferredItem<Item> LUCK_WEAPON =
           luckItem("luck_weapon", 1, 1, 6, 2,
                    ModTags.Enchantments.SWORD_ENCHANTMENTS, luckWeaponLoreColor, "weapon");

    // ** CUSTOM METHOD - Luck items **
    public static DeferredItem<Item> luckItem(String name, int bookAmount,
                                              int enchPerBook, int enchLevel, int enchType,
                                              TagKey<Enchantment> tag, int textColor, @Nullable String textMessage) {
        return editItem(name, properties ->
                        new LuckItem(properties.fireResistant(), bookAmount, enchPerBook,
                                     enchLevel, enchType, tag, textColor, textMessage), textColor);
    }

    // ** CUSTOM METHOD - Level Charger items **
    public static DeferredItem<Item> levelChargerItem(String name, int amount,
                                                      @Nullable ResourceKey<Enchantment> enchantment,
                                                      int color) {
        return editItem(name, properties ->
                        new LevelChargerItem(properties.fireResistant(), amount, enchantment), color);
    }

    // ** CUSTOM METHOD - Fuel **
    public static DeferredItem<Item> fuelItem(String name, int burnTime) {
        return editItem(name, properties -> new FuelItem(properties, burnTime), whiteColor);
    }

    // ** CUSTOM METHOD - Food **
    public static DeferredItem<Item> foodItem(String name, int nutrition,
                                              float saturation, Holder<MobEffect> effect,
                                              int duration, float chance, int color) {
        return customItem(name, Item::new,
                          props -> props.food(new FoodProperties.Builder()
                                                                          .nutrition(nutrition)
                                                                          .saturationModifier(saturation).build(),
                                                       Consumables.defaultFood()
                                                                  .onConsume(new ApplyStatusEffectsConsumeEffect(
                                                                             new MobEffectInstance(effect, duration),
                                                                                                   chance)).build()),
                          color);
    }

    // ** CUSTOM METHOD - Trim Material **
    public static DeferredItem<Item> trimMaterialItem(String name,
                                                      ResourceKey<TrimMaterial> trim, int lore) {
        return customItem(name, Item::new, props -> props.trimMaterial(trim), lore);
    }

    // ** CUSTOM METHOD - Paxel tool **
    public static DeferredItem<Item> paxelItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed,
                                               TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new PaxelItem(material, attackDamage, attackSpeed,
                                      properties.fireResistant().repairable(repair), color), color);
    }

    // ** CUSTOM METHOD - Hammer tool **
    public static DeferredItem<Item> hammerItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed, TagKey<Item> repair,
                                               int radius, int argbColors, int textColor) {
        return editItem(name, properties ->
                        new HammerItem(material, attackDamage, attackSpeed, properties.fireResistant().repairable(repair),
                                       radius, argbColors, textColor), textColor);
    }

    // ** CUSTOM METHOD - Shovel tool **
    public static DeferredItem<Item> shovelItem(String name, ToolMaterial material,
                                                float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new ShovelItem(material, attackDamage, attackSpeed,
                                       properties.fireResistant().repairable(repair)) {
                                            @Override
                                            public @NotNull Component getName(@NotNull ItemStack stack) {
                                                return componentTranslatableIntColor(this.getDescriptionId(), color);
                                            }
                                       }, color);
    }

    // ** CUSTOM METHOD - Axe tool **
    public static DeferredItem<Item> axeItem(String name, ToolMaterial material,
                                             float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new AxeItem(material, attackDamage, attackSpeed,
                                    properties.fireResistant().repairable(repair)) {
                                         @Override
                                         public @NotNull Component getName(@NotNull ItemStack stack) {
                                             return componentTranslatableIntColor(this.getDescriptionId(), color);
                                         }
                                    }, color);
    }

    // ** CUSTOM METHOD - Hoe tool **
    public static DeferredItem<Item> hoeItem(String name, ToolMaterial material,
                                             float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new HoeItem(material, attackDamage, attackSpeed,
                                    properties.fireResistant().repairable(repair)) {
                                         @Override
                                         public @NotNull Component getName(@NotNull ItemStack stack) {
                                             return componentTranslatableIntColor(this.getDescriptionId(), color);
                                         }
                                    }, color);
    }

    // ** CUSTOM METHOD - Pickaxe tool **
    public static DeferredItem<Item> pickaxeItem(String name, ToolMaterial material,
                                                 float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new Item(properties.pickaxe(material, attackDamage, attackSpeed)
                                           .fireResistant().repairable(repair)) {
                                      @Override
                                      public @NotNull Component getName(@NotNull ItemStack stack) {
                                          return componentTranslatableIntColor(this.getDescriptionId(), color);
                                      }
                                 }, color);
    }

    // ** CUSTOM METHOD - Sword tool **
    public static DeferredItem<Item> swordItem(String name, ToolMaterial material,
                                               float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new Item(properties.sword(material, attackDamage, attackSpeed)
                                           .fireResistant().repairable(repair)) {
                                      @Override
                                      public @NotNull Component getName(@NotNull ItemStack stack) {
                                          return componentTranslatableIntColor(this.getDescriptionId(), color);
                                      }
                                 }, color);
    }

    // ** CUSTOM METHOD - Special SWORD tool **
    public static DeferredItem<Item> swordEffectItem(String name, ToolMaterial material,
                                                     float attackDamage, float attackSpeed,
                                                     TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new SwordEffectItem(properties, material, attackDamage, attackSpeed, repair, color), color);
    }

    // ** CUSTOM METHOD - Helmet armor **
    public static DeferredItem<Item> helmetArmor(String name,
                                                 ArmorMaterial material, int color) {
        return editItem(name, properties ->
                        new ModArmorItem(properties.humanoidArmor(material, ArmorType.HELMET)
                                                   .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Chestplate armor **
    public static DeferredItem<Item> chestplateArmor(String name,
                                                     ArmorMaterial material, int color) {
        return editItem(name, properties ->
                        new ModArmorItem(properties.humanoidArmor(material, ArmorType.CHESTPLATE)
                                                   .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Leggings armor **
    public static DeferredItem<Item> leggingsArmor(String name,
                                                   ArmorMaterial material, int color) {
        return editItem(name, properties ->
                        new ModArmorItem(properties.humanoidArmor(material, ArmorType.LEGGINGS)
                                                   .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Boots armor **
    public static DeferredItem<Item> bootsArmor(String name, ArmorMaterial material, int color) {
        return editItem(name, properties ->
                        new ModArmorItem(properties.humanoidArmor(material, ArmorType.BOOTS)
                                                   .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Elytra armor **
    public static DeferredItem<Item> elytraArmor(String name,
                                                 int durability, ResourceKey<EquipmentAsset> equipAsset,
                                                 TagKey<Item> repair, Holder<MobEffect> effectHolder,
                                                 int effectAmplifier, int color) {
        return editItem(name, properties ->
                        new ElytraPlusItem(effectHolder, effectAmplifier,
                                           properties.fireResistant().durability(durability).rarity(Rarity.EPIC)
                                                     .component(DataComponents.GLIDER, Unit.INSTANCE)
                                                     .component(DataComponents.EQUIPPABLE,
                                                                Equippable.builder(EquipmentSlot.CHEST)
                                                                          .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                                                          .setAsset(equipAsset).setDamageOnHurt(false)
                                                                          .build())
                                                     .repairable(repair)), color);
    }

    // ** CUSTOM METHOD - Bow tool **
    public static DeferredItem<Item> bowItem(String name,
                                             TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new BowItem(properties.repairable(repair).stacksTo(1)) {
                                         @Override
                                         public @NotNull Component getName(@NotNull ItemStack stack) {
                                             return componentTranslatableIntColor(this.getDescriptionId(), color);
                                         }
                                    }, color);
    }

    // ** CUSTOM METHOD - Hammer Bow tool **
    public static DeferredItem<Item> hammerBowItem(String name, TagKey<Item> repair,
                                                   int radius, int depth, int color) {
        return editItem(name, properties ->
                        new MinerBowItem(properties.repairable(repair), radius, depth), color);
    }

    // ** CUSTOM METHOD - Horse armor **
    public static DeferredItem<Item> horseArmorItem(String name,
                                                    ArmorMaterial armorMaterial, int color) {
        return editItem(name, properties ->
                        new Item(properties.stacksTo(1).horseArmor(armorMaterial)) {
                                    @Override
                                    public @NotNull Component getName(@NotNull ItemStack stack) {
                                        return componentTranslatableIntColor(this.getDescriptionId(), color);
                                    }
                                }, color);
    }

    // ** CUSTOM METHOD - Shield tool **
    public static DeferredItem<Item> shieldItem(String name, TagKey<Item> repair, int color) {
        return editItem(name, properties ->
                        new ShieldItem(properties.component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                                                 .repairable(repair).equippableUnswappable(EquipmentSlot.OFFHAND)
                                                 .component(DataComponents.BLOCKS_ATTACKS,
                                                            new BlocksAttacks(0.25F, 1.0F,
                                                                              List.of(new BlocksAttacks.DamageReduction(
                                                                                      90.0F,
                                                                                      Optional.empty(),
                                                                                      0.0F, 1.0F)),
                                                            new BlocksAttacks.ItemDamageFunction(3.0F,
                                                                                                 1.0F, 1.0F),
                                                            Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                                            Optional.of(SoundEvents.SHIELD_BLOCK),
                                                            Optional.of(SoundEvents.SHIELD_BREAK)))
                                                 .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)), color);
    }

    // ** CUSTOM METHOD - Ores Smithing Upgrade Template **
    public static DeferredItem<Item> oresSmithingTemplate(String name, String ore,
                                                          int lore) {
        String smithingName = "smithing_template." + ore + "_upgrade.";
        return editItem(name, properties ->
                        new OresSmithingTemplateItem(lines(smithingName + "applies_to", lore),
                                                     lines(smithingName + "ingredients", lore),
                                                     lines(smithingName + "base_slot_description", lore),
                                                     lines(smithingName + "additions_slot_description", lore),
                                                     createOresUpgradeIconList(), createOresUpgradeMaterialList(),
                                                     properties.fireResistant().rarity(Rarity.EPIC)), lore);
    }

    // ** CUSTOM METHOD - Ores Armor Trim Smithing Template **
    public static DeferredItem<Item> oresArmorTrimSmithingTemplate(String name, String ore,
                                                                   int lore) {
        String smithingName = "smithing_template." + ore + ".armor_trim.";
        return editItem(name, properties ->
                        new OresSmithingTemplateItem(lines(smithingName + "applies_to", lore),
                                                     lines(smithingName + "ingredients", lore),
                                                     lines(smithingName + "base_slot_description", lore),
                                                     lines(smithingName + "additions_slot_description", lore),
                                                     createOresTrimmableArmorIconList(),
                                                     createOresTrimmableMaterialIconList(),
                                                     properties.rarity(Rarity.UNCOMMON)), lore);
    }

    // CUSTOM METHOD - (CUSTOM classes) Registry all custom ITEMS
    private static <I extends Item> DeferredItem<I> customItem(String name,
                                                               Function<Properties, ? extends I> item,
                                                               UnaryOperator<Properties> properties,
                                                               int lore) {
        List<Component> itemLore = List.of(componentTranslatableIntColor("tooltip.item.mccoursemod." + name, lore));
        return ITEMS.registerItem(name, item,
                                  props -> properties.apply(props.component(DataComponents.LORE, new ItemLore(itemLore))));
    }

    // CUSTOM METHOD - (CUSTOM classes) Registry all custom ITEMS
    public static <I extends Item> DeferredItem<I> editItem(String name,
                                                            Function<Item.Properties, ? extends I> item, int lore) {
        List<Component> itemLore = List.of(componentTranslatableIntColor("tooltip.item.mccoursemod." + name, lore));
        return ITEMS.registerItem(name, item, props -> props.component(DataComponents.LORE, new ItemLore(itemLore)));
    }

    // CUSTOM METHOD - Registry all items on event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}