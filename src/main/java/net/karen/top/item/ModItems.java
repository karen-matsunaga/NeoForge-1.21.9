package net.karen.top.item;

import net.karen.top.Top;
import net.karen.top.block.ModBlocks;
import net.karen.top.entity.ModEntities;
import net.karen.top.item.custom.*;
import net.karen.top.sound.ModSounds;
import net.karen.top.trim.ModTrimMaterials;
import net.karen.top.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
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
import static net.karen.top.item.custom.OresSmithingTemplateItem.*;
import static net.karen.top.util.ChatUtils.*;

public class ModItems {
    // ** CUSTOM items - Registry all custom ITEMS **
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Top.MOD_ID);

    // ** CUSTOM ore items **
    // BISMUTH
    public static final DeferredItem<Item> BISMUTH = trimMaterialItem("bismuth", ModTrimMaterials.BISMUTH, bismuthColor);

    public static final DeferredItem<Item> RAW_BISMUTH = rawItem("raw_bismuth", bismuthColor);

    // ALEXANDRITE
    public static final DeferredItem<Item> ALEXANDRITE =
           trimMaterialItem("alexandrite", ModTrimMaterials.ALEXANDRITE, alexandriteColor);

    public static final DeferredItem<Item> RAW_ALEXANDRITE = rawItem("raw_alexandrite", alexandriteColor);

    // PINK
    public static final DeferredItem<Item> PINK = trimMaterialItem("pink", ModTrimMaterials.PINK, pinkColor);

    // ** CUSTOM advanced items **
    public static final DeferredItem<Item> CHISEL =
           shiftItem("chisel", props -> new ChiselItem(props.durability(32)));

    public static final DeferredItem<Item> RESTORE =
           customItem("restore", RestoreItem::new, Properties::fireResistant, restoreColor);

    public static final DeferredItem<Item> FARMER =
           customItem("farmer", FarmerItem::new, Properties::fireResistant, farmerColor);

    public static final DeferredItem<Item> CATTAIL =
           customItem("cattail", ModWaxingItem::new, Properties::fireResistant, cattailColor);

    // ** CUSTOM Foods **
    public static final DeferredItem<Item> RADISH =
           foodItem("radish", 3, 0.25F, MobEffects.HEALTH_BOOST, 400, 0.35F, radishColor);

    public static final DeferredItem<Item> KOHLRABI =
           foodItem("kohlrabi", 3, 0.25F, MobEffects.SPEED, 200, 0.1F, kohlrabiColor);

    public static final DeferredItem<Item> COFFEE =
           foodItem("coffee", 5, 0.1F, MobEffects.NIGHT_VISION, 600, 0.5F, coffeeColor);

    // ** CUSTOM fuels (Custom FURNACE) **
    public static final DeferredItem<Item> FROSTFIRE_ICE =
           fuelItem("frostfire_ice", 800, frostfireIceColor);

    public static final DeferredItem<Item> STARLIGHT_ASHES =
           fuelItem("starlight_ashes", 1200, starlightAshesColor);

    public static final DeferredItem<Item> PEAT_BRICK = fuelItem("peat_brick", 200, peatBrickColor);

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
           helmetArmor("ultra_copper_helmet", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperColor);

    public static final DeferredItem<Item> ULTRA_COPPER_CHESTPLATE =
           chestplateArmor("ultra_copper_chestplate", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperColor);

    public static final DeferredItem<Item> ULTRA_COPPER_LEGGINGS =
           leggingsArmor("ultra_copper_leggings", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperColor);

    public static final DeferredItem<Item> ULTRA_COPPER_BOOTS =
           bootsArmor("ultra_copper_boots", ModArmorMaterials.COPPER_ARMOR_MATERIAL, copperColor);

    // LAPIS LAZULI
    public static final DeferredItem<Item> LAPIS_LAZULI_HELMET =
           helmetArmor("lapis_lazuli_helmet", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_CHESTPLATE =
           chestplateArmor("lapis_lazuli_chestplate", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_LEGGINGS =
           leggingsArmor("lapis_lazuli_leggings", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_BOOTS =
           bootsArmor("lapis_lazuli_boots", ModArmorMaterials.LAPIS_LAZULI_ARMOR_MATERIAL, lapisColor);

    // REDSTONE
    public static final DeferredItem<Item> REDSTONE_HELMET =
           helmetArmor("redstone_helmet", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneColor);

    public static final DeferredItem<Item> REDSTONE_CHESTPLATE =
           chestplateArmor("redstone_chestplate", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneColor);

    public static final DeferredItem<Item> REDSTONE_LEGGINGS =
           leggingsArmor("redstone_leggings", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneColor);

    public static final DeferredItem<Item> REDSTONE_BOOTS =
           bootsArmor("redstone_boots", ModArmorMaterials.REDSTONE_ARMOR_MATERIAL, redstoneColor);

    // EMERALD
    public static final DeferredItem<Item> EMERALD_HELMET =
           helmetArmor("emerald_helmet", ModArmorMaterials.EMERALD_ARMOR_MATERIAL, emeraldColor);

    public static final DeferredItem<Item> EMERALD_CHESTPLATE =
           chestplateArmor("emerald_chestplate", ModArmorMaterials.EMERALD_ARMOR_MATERIAL, emeraldColor);

    public static final DeferredItem<Item> EMERALD_LEGGINGS =
           leggingsArmor("emerald_leggings", ModArmorMaterials.EMERALD_ARMOR_MATERIAL, emeraldColor);

    public static final DeferredItem<Item> EMERALD_BOOTS =
           bootsArmor("emerald_boots", ModArmorMaterials.EMERALD_ARMOR_MATERIAL, emeraldColor);

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
    public static final DeferredItem<Item> TOP_FISHING_ROD =
           editItem(Top.MOD_ID + "_fishing_rod", props ->
                    new FishingRodItem(props.fireResistant().stacksTo(1)) {
                                       // DEFAULT METHOD - Appears on name item
                                       @Override
                                       public @NotNull Component getName(@NotNull ItemStack stack) {
                                           return componentTranslatableIntColor(this.getDescriptionId(), fishingRodColor);
                                       }},
                    fishingRodColor);

    // ** CUSTOM Shield tools **
    public static final DeferredItem<Item> ALEXANDRITE_SHIELD =
           shieldItem("alexandrite_shield", ModTags.Items.ALEXANDRITE_TOOL_MATERIALS, 600, alexandriteColor);

    // ** CUSTOM Paxel tools **
    public static final DeferredItem<Item> BISMUTH_PAXEL =
           paxelItem("bismuth_paxel", ModToolMaterials.BISMUTH, 1F, -2.8F, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_PAXEL =
           paxelItem("alexandrite_paxel", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                     alexandriteColor);

    public static final DeferredItem<Item> PINK_PAXEL =
           paxelItem("pink_paxel", ModToolMaterials.PINK, 1.0F, 2.0F, pinkColor);

    public static final DeferredItem<Item> COPPER_PAXEL =
           paxelItem("copper_paxel", ModToolMaterials.COPPER, 1.0F, 2.5F, copperColor);

    public static final DeferredItem<Item> DIAMOND_PAXEL =
           paxelItem("diamond_paxel", ToolMaterial.DIAMOND, 1.0F, 4.5F, diamondColor);

    public static final DeferredItem<Item> GOLD_PAXEL =
           paxelItem("gold_paxel", ToolMaterial.GOLD, 1.0F, 4.0F, goldColor);

    public static final DeferredItem<Item> IRON_PAXEL =
           paxelItem("iron_paxel", ToolMaterial.IRON, 1.0F, 3.0F, ironColor);

    public static final DeferredItem<Item> STONE_PAXEL =
           paxelItem("stone_paxel", ToolMaterial.STONE, 1.0F, 1.5F, stoneColor);

    public static final DeferredItem<Item> WOODEN_PAXEL =
           paxelItem("wooden_paxel", ToolMaterial.WOOD, 1.0F, 1.0F, woodenColor);

    public static final DeferredItem<Item> NETHERITE_PAXEL =
           paxelItem("netherite_paxel", ToolMaterial.NETHERITE, 1.0F, 5.0F,
                     netheriteColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_PAXEL =
           paxelItem("lapis_lazuli_paxel", ModToolMaterials.LAPIS_LAZULI, 1.0F, 3.5F,
                     lapisColor);

    public static final DeferredItem<Item> REDSTONE_PAXEL =
           paxelItem("redstone_paxel", ModToolMaterials.REDSTONE, 1.0F, 4.5F,
                     redstoneColor);

    public static final DeferredItem<Item> EMERALD_PAXEL =
           paxelItem("emerald_paxel", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                     emeraldColor);

    // ** CUSTOM Hammer tools **
    public static final DeferredItem<Item> BISMUTH_HAMMER =
           hammerItem("bismuth_hammer", ModToolMaterials.BISMUTH, 7F, -3.5F,
                      2, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_HAMMER =
           hammerItem("alexandrite_hammer", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                      2, alexandriteColor);

    public static final DeferredItem<Item> PINK_HAMMER =
           hammerItem("pink_hammer", ModToolMaterials.PINK, 2.0F, 2.0F,
                      3, pinkColor);

    public static final DeferredItem<Item> COPPER_HAMMER =
           hammerItem("copper_hammer", ModToolMaterials.COPPER, 2.0F, 2.5F,
                      2, copperColor);

    public static final DeferredItem<Item> DIAMOND_HAMMER =
           hammerItem("diamond_hammer", ToolMaterial.DIAMOND, 2.0F, 4.5F,
                      3, diamondColor);

    public static final DeferredItem<Item> GOLD_HAMMER =
           hammerItem("gold_hammer", ToolMaterial.GOLD, 2.0F, 4.0F,
                      2, goldColor);

    public static final DeferredItem<Item> IRON_HAMMER =
           hammerItem("iron_hammer", ToolMaterial.IRON, 2.0F, 3.0F,
                      2, ironColor);

    public static final DeferredItem<Item> STONE_HAMMER =
           hammerItem("stone_hammer", ToolMaterial.STONE, 2.0F, 1.5F,
                      1, stoneColor);

    public static final DeferredItem<Item> WOODEN_HAMMER =
           hammerItem("wooden_hammer", ToolMaterial.WOOD, 2.0F, 1.0F,
                      1, woodenColor);

    public static final DeferredItem<Item> NETHERITE_HAMMER =
           hammerItem("netherite_hammer", ToolMaterial.NETHERITE, 2.0F, 5.0F,
                      5, netheriteColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_HAMMER =
           hammerItem("lapis_lazuli_hammer", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                      4, lapisColor);

    public static final DeferredItem<Item> REDSTONE_HAMMER =
           hammerItem("redstone_hammer", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                      4, redstoneColor);

    public static final DeferredItem<Item> EMERALD_HAMMER =
           hammerItem("emerald_hammer", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                      4, emeraldColor);

    // ** CUSTOM Shovel tools **
    public static final DeferredItem<Item> BISMUTH_SHOVEL =
           shovelItem("bismuth_shovel", ModToolMaterials.BISMUTH, 1.5F, -3.0F,
                      bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_SHOVEL =
           shovelItem("alexandrite_shovel", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                      alexandriteColor);

    public static final DeferredItem<Item> PINK_SHOVEL =
           shovelItem("pink_shovel", ModToolMaterials.PINK, 2.0F, 2.0F, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_SHOVEL =
           shovelItem("ultra_copper_shovel", ModToolMaterials.COPPER, 2.0F, 2.5F,
                      copperColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_SHOVEL =
           shovelItem("lapis_lazuli_shovel", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                      lapisColor);

    public static final DeferredItem<Item> REDSTONE_SHOVEL =
           shovelItem("redstone_shovel", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                      redstoneColor);

    public static final DeferredItem<Item> EMERALD_SHOVEL =
           shovelItem("emerald_shovel", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                      emeraldColor);

    // ** CUSTOM Axe tools **
    public static final DeferredItem<Item> BISMUTH_AXE =
           axeItem("bismuth_axe", ModToolMaterials.BISMUTH, 6.0F, -3.2F, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_AXE =
           axeItem("alexandrite_axe", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                   alexandriteColor);

    public static final DeferredItem<Item> PINK_AXE =
           axeItem("pink_axe", ModToolMaterials.PINK, 2.0F, 2.0F, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_AXE =
           axeItem("ultra_copper_axe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                   copperColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_AXE =
           axeItem("lapis_lazuli_axe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                   lapisColor);

    public static final DeferredItem<Item> REDSTONE_AXE =
           axeItem("redstone_axe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                   redstoneColor);

    public static final DeferredItem<Item> EMERALD_AXE =
           axeItem("emerald_axe", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                   emeraldColor);

    // ** CUSTOM hoe **
    public static final DeferredItem<Item> BISMUTH_HOE =
           hoeItem("bismuth_hoe", ModToolMaterials.BISMUTH, 0.0F, -3.0F, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_HOE =
           hoeItem("alexandrite_hoe", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                   alexandriteColor);

    public static final DeferredItem<Item> PINK_HOE =
           hoeItem("pink_hoe", ModToolMaterials.PINK, 2.0F, 2.0F, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_HOE =
           hoeItem("ultra_copper_hoe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                   copperColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_HOE =
           hoeItem("lapis_lazuli_hoe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                   lapisColor);

    public static final DeferredItem<Item> REDSTONE_HOE =
           hoeItem("redstone_hoe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                   redstoneColor);

    public static final DeferredItem<Item> EMERALD_HOE =
           hoeItem("emerald_hoe", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                   emeraldColor);

    // ** CUSTOM pickaxe **
    public static final DeferredItem<Item> BISMUTH_PICKAXE =
           pickaxeItem("bismuth_pickaxe", ModToolMaterials.BISMUTH, 1.0F, -2.8F,
                       bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_PICKAXE =
           pickaxeItem("alexandrite_pickaxe", ModToolMaterials.ALEXANDRITE, 1.0F, 2.0F,
                       alexandriteColor);

    public static final DeferredItem<Item> PINK_PICKAXE =
           pickaxeItem("pink_pickaxe", ModToolMaterials.PINK, 2.0F, 2.0F, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_PICKAXE =
           pickaxeItem("ultra_copper_pickaxe", ModToolMaterials.COPPER, 2.0F, 2.5F,
                       copperColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_PICKAXE =
           pickaxeItem("lapis_lazuli_pickaxe", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                       lapisColor);

    public static final DeferredItem<Item> REDSTONE_PICKAXE =
           pickaxeItem("redstone_pickaxe", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                       redstoneColor);

    public static final DeferredItem<Item> EMERALD_PICKAXE =
           pickaxeItem("emerald_pickaxe", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                       emeraldColor);

    // ** CUSTOM sword **
    public static final DeferredItem<Item> BISMUTH_SWORD =
           swordItem("bismuth_sword", ModToolMaterials.BISMUTH, 5.0F, -2.4F, bismuthColor);

    public static final DeferredItem<Item> ALEXANDRITE_SWORD =
           swordItem("alexandrite_sword", ModToolMaterials.ALEXANDRITE, 2.0F, 3.0F,
                     alexandriteColor);

    public static final DeferredItem<Item> PINK_SWORD =
           swordItem("pink_sword", ModToolMaterials.PINK, 2.0F, 2.0F, pinkColor);

    public static final DeferredItem<Item> ULTRA_COPPER_SWORD =
           swordEffectItem("ultra_copper_sword", ModToolMaterials.COPPER, 2.0F, 2.5F,
                           copperColor);

    public static final DeferredItem<Item> LAPIS_LAZULI_SWORD =
           swordItem("lapis_lazuli_sword", ModToolMaterials.LAPIS_LAZULI, 2.0F, 3.5F,
                           lapisColor);

    public static final DeferredItem<Item> REDSTONE_SWORD =
           swordItem("redstone_sword", ModToolMaterials.REDSTONE, 2.0F, 4.5F,
                           redstoneColor);

    public static final DeferredItem<Item> EMERALD_SWORD =
           swordItem("emerald_sword", ModToolMaterials.EMERALD, 1.0F, 1.0F,
                           emeraldColor);

    // ** CUSTOM Elytra armor **
    public static final DeferredItem<Item> DIAMOND_ELYTRA =
           elytraArmor("diamond_elytra", MobEffects.REGENERATION, ModArmorMaterials.DIAMOND_ELYTRA_ARMOR_MATERIAL,
                       4, elytraColor);

    public static final DeferredItem<Item> EMERALD_ELYTRA =
           elytraArmor("emerald_elytra", MobEffects.LUCK, ModArmorMaterials.EMERALD_ELYTRA_ARMOR_MATERIAL,
                       2, emeraldColor);

    // ** CUSTOM Armor Trim Smithing Template **
    public static final DeferredItem<Item> KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE =
           oresArmorTrimSmithingTemplate("kaupen_armor_trim_smithing_template", "kaupen", kaupenTrimColor);

    // ** CUSTOM Ores Smithing Upgrade Template **
    public static final DeferredItem<Item> COPPER_UPGRADE_SMITHING_TEMPLATE =
           oresSmithingTemplate("copper_upgrade_smithing_template", "copper", copperUpgradeSmithingColor);

    // ** CUSTOM Music Disc **
    public static final DeferredItem<Item> BAR_BRAWL_MUSIC_DISC =
           editItem("bar_brawl_music_disc", props ->
                    new Item(props.jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).stacksTo(1)) {
                             @Override
                             public @NotNull Component getName(@NotNull ItemStack stack) {
                                 return componentTranslatableIntColor(this.getDescriptionId(), barBrawlColor);
                             }},
                   barBrawlColor);

    // ** CUSTOM Seeds **
    public static final DeferredItem<Item> RADISH_SEEDS =
           editItem("radish_seeds", props ->
                    new BlockItem(ModBlocks.RADISH_CROP.get(),
                                  props.component(DataComponents.CUSTOM_NAME,
                                                  componentTranslatableIntColor("item." + top + "radish_seeds",
                                                                                radishColor))), radishColor);

    public static final DeferredItem<Item> KOHLRABI_SEEDS =
           editItem("kohlrabi_seeds", props ->
                    new BlockItem(ModBlocks.KOHLRABI_CROP.get(),
                                  props.component(DataComponents.CUSTOM_NAME,
                                                  componentTranslatableIntColor("item." + top + "kohlrabi_seeds",
                                                                                kohlrabiColor))), kohlrabiColor);

    public static final DeferredItem<Item> CATTAIL_SEEDS =
           editItem("cattail_seeds", props ->
                    new BlockItem(ModBlocks.CATTAIL_CROP.get(),
                                  props.component(DataComponents.CUSTOM_NAME,
                                                  componentTranslatableIntColor("item." + top + "cattail_seeds",
                                                                                cattailColor))), cattailColor);

    // ** CUSTOM Bush Crop **
    public static final DeferredItem<Item> GOJI_BERRIES =
           editItem("goji_berries", props ->
                    new BlockItem(ModBlocks.GOJI_BERRY_BUSH.get(),
                                  props.food(new FoodProperties.Builder().nutrition(2).saturationModifier(0.15F).build())
                                       .component(DataComponents.CUSTOM_NAME,
                                                  componentTranslatableIntColor("item." + top + "goji_berries", gojiColor))),
                    gojiColor);

    // ** CUSTOM Mob **
    // GECKO
    public static final DeferredItem<Item> GECKO_SPAWN_EGG =
           editItem("gecko_spawn_egg", props ->
                    new SpawnEggItem(props.spawnEgg(ModEntities.GECKO.get())) {
                                     @Override
                                     public @NotNull Component getName(@NotNull ItemStack stack) {
                                         return componentTranslatableIntColor(this.getDescriptionId(), geckoColor);
                                     }},
                    geckoColor);

    // RHINO
    public static final DeferredItem<Item> RHINO_SPAWN_EGG =
           editItem("rhino_spawn_egg", props ->
                    new SpawnEggItem(props.spawnEgg(ModEntities.RHINO.get())) {
                                     @Override
                                     public @NotNull Component getName(@NotNull ItemStack stack) {
                                         return componentTranslatableIntColor(this.getDescriptionId(), rhinoColor);
                                     }},
                    rhinoColor);

    // ** CUSTOM Throwable Projectiles **
    public static final DeferredItem<Item> TOMAHAWK =
           customItem("tomahawk", TomahawkItem::new, props -> props.stacksTo(16), tomahawkColor);

    public static final DeferredItem<Item> DICE_ITEM =
           customItem("dice_item", DiceItem::new, props -> props, diceColor);

    // ** CUSTOM Animated Textures **
    public static final DeferredItem<Item> RADIATION_STAFF =
           customItem("radiation_staff", RadiationStaffItem::new, props ->
                      props.stacksTo(1).durability(1024), radiationStaffColor);

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
           editItem("ultra_compactor", props ->
                    new CompactorItem(props.stacksTo(1).fireResistant(), true,
                                      ModTags.Items.ULTRA_COMPACTOR_ITEMS, ModTags.Items.ULTRA_COMPACTOR_RESULT), pinkColor);

    // Pink Ultra Compactor
    public static final DeferredItem<Item> PINK_ULTRA_COMPACTOR =
           editItem("pink_ultra_compactor", props ->
                    new CompactorItem(props.stacksTo(1).fireResistant(), false,
                                      ModTags.Items.PINK_ULTRA_COMPACTOR_ITEMS, ModTags.Items.PINK_ULTRA_COMPACTOR_RESULT),
                                      pinkColor);

    // Special Bottle item
    public static final DeferredItem<Item> SPECIAL_BOTTLE =
           editItem("special_bottle", props ->
                    new SpecialBottleItem(props.fireResistant().stacksTo(1), 100000, 1),
                    specialBottleColor);

    // METAL DETECTOR item
    public static final DeferredItem<Item> METAL_DETECTOR =
           shiftItem("metal_detector", props ->
                     new MetalDetectorItem(props.fireResistant().stacksTo(1),
                                           ModTags.Blocks.METAL_DETECTOR_VALUABLES));

    // DATA TABLET item
    public static final DeferredItem<Item> DATA_TABLET =
           customItem("data_tablet", DataTabletItem::new, props -> props.stacksTo(1),
                      dataTabletColor);

    // GROWTH item
    public static final DeferredItem<Item> GROWTH =
           customItem("growth", GrowthItem::new, props ->
                      props.stacksTo(64).fireResistant(), growthColor);

    // ** CUSTOM sign and Hanging sign **
    public static final DeferredItem<Item> WALNUT_SIGN =
           editItem("walnut_sign", props ->
                    new SignItem(ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get(),
                                 props.stacksTo(16)
                                      .component(DataComponents.CUSTOM_NAME,
                                                 componentTranslatableIntColor("item." + top + "walnut_sign",
                                                                               walnutColor))), walnutColor);

    public static final DeferredItem<Item> WALNUT_HANGING_SIGN =
           editItem("walnut_hanging_sign", props ->
                    new HangingSignItem(ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get(),
                                        props.stacksTo(16)
                                             .component(DataComponents.CUSTOM_NAME,
                                                        componentTranslatableIntColor(
                                                        "item." + top + "walnut_hanging_sign", walnutColor))),
                                                        walnutColor);

    // TORCH BALL item
    public static final DeferredItem<Item> TORCH_BALL =
           customItem("torch_ball", TorchBallItem::new,
                      props -> props.fireResistant().stacksTo(1), torchBallColor);

    public static final DeferredItem<Item> BOUNCY_BALLS =
           customItem("bouncy_balls", BouncyBallsItem::new,
                      props -> props.fireResistant().stacksTo(1), bouncyBallsColor);

    public static final DeferredItem<Item> BOUNCY_BALLS_PARTICLES =
           editItem("bouncy_balls_particles", props ->
                    new Item(props.stacksTo(64).fireResistant()) {
                             @Override
                             public @NotNull Component getName(@NotNull ItemStack stack) {
                                 return componentTranslatableIntColor(this.getDescriptionId(), bouncyBallsColor);
                             }},
                   bouncyBallsColor);

    // ** CUSTOM boats **
    public static final DeferredItem<Item> WALNUT_BOAT =
           editItem("walnut_boat", props ->
                    new ModBoatItem(ModEntities.MOD_BOAT.get(), props.stacksTo(1), walnutColor), walnutColor);

    public static final DeferredItem<Item> WALNUT_CHEST_BOAT =
           editItem("walnut_chest_boat", props ->
                    new ModBoatItem(ModEntities.MOD_CHEST_BOAT.get(), props.stacksTo(1), walnutColor), walnutColor);

    // ** Luck items **
    public static final DeferredItem<Item> LUCK_GENERAL =
           luckItem("luck_general", 3, 3, 0, 0,
                    ModTags.Enchantments.ALL_ENCHANTMENTS, luckGeneralColor, null);

    public static final DeferredItem<Item> LUCK_PICKAXE =
           luckItem("luck_pickaxe", 1, 2, 10, 1,
                    ModTags.Enchantments.MINING_ENCHANTMENTS, luckPickaxeColor, "pickaxe");

    public static final DeferredItem<Item> LUCK_WEAPON =
           luckItem("luck_weapon", 1, 1, 6, 2,
                    ModTags.Enchantments.SWORD_ENCHANTMENTS, luckWeaponColor, "weapon");

    // ** CUSTOM METHOD - Raw ore items **
    public static DeferredItem<Item> rawItem(String name, int color) {
        return editItem(name, props -> new Item(props.fireResistant()) {
                        @Override
                        public @NotNull Component getName(@NotNull ItemStack stack) {
                            return componentTranslatableIntColor(this.getDescriptionId(), color);
                        }},
                        color);
    }

    // ** CUSTOM METHOD - Luck items **
    public static DeferredItem<Item> luckItem(String name, int bookAmount,
                                              int enchPerBook, int enchLevel, int enchType,
                                              TagKey<Enchantment> tag, int textColor, @Nullable String textMessage) {
        return editItem(name, props -> new LuckItem(props.fireResistant(), bookAmount, enchPerBook,
                                                              enchLevel, enchType, tag, textColor, textMessage), textColor);
    }

    // ** CUSTOM METHOD - Level Charger items **
    public static DeferredItem<Item> levelChargerItem(String name, int amount,
                                                      @Nullable ResourceKey<Enchantment> enchantment, int color) {
        return editItem(name, props -> new LevelChargerItem(props.fireResistant(), amount, enchantment), color);
    }

    // ** CUSTOM METHOD - Fuel **
    public static DeferredItem<Item> fuelItem(String name, int burnTime, int color) {
        return editItem(name, props -> new FuelItem(props, burnTime, color), color);
    }

    // ** CUSTOM METHOD - Food **
    public static DeferredItem<Item> foodItem(String name, int nutrition,
                                              float saturation, Holder<MobEffect> effect,
                                              int duration, float chance, int color) {
        return editItem(name, props ->
                        new Item(props.food(new FoodProperties.Builder().nutrition(nutrition)
                                                                        .saturationModifier(saturation).build(),
                                            Consumables.defaultFood()
                                                       .onConsume(new ApplyStatusEffectsConsumeEffect(
                                                                  new MobEffectInstance(effect, duration),
                                                                  chance)).build())) {
                                 @Override
                                 public @NotNull Component getName(@NotNull ItemStack stack) {
                                     return componentTranslatableIntColor(this.getDescriptionId(), color);
                                 }},
                        color);
    }

    // ** CUSTOM METHOD - Trim Material **
    public static DeferredItem<Item> trimMaterialItem(String name,
                                                      ResourceKey<TrimMaterial> trim, int lore) {
        return editItem(name, props -> new Item(props.trimMaterial(trim)) {
                        @Override public @NotNull Component getName(@NotNull ItemStack stack) {
                            return componentTranslatableIntColor(this.getDescriptionId(), lore);
                        }},
                        lore);
    }

    // ** CUSTOM METHOD - Paxel tool **
    public static DeferredItem<Item> paxelItem(String name, ToolMaterial tool,
                                               float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new PaxelItem(tool, attackDamage, attackSpeed,
                                      props.fireResistant().repairable(tool.repairItems()), color), color);
    }

    // ** CUSTOM METHOD - Hammer tool **
    public static DeferredItem<Item> hammerItem(String name, ToolMaterial tool,
                                                float attackDamage, float attackSpeed,
                                                int radius, int color) {
        return editItem(name, props ->
                        new HammerItem(tool, attackDamage, attackSpeed,
                                       props.fireResistant().repairable(tool.repairItems()),
                                       radius, color, color), color);
    }

    // ** CUSTOM METHOD - Shovel tool **
    public static DeferredItem<Item> shovelItem(String name, ToolMaterial tool,
                                                float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new ShovelItem(tool, attackDamage, attackSpeed,
                                       props.fireResistant().repairable(tool.repairItems())) {
                                            @Override
                                            public @NotNull Component getName(@NotNull ItemStack stack) {
                                                return componentTranslatableIntColor(this.getDescriptionId(), color);
                                            }
                                       }, color);
    }

    // ** CUSTOM METHOD - Axe tool **
    public static DeferredItem<Item> axeItem(String name, ToolMaterial tool,
                                             float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new AxeItem(tool, attackDamage, attackSpeed,
                                    props.fireResistant().repairable(tool.repairItems())) {
                                         @Override
                                         public @NotNull Component getName(@NotNull ItemStack stack) {
                                             return componentTranslatableIntColor(this.getDescriptionId(), color);
                                         }
                                    }, color);
    }

    // ** CUSTOM METHOD - Hoe tool **
    public static DeferredItem<Item> hoeItem(String name, ToolMaterial tool,
                                             float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new HoeItem(tool, attackDamage, attackSpeed,
                                    props.fireResistant().repairable(tool.repairItems())) {
                                         @Override
                                         public @NotNull Component getName(@NotNull ItemStack stack) {
                                             return componentTranslatableIntColor(this.getDescriptionId(), color);
                                         }
                                    }, color);
    }

    // ** CUSTOM METHOD - Pickaxe tool **
    public static DeferredItem<Item> pickaxeItem(String name, ToolMaterial tool,
                                                 float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new Item(props.pickaxe(tool, attackDamage, attackSpeed)
                                           .fireResistant().repairable(tool.repairItems())) {
                                      @Override
                                      public @NotNull Component getName(@NotNull ItemStack stack) {
                                          return componentTranslatableIntColor(this.getDescriptionId(), color);
                                      }
                                 }, color);
    }

    // ** CUSTOM METHOD - Sword tool **
    public static DeferredItem<Item> swordItem(String name, ToolMaterial tool,
                                               float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new Item(props.sword(tool, attackDamage, attackSpeed)
                                      .fireResistant().repairable(tool.repairItems())) {
                                      @Override
                                      public @NotNull Component getName(@NotNull ItemStack stack) {
                                          return componentTranslatableIntColor(this.getDescriptionId(), color);
                                      }
                                 }, color);
    }

    // ** CUSTOM METHOD - Special SWORD tool **
    public static DeferredItem<Item> swordEffectItem(String name, ToolMaterial tool,
                                                     float attackDamage, float attackSpeed, int color) {
        return editItem(name, props ->
                        new SwordEffectItem(props, tool, attackDamage, attackSpeed, tool.repairItems(), color),
                        color);
    }

    // ** CUSTOM METHOD - Helmet armor **
    public static DeferredItem<Item> helmetArmor(String name,
                                                 ArmorMaterial armor, int color) {
        return editItem(name, props -> new ModArmorItem(props.humanoidArmor(armor, ArmorType.HELMET)
                                                                       .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Chestplate armor **
    public static DeferredItem<Item> chestplateArmor(String name,
                                                     ArmorMaterial armor, int color) {
        return editItem(name, props -> new ModArmorItem(props.humanoidArmor(armor, ArmorType.CHESTPLATE)
                                                                       .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Leggings armor **
    public static DeferredItem<Item> leggingsArmor(String name,
                                                   ArmorMaterial armor, int color) {
        return editItem(name, props -> new ModArmorItem(props.humanoidArmor(armor, ArmorType.LEGGINGS)
                                                                       .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Boots armor **
    public static DeferredItem<Item> bootsArmor(String name,
                                                ArmorMaterial armor, int color) {
        return editItem(name, props -> new ModArmorItem(props.humanoidArmor(armor, ArmorType.BOOTS)
                                                                       .fireResistant(), color), color);
    }

    // ** CUSTOM METHOD - Elytra armor **
    public static DeferredItem<Item> elytraArmor(String name, Holder<MobEffect> effectHolder,
                                                 ArmorMaterial armor, int effectAmplifier, int color) {
        return editItem(name, props ->
                        new ElytraPlusItem(effectHolder, effectAmplifier,
                                           props.fireResistant().durability(armor.durability()).rarity(Rarity.EPIC)
                                                .component(DataComponents.GLIDER, Unit.INSTANCE)
                                                .component(DataComponents.EQUIPPABLE,
                                                           Equippable.builder(EquipmentSlot.CHEST)
                                                                     .setEquipSound(armor.equipSound())
                                                                     .setAsset(armor.assetId())
                                                                     .setDamageOnHurt(false).build())
                                                .repairable(armor.repairIngredient())
                                                .humanoidArmor(armor, ArmorType.CHESTPLATE)),
                        color);
    }

    // ** CUSTOM METHOD - Bow tool **
    public static DeferredItem<Item> bowItem(String name,
                                             TagKey<Item> repair, int color) {
        return editItem(name, props ->
                        new BowItem(props.repairable(repair).stacksTo(1)) {
                                    @Override
                                    public @NotNull Component getName(@NotNull ItemStack stack) {
                                        return componentTranslatableIntColor(this.getDescriptionId(), color);
                                    }}, color);
    }

    // ** CUSTOM METHOD - Hammer Bow tool **
    public static DeferredItem<Item> hammerBowItem(String name, TagKey<Item> repair,
                                                   int radius, int depth, int color) {
        return editItem(name, props -> new MinerBowItem(props.repairable(repair), radius, depth), color);
    }

    // ** CUSTOM METHOD - Horse armor **
    public static DeferredItem<Item> horseArmorItem(String name,
                                                    ArmorMaterial armorMaterial, int color) {
        return editItem(name, props ->
                        new Item(props.stacksTo(1).horseArmor(armorMaterial)) {
                                 @Override
                                 public @NotNull Component getName(@NotNull ItemStack stack) {
                                     return componentTranslatableIntColor(this.getDescriptionId(), color);
                                 }},
                                 color);
    }

    // ** CUSTOM METHOD - Shield tool **
    public static DeferredItem<Item> shieldItem(String name, TagKey<Item> repair,
                                                int durability, int color) {
        return editItem(name, props ->
                        new ShieldItem(props.durability(durability)
                                            .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                                            .repairable(repair).equippableUnswappable(EquipmentSlot.OFFHAND)
                                            .component(DataComponents.BLOCKS_ATTACKS,
                                                       new BlocksAttacks(0.25F, 1.0F,
                                                                         List.of(new BlocksAttacks.DamageReduction(
                                                                                 90.0F,
                                                                                 Optional.empty(), 0.0F, 1.0F)),
                                                       new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                                       Optional.of(DamageTypeTags.BYPASSES_SHIELD),
                                                       Optional.of(SoundEvents.SHIELD_BLOCK),
                                                       Optional.of(SoundEvents.SHIELD_BREAK)))
                                            .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)) {
                                       @Override
                                       public @NotNull Component getName(@NotNull ItemStack stack) {
                                           DyeColor dyecolor = stack.get(DataComponents.BASE_COLOR);
                                           String dyeName = this.descriptionId + ".";
                                           Component component;
                                           if (dyecolor != null) {
                                               component = componentTranslatableIntColor(dyeName + dyecolor.getName(),
                                                                                         dyecolor.getTextColor());
                                           }
                                           else { component = componentTranslatableIntColor(this.getDescriptionId(), color); }
                                           return component;
                                       }},
                                       color);
    }

    // ** CUSTOM METHOD - Ores Smithing Upgrade Template **
    public static DeferredItem<Item> oresSmithingTemplate(String name, String ore,
                                                          int lore) {
        String smithingName = "smithing_template." + ore + "_upgrade.";
        return editItem(name, props ->
                        new OresSmithingTemplateItem(lines(smithingName + "applies_to", lore),
                                                     lines(smithingName + "ingredients", lore),
                                                     lines(smithingName + "base_slot_description", lore),
                                                     lines(smithingName + "additions_slot_description", lore),
                                                     createOresUpgradeIconList(), createOresUpgradeMaterialList(),
                                                     props.fireResistant().rarity(Rarity.EPIC)), lore);
    }

    // ** CUSTOM METHOD - Ores Armor Trim Smithing Template **
    public static DeferredItem<Item> oresArmorTrimSmithingTemplate(String name, String ore,
                                                                   int lore) {
        String smithingName = "smithing_template." + ore + ".armor_trim.";
        return editItem(name, props ->
                        new OresSmithingTemplateItem(lines(smithingName + "applies_to", lore),
                                                     lines(smithingName + "ingredients", lore),
                                                     lines(smithingName + "base_slot_description", lore),
                                                     lines(smithingName + "additions_slot_description", lore),
                                                     createOresTrimmableArmorIconList(),
                                                     createOresTrimmableMaterialIconList(), props.rarity(Rarity.UNCOMMON)), lore);
    }

    // CUSTOM METHOD - (CUSTOM classes) Registry all custom ITEMS
    private static <I extends Item> DeferredItem<I> customItem(String name,
                                                               Function<Properties, ? extends I> item,
                                                               UnaryOperator<Properties> properties, int lore) {
        List<Component> itemLore = List.of(componentTranslatableIntColor("tooltip.item.top." + name, lore));
        return ITEMS.registerItem(name, item, props ->
                                  properties.apply(props.component(DataComponents.LORE, new ItemLore(itemLore))));
    }

    // CUSTOM METHOD - (CUSTOM classes) Registry all custom ITEMS
    public static <I extends Item> DeferredItem<I> editItem(String name,
                                                            Function<Item.Properties, ? extends I> item, int lore) {
        List<Component> itemLore = List.of(componentTranslatableIntColor("tooltip.item." + top + name, lore));
        return ITEMS.registerItem(name, item, props -> props.component(DataComponents.LORE, new ItemLore(itemLore)));
    }

    // CUSTOM METHOD - (CUSTOM classes) Registry all custom ITEMS
    public static <I extends Item> DeferredItem<I> shiftItem(String name,
                                                             Function<Item.Properties, ? extends I> item) {
        return ITEMS.registerItem(name, item, props -> props);
    }

    // CUSTOM METHOD - Registry all items on event bus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}