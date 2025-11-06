package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.block.ModBlocks;
import net.karen.top.effect.ModEffects;
import net.karen.top.fluid.ModFluidTypes;
import net.karen.top.fluid.ModFluids;
import net.karen.top.item.ModItems;
import net.karen.top.item.custom.FuelItem;
import net.karen.top.item.custom.HammerItem;
import net.karen.top.worldgen.biome.ModBiomes;
import net.karen.top.worldgen.dimension.ModDimensions;
import net.minecraft.core.Holder;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import static net.karen.top.util.ChatUtils.*;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput packOutput) {
        super(packOutput, Top.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // ** CUSTOM ITEMS **
        // ** CUSTOM ore items **
        // BISMUTH
        addItem(ModItems.BISMUTH, "Bismuth");
        addItem(ModItems.RAW_BISMUTH, "Raw Bismuth");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE, "Alexandrite");
        addItem(ModItems.RAW_ALEXANDRITE, "Raw Alexandrite");
        // PINK
        addItem(ModItems.PINK, "Pink");

        // ** CUSTOM ADVANCED ITEMS **
        addItem(ModItems.GROWTH, "Growth");
        addItem(ModItems.LEVEL_CHARGER_GENERIC_PLUS, "Level Charger Generic Plus");
        addItem(ModItems.LEVEL_CHARGER_GENERIC_MINUS, "Level Charger Generic Minus");
        addItem(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE, "Level Charger Specif Minus Fortune");
        addItem(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE, "Level Charger Specif Plus Fortune");
        addItem(ModItems.SPECIAL_BOTTLE, "Special Bottle");
        addItem(ModItems.CHISEL, "Chisel");
        addItem(ModItems.DATA_TABLET, "Data Tablet");
        addItem(ModItems.METAL_DETECTOR, "Metal Detector");
        addItem(ModItems.ULTRA_COMPACTOR, "Ultra Compactor");
        addItem(ModItems.PINK_ULTRA_COMPACTOR, "Pink Ultra Compactor");
        addItem(ModItems.RESTORE, "Restore");
        addItem(ModItems.FARMER, "Farmer");
        addItem(ModItems.LUCK_GENERAL, "Luck General");
        addItem(ModItems.LUCK_PICKAXE, "Luck Pickaxe");
        addItem(ModItems.LUCK_WEAPON, "Luck Weapon");

        // ** CUSTOM MUSIC DISC **
        addItem(ModItems.BAR_BRAWL_MUSIC_DISC, "Bar Brawl Music Disc");

        // ** CUSTOM Foods **
        addItem(ModItems.COFFEE, "Coffee");

        // ** CUSTOM Tools **
        // BISMUTH
        addItem(ModItems.BISMUTH_SWORD, "Bismuth Sword");
        addItem(ModItems.BISMUTH_PICKAXE, "Bismuth Pickaxe");
        addItem(ModItems.BISMUTH_SHOVEL, "Bismuth Shovel");
        addItem(ModItems.BISMUTH_AXE, "Bismuth Axe");
        addItem(ModItems.BISMUTH_HOE, "Bismuth Hoe");
        addItem(ModItems.KAUPEN_BOW, "Kaupen Bow");
        addItem(ModItems.MINER_BOW, "Miner Bow");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE_SWORD, "Alexandrite Sword");
        addItem(ModItems.ALEXANDRITE_PICKAXE, "Alexandrite Pickaxe");
        addItem(ModItems.ALEXANDRITE_SHOVEL, "Alexandrite Shovel");
        addItem(ModItems.ALEXANDRITE_AXE, "Alexandrite Axe");
        addItem(ModItems.ALEXANDRITE_HOE, "Alexandrite Hoe");
        addItem(ModItems.ALEXANDRITE_BOW, "Alexandrite Bow");
        // EMERALD
        addItem(ModItems.EMERALD_SWORD, "Emerald Sword");
        addItem(ModItems.EMERALD_PICKAXE, "Emerald Pickaxe");
        addItem(ModItems.EMERALD_SHOVEL, "Emerald Shovel");
        addItem(ModItems.EMERALD_AXE, "Emerald Axe");
        addItem(ModItems.EMERALD_HOE, "Emerald Hoe");

        // ** CUSTOM Armors **
        // BISMUTH
        addItem(ModItems.BISMUTH_HELMET, "Bismuth Helmet");
        addItem(ModItems.BISMUTH_CHESTPLATE, "Bismuth Chestplate");
        addItem(ModItems.BISMUTH_LEGGINGS, "Bismuth Leggings");
        addItem(ModItems.BISMUTH_BOOTS, "Bismuth Boots");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE_HELMET, "Alexandrite Helmet");
        addItem(ModItems.ALEXANDRITE_CHESTPLATE, "Alexandrite Chestplate");
        addItem(ModItems.ALEXANDRITE_LEGGINGS, "Alexandrite Leggings");
        addItem(ModItems.ALEXANDRITE_BOOTS, "Alexandrite Boots");
        // PINK
        addItem(ModItems.PINK_HELMET, "Pink Helmet");
        addItem(ModItems.PINK_CHESTPLATE, "Pink Chestplate");
        addItem(ModItems.PINK_LEGGINGS, "Pink Leggings");
        addItem(ModItems.PINK_BOOTS, "Pink Boots");
        // COPPER
        addItem(ModItems.ULTRA_COPPER_HELMET, "Ultra Copper Helmet");
        addItem(ModItems.ULTRA_COPPER_CHESTPLATE, "Ultra Copper Chestplate");
        addItem(ModItems.ULTRA_COPPER_LEGGINGS, "Ultra Copper Leggings");
        addItem(ModItems.ULTRA_COPPER_BOOTS, "Ultra Copper Boots");
        // LAPIS LAZULI
        addItem(ModItems.LAPIS_LAZULI_HELMET, "Lapis Lazuli Helmet");
        addItem(ModItems.LAPIS_LAZULI_CHESTPLATE, "Lapis Lazuli Chestplate");
        addItem(ModItems.LAPIS_LAZULI_LEGGINGS, "Lapis Lazuli Leggings");
        addItem(ModItems.LAPIS_LAZULI_BOOTS, "Lapis Lazuli Boots");
        // REDSTONE
        addItem(ModItems.REDSTONE_HELMET, "Redstone Helmet");
        addItem(ModItems.REDSTONE_CHESTPLATE, "Redstone Chestplate");
        addItem(ModItems.REDSTONE_LEGGINGS, "Redstone Leggings");
        addItem(ModItems.REDSTONE_BOOTS, "Redstone Boots");
        // EMERALD
        addItem(ModItems.EMERALD_HELMET, "Emerald Helmet");
        addItem(ModItems.EMERALD_CHESTPLATE, "Emerald Chestplate");
        addItem(ModItems.EMERALD_LEGGINGS, "Emerald Leggings");
        addItem(ModItems.EMERALD_BOOTS, "Emerald Boots");

        // ** CUSTOM Shovel **
        addItem(ModItems.PINK_SHOVEL, "Pink Shovel");
        addItem(ModItems.ULTRA_COPPER_SHOVEL, "Ultra Copper Shovel");
        addItem(ModItems.LAPIS_LAZULI_SHOVEL, "Lapis Lazuli Shovel");
        addItem(ModItems.REDSTONE_SHOVEL, "Redstone Shovel");

        // ** CUSTOM Axe **
        addItem(ModItems.PINK_AXE, "Pink Axe");
        addItem(ModItems.ULTRA_COPPER_AXE, "Ultra Copper Axe");
        addItem(ModItems.LAPIS_LAZULI_AXE, "Lapis Lazuli Axe");
        addItem(ModItems.REDSTONE_AXE, "Redstone Axe");

        // ** CUSTOM Hoe **
        addItem(ModItems.PINK_HOE, "Pink Hoe");
        addItem(ModItems.ULTRA_COPPER_HOE, "Ultra Copper Hoe");
        addItem(ModItems.LAPIS_LAZULI_HOE, "Lapis Lazuli Hoe");
        addItem(ModItems.REDSTONE_HOE, "Redstone Hoe");

        // ** CUSTOM Pickaxe **
        addItem(ModItems.PINK_PICKAXE, "Pink Pickaxe");
        addItem(ModItems.ULTRA_COPPER_PICKAXE, "Ultra Copper Pickaxe");
        addItem(ModItems.LAPIS_LAZULI_PICKAXE, "Lapis Lazuli Pickaxe");
        addItem(ModItems.REDSTONE_PICKAXE, "Redstone Pickaxe");

        // ** CUSTOM Pickaxe **
        addItem(ModItems.PINK_SWORD, "Pink Sword");
        addItem(ModItems.ULTRA_COPPER_SWORD, "Ultra Copper Sword");
        addItem(ModItems.LAPIS_LAZULI_SWORD, "Lapis Lazuli Sword");
        addItem(ModItems.REDSTONE_SWORD, "Redstone Sword");

        // ** CUSTOM Elytra armor **
        // DIAMOND
        elytra(ModItems.DIAMOND_ELYTRA, "Diamond Elytra");
        // EMERALD
        elytra(ModItems.EMERALD_ELYTRA, "Emerald Elytra");

        // ** CUSTOM Horse armor **
        // BISMUTH
        addItem(ModItems.BISMUTH_HORSE_ARMOR, "Bismuth Horse Armor");
        // ALEXANDRITE
        addItem(ModItems.ALEXANDRITE_HORSE_ARMOR, "Alexandrite Horse Armor");

        // ** CUSTOM ARMOR TRIM SMITHING TEMPLATE **
        oresArmorTrimSmithingTemplate(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE, "kaupen");

        // ** CUSTOM ORES UPGRADE SMITHING TEMPLATE **
        oresUpgradeSmithingTemplate(ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE, "copper", false);

        // ** CUSTOM TRIM MATERIAL **
        add("trim_material." + top + "bismuth", "Bismuth Material");
        add("trim_material." + top + "alexandrite", "Alexandrite Material");
        add("trim_material." + top + "pink", "Pink Material");

        // ** CUSTOM TRIM PATTERN **
        add("trim_pattern." + top + "kaupen", "Kaupen Armor Trim");

        // ** CUSTOM Painting Variant **
        painting("World", "NanoAttack");
        painting("Shrimp", "NanoAttack");
        painting("Saw Them", "NanoAttack");

        // ** CUSTOM seeds **
        addItem(ModItems.RADISH, "Radish");
        addItem(ModItems.RADISH_SEEDS, "Radish Seeds");
        addItem(ModItems.KOHLRABI, "Kohlrabi");
        addItem(ModItems.KOHLRABI_SEEDS, "Kohlrabi Seeds");
        addItem(ModItems.CATTAIL, "Cattail");
        addItem(ModItems.CATTAIL_SEEDS, "Cattail Seeds");

        // ** CUSTOM fuels **
        addItem(ModItems.FROSTFIRE_ICE, "Frostfire Ice");
        addItem(ModItems.STARLIGHT_ASHES, "Starlight Ashes");
        addItem(ModItems.PEAT_BRICK, "Peat Brick");

        // ** CUSTOM Bush **
        addItem(ModItems.GOJI_BERRIES, "Goji Berries");

        // ** CUSTOM Mob **
        // GECKO
        addItem(ModItems.GECKO_SPAWN_EGG, "Gecko Spawn Egg");
        // RHINO
        addItem(ModItems.RHINO_SPAWN_EGG, "Rhino Spawn Egg");

        // ** CUSTOM Throwable Projectiles **
        addItem(ModItems.TOMAHAWK, "Tomahawk");
        addItem(ModItems.TORCH_BALL, "Torch Ball");
        addItem(ModItems.BOUNCY_BALLS, "Bouncy Balls");
        addItem(ModItems.BOUNCY_BALLS_PARTICLES, "Bouncy Balls Particles");
        addItem(ModItems.DICE_ITEM, "Dice Item");

        // ** CUSTOM Animated Textures **
        addItem(ModItems.RADIATION_STAFF, "Radiation Staff");

        // ** CUSTOM Fishing Rod **
        addItem(ModItems.TOP_FISHING_ROD, topUpper + " Fishing Rod");

        // ** CUSTOM Shield **
        shield(ModItems.ALEXANDRITE_SHIELD, "Alexandrite Shield");

        // ** CUSTOM Fluid **
        addItem(ModFluids.SOAP_WATER_BUCKET, "Soap Water Bucket");
        add(ModFluidTypes.SOAP_WATER_FLUID_TYPE.get().getDescriptionId(), "Soap Water Fluid");

        // ** CUSTOM Boat **
        addItem(ModItems.WALNUT_BOAT, "Walnut Boat");
        addItem(ModItems.WALNUT_CHEST_BOAT, "Walnut Chest Boat");

        // ** CUSTOM BLOCKS **
        // ** CUSTOM ores **
        // BISMUTH
        blockLang(ModBlocks.BISMUTH_BLOCK, "§6");
        blockLang(ModBlocks.BISMUTH_ORE, "§6");
        blockLang(ModBlocks.BISMUTH_DEEPSLATE_ORE, "§6");
        blockLang(ModBlocks.BISMUTH_END_ORE, "§6");
        blockLang(ModBlocks.BISMUTH_NETHER_ORE, "§6");
        // ALEXANDRITE
        blockLang(ModBlocks.ALEXANDRITE_BLOCK, "§b");
        blockLang(ModBlocks.RAW_ALEXANDRITE_BLOCK, "§b");
        blockLang(ModBlocks.ALEXANDRITE_ORE, "§b");
        blockLang(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, "§b");
        blockLang(ModBlocks.END_STONE_ALEXANDRITE_ORE, "§b");
        blockLang(ModBlocks.NETHER_ALEXANDRITE_ORE, "§b");

        // PINK
        blockLang(ModBlocks.PINK_BLOCK, "§d");
        blockLang(ModBlocks.PINK_ORE, "§d");
        blockLang(ModBlocks.DEEPSLATE_PINK_ORE, "§d");
        blockLang(ModBlocks.END_STONE_PINK_ORE, "§d");
        blockLang(ModBlocks.NETHER_PINK_ORE, "§d");

        // ** CUSTOM Advanced blocks **
        blockLang(ModBlocks.ENCHANT, "§2");
        blockLang(ModBlocks.DISENCHANT_INDIVIDUAL, "§5");
        blockLang(ModBlocks.DISENCHANT_GROUPED, "§3");
        blockLang(ModBlocks.MAGIC, "§4");
        blockLang(ModBlocks.TOP_ELEVATOR, "§8");
        blockLang(ModBlocks.TOP_GENERATOR, "§a");
        blockLang(ModBlocks.CRAFTING_PLUS, "§a");
        blockLang(ModBlocks.SOUND, "§8");

        // ** CUSTOM Block Families **
        // BISMUTH
        blockLang(ModBlocks.BISMUTH_BUTTON, "§6");
        blockLang(ModBlocks.BISMUTH_DOOR, "§6");
        blockLang(ModBlocks.BISMUTH_FENCE, "§6");
        blockLang(ModBlocks.BISMUTH_FENCE_GATE, "§6");
        blockLang(ModBlocks.BISMUTH_PRESSURE_PLATE, "§6");
        blockLang(ModBlocks.BISMUTH_SLAB, "§6");
        blockLang(ModBlocks.BISMUTH_STAIRS, "§6");
        blockLang(ModBlocks.BISMUTH_TRAPDOOR, "§6");
        blockLang(ModBlocks.BISMUTH_WALL, "§6");
        // ALEXANDRITE
        blockLang(ModBlocks.ALEXANDRITE_BUTTON, "§b");
        blockLang(ModBlocks.ALEXANDRITE_DOOR, "§b");
        blockLang(ModBlocks.ALEXANDRITE_FENCE, "§b");
        blockLang(ModBlocks.ALEXANDRITE_FENCE_GATE, "§b");
        blockLang(ModBlocks.ALEXANDRITE_PREASSURE_PLATE, "§b");
        blockLang(ModBlocks.ALEXANDRITE_SLABS, "§b");
        blockLang(ModBlocks.ALEXANDRITE_STAIRS, "§b");
        blockLang(ModBlocks.ALEXANDRITE_TRAPDOOR, "§b");
        blockLang(ModBlocks.ALEXANDRITE_WALL, "§b");

        // ** CUSTOM glass block **
        blockLang(ModBlocks.FORCED_STAINED_GLASS, "§a");
        blockLang(ModBlocks.FORCED_STAINED_GLASS_PANE, "§a");

        // ** CUSTOM Blockstate block **
        blockLang(ModBlocks.BISMUTH_LAMP, "§6");
        blockLang(ModBlocks.ALEXANDRITE_LAMP, "§b");

        // ** CUSTOM Crop block **
        // One height
        blockLang(ModBlocks.RADISH_CROP, "§2");
        blockLang(ModBlocks.KOHLRABI_CROP, "§2");
        // Two height
        blockLang(ModBlocks.CATTAIL_CROP, "§6");

        // ** CUSTOM Bush block **
        blockLang(ModBlocks.GOJI_BERRY_BUSH, "§1");

        // ** CUSTOM Log block **
        // BLOODWOOD
        blockLang(ModBlocks.BLOODWOOD_LOG, "§4");
        blockLang(ModBlocks.BLOODWOOD_PLANKS, "§4");
        blockLang(ModBlocks.BLOODWOOD_WOOD, "§4");
        blockLang(ModBlocks.STRIPPED_BLOODWOOD_LOG, "§4");
        blockLang(ModBlocks.STRIPPED_BLOODWOOD_WOOD, "§4");
        blockLang(ModBlocks.BLOODWOOD_LEAVES, "§4");
        blockLang(ModBlocks.BLOODWOOD_SAPLING, "§4");

        // WALNUT
        blockLang(ModBlocks.WALNUT_LOG, "§e");
        blockLang(ModBlocks.WALNUT_PLANKS, "§e");
        blockLang(ModBlocks.WALNUT_WOOD, "§e");
        blockLang(ModBlocks.STRIPPED_WALNUT_LOG, "§e");
        blockLang(ModBlocks.STRIPPED_WALNUT_WOOD, "§e");
        blockLang(ModBlocks.WALNUT_LEAVES, "§e");
        blockLang(ModBlocks.WALNUT_SAPLING, "§e");

        // ** CUSTOM colored blocks **
        blockLang(ModBlocks.COLORED_LEAVES, "§2");

        // ** CUSTOM Sign and Hanging sign **
        blockLang(ModBlocks.WALNUT_SIGN, "");
        blockLang(ModBlocks.WALNUT_HANGING_SIGN, "");
        blockLang(ModBlocks.WALNUT_WALL_SIGN, "");
        blockLang(ModBlocks.WALNUT_WALL_HANGING_SIGN, "");

        // ** CUSTOM sittable block model **
        blockLang(ModBlocks.CHAIR, "§e");

        // ** CUSTOM block entity **
        blockLang(ModBlocks.PEDESTAL, "§8");
        blockLang(ModBlocks.GROWTH_CHAMBER, "§8");
        blockLang(ModBlocks.GEM_EMPOWERING_STATION, "§8");

        // ** CUSTOM Fluid block **
        blockLang(ModFluids.SOAP_WATER_BLOCK, "");

        // ** CUSTOM Ender Pearl blocks **
        blockLang(ModBlocks.ENDER_PEARL_BLOCK, "§3");
        blockLang(ModBlocks.GREEN_ENDER_PEARL_BLOCK, "§2");
        blockLang(ModBlocks.BLACK_ENDER_PEARL_BLOCK, "§8");
        blockLang(ModBlocks.MAGENTA_ENDER_PEARL_BLOCK, "§5");
        blockLang(ModBlocks.PURPLE_ENDER_PEARL_BLOCK, "§5");
        blockLang(ModBlocks.ORANGE_ENDER_PEARL_BLOCK, "§6");
        blockLang(ModBlocks.PINK_ENDER_PEARL_BLOCK, "§d");
        blockLang(ModBlocks.CYAN_ENDER_PEARL_BLOCK, "§7");
        blockLang(ModBlocks.BROWN_ENDER_PEARL_BLOCK, "§6");
        blockLang(ModBlocks.GRAY_ENDER_PEARL_BLOCK, "§8");
        blockLang(ModBlocks.RED_ENDER_PEARL_BLOCK, "§c");
        blockLang(ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK, "§a");
        blockLang(ModBlocks.YELLOW_ENDER_PEARL_BLOCK, "§e");
        blockLang(ModBlocks.BLUE_ENDER_PEARL_BLOCK, "§1");
        blockLang(ModBlocks.WHITE_ENDER_PEARL_BLOCK, "§f");

        // ** CUSTOM mob blocks **
        blockLang(ModBlocks.NETHER_STAR_BLOCK, "§e");
        blockLang(ModBlocks.GUNPOWDER_BLOCK, "§8");
        blockLang(ModBlocks.ROTTEN_FLESH_BLOCK, "§c");
        blockLang(ModBlocks.BLAZE_ROD_BLOCK, "§6");
        blockLang(ModBlocks.PHANTOM_MEMBRANE_BLOCK, "§8");
        blockLang(ModBlocks.STRING_BLOCK, "§f");
        blockLang(ModBlocks.SPIDER_EYE_BLOCK, "§c");
        blockLang(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK, "§4");
        blockLang(ModBlocks.SUGAR_BLOCK, "§7");
        blockLang(ModBlocks.SUGAR_CANE_BLOCK, "§a");

        // ** CUSTOM oxidizable blocks **
        blockLang(ModBlocks.RUBY_BLOCK, "§c");
        blockLang(ModBlocks.RUBY_BLOCK_1, "§c");
        blockLang(ModBlocks.RUBY_BLOCK_2, "§c");
        blockLang(ModBlocks.RUBY_BLOCK_3, "§c");
        blockLang(ModBlocks.WAXED_RUBY_BLOCK, "§c");
        blockLang(ModBlocks.WAXED_RUBY_BLOCK_1, "§c");
        blockLang(ModBlocks.WAXED_RUBY_BLOCK_2, "§c");
        blockLang(ModBlocks.WAXED_RUBY_BLOCK_3, "§c");

        // ** CUSTOM flowers and pot flowers **
        blockLang(ModBlocks.SNAPDRAGON, "§5");
        blockLang(ModBlocks.POTTED_SNAPDRAGON, "§5");

        // ** CUSTOM portal **
        blockLang(ModBlocks.KAUPEN_PORTAL, "§7");

        // ** CUSTOM furnace **
        blockLang(ModBlocks.KAUPEN_FURNACE, "§7");

        // ** CUSTOM block projectile **
        blockLang(ModBlocks.DICE, "§7");

        // ** CUSTOM CREATIVE TABS **
        add("creativetab." + top + "bismuth_items", topUpper + " Items");
        add("creativetab." + top + "bismuth_blocks", topUpper + " Blocks");

        // ** CUSTOM Top Enchantment names **
        enchantment("Lightning Striker",
                    "When applied on sword when player hits on entities appears lightning, " +
                    "but player receive damage if attacked.");

        enchantment("Auto Smelt",
                    "When applied on pickaxe transform all items that can be roasted on furnace.");

        enchantment("More Ores",
                    "When applied on pickaxe if a stone block is break has a percentage to receive ores.");

        enchantment("Block Fly",
                    "When applied on pickaxe if player has flying speed mining equals if player has on ground.");

        enchantment("Magnet",
                    "When applied on pickaxe all mined blocks automatically store on Player's inventory.");

        enchantment("Rainbow",
                    "When applied on pickaxe the broken ore is replaced and turned on an ore block.");

        enchantment("Immortal",
                    "When applied to tools, armor, etc., it prevents the loss of the item in cactus, TNT, lava, void, etc.");

        enchantment("Peaceful Mobs", "When applied on leggings armor all entities not attack Player.");

        enchantment("Lightstring", "When applied on bow increases bow loading speed.");

        enchantment("Glowing Mobs",
                    "When applied on helmet armor all entities are detected with X-Ray of mobs. " +
                    "(Press M key to activated or disabled)");

        enchantment("Magnetism",
                    "When applied on leggings armor searches for items and Experience Orbs on the ground within " +
                    "a radius and returns them to the Player's inventory.");

        enchantment("Xp Boost",
                    "When applied to tools, armor, etc. gain more experience orb when killing mobs, mining blocks, " +
                    "cooking items, walking, mining, attacking, etc.");

        enchantment("Unlock",
                    "When applied to tools, armor, etc., item is locked from being dropped " +
                    "until you press the V key to unlock the item drop.");

        enchantment("Glowing Blocks",
                    "When applied on helmet armor all ores blocks are detected with X-Ray of blocks. " +
                              "(Press G key to activated or disabled)");

        enchantment("Mobs Critical",
                    "When applied on sword when player attack all entities receive critical damage.");

        // ** CUSTOM DESCRIPTIONS **
        // ** CUSTOM MUSIC DISC **
        add("item." + top + "bar_brawl_music_disc.desc", "Bryan Tech - Bar Brawl (CC0)");

        // ** VANILLA ENCHANTMENTS **
        // MINING
        add("enchantment.minecraft.efficiency.desc",
            "When applied on axe, pickaxe, shovel or hoe increases mining speed.");
        add("enchantment.minecraft.silk_touch.desc",
            "When applied on axe, pickaxe, shovel or hoe receives the broken block.");
        add("enchantment.minecraft.fortune.desc",
            "When applied on axe, pickaxe, shovel or hoe receives bonus loot drops of ores.");

        // DURABILITY
        add("enchantment.minecraft.mending.desc",
            "When applied on tools or armor regenerates tools, equipments, etc. only player received XP.");
        add("enchantment.minecraft.unbreaking.desc",
            "When applied on tools or armor increases durability of tools, equipments, etc.");

        // ARMOR EXCLUSIVE
        add("enchantment.minecraft.protection.desc", "When applied on armor add bonus damage reduction.");
        add("enchantment.minecraft.fire_protection.desc", "When applied on armor add bonus damage reduction of fire.");
        add("enchantment.minecraft.blast_protection.desc",
            "When applied on armor add bonus damage reduction of explosions and fireworks.");
        add("enchantment.minecraft.projectile_protection.desc",
            "When applied on armor add bonus damage reduction of projectiles.");

        // CHESTPLATE
        add("enchantment.minecraft.thorns.desc", "When applied on armor if entities attacked player receives damage.");

        // HELMET
        add("enchantment.minecraft.respiration.desc", "When applied on helmet armor extending breathing time underwater.");
        add("enchantment.minecraft.aqua_affinity.desc", "When applied on helmet armor increases underwater mining speed.");

        // LEGGINGS
        add("enchantment.minecraft.swift_sneak.desc", "When applied on leggings armor walk more quickly while sneaking.");

        // BOOTS
        add("enchantment.minecraft.feather_falling.desc",
            "When applied on boots armor reduces fall damage the player takes, but it does not affect falling speed.");
        add("enchantment.minecraft.depth_strider.desc",
            "When applied on boots armor increases underwater movement speed.");
        add("enchantment.minecraft.frost_walker.desc",
            "When applied on boots armor creates frosted ice blocks when walking over water, " +
            "and causes the wearer to be immune to damage from certain blocks such as campfires and magma blocks " +
            "when stepped on, but not working with lava.");
        add("enchantment.minecraft.soul_speed.desc",
            "When applied on boots armor walk more quickly on soul sand and soul soil blocks.");

        // SWORD
        add("enchantment.minecraft.sharpness.desc", "When applied on sword or axe increases melee damage attack on entities.");

        add("enchantment.minecraft.smite.desc",
            "When applied on sword or axe increases damage dealt to undead mobs " +
            "also Skeleton, Zombie, Wither, Phantom, Zoglin, etc.");

        add("enchantment.minecraft.bane_of_arthropods.desc",
            "When applied on sword or axe increases damage to arthropod mobs also Spiders, Bees, Silverfish, Endermites, etc.");

        add("enchantment.minecraft.knockback.desc", "When applied on sword or axe increases knockback distance of entities.");

        add("enchantment.minecraft.fire_aspect.desc", "When applied on sword or axe an entity received fire attack when hit.");

        add("enchantment.minecraft.looting.desc", "When applied on sword or axe increases amount of drop loot of entities.");

        // SWEEPING EDGE ENCHANTMENT
        add("enchantment.minecraft.sweeping.desc", "When applied on sword or axe increases sweep attack damage on entities.");

        add("enchantment.minecraft.sweeping_edge.desc", "When applied on sword or axe increases sweep attack damage on entities.");

        // BOW
        add("enchantment.minecraft.power.desc", "When applied on bow increases arrow damage.");

        add("enchantment.minecraft.punch.desc",
            "When applied on bow increases an arrow's knockback, but it not affect damage dealt of arrows.");

        add("enchantment.minecraft.flame.desc", "When applied on bow shoots flaming arrows.");

        add("enchantment.minecraft.infinity.desc",
            "When applied on bow one arrow is needed to used the enchantment that prevents regular arrows " +
            "from being consumed when slot.");

        // FISHING ROD
        add("enchantment.minecraft.luck_of_the_sea.desc",
            "When applied on fishing rod increases luck while fishing to received enchantments, armors, tools, etc.");

        add("enchantment.minecraft.lure.desc", "When applied on fishing rod decreases the wait time for a bite on the hook.");

        // TRIDENT
        add("enchantment.minecraft.loyalty.desc", "When applied on trident causing it to return to the owner once thrown.");

        add("enchantment.minecraft.impaling.desc",
            "When applied on trident deal extra damage on each hit against aquatic mobs also axolotls, " +
            "dolphins, guardians, squid, turtles, all variants of fish, etc. Except drowned is an undead mob.");

        add("enchantment.minecraft.riptide.desc",
            "When applied on trident hurls the user in the direction the user is facing, but only when they are wet.");

        add("enchantment.minecraft.channeling.desc",
            "When applied on trident produces lightning when thrown at a mob or lightning rod while a thunderstorm is occurring.");

        // CROSSBOW
        add("enchantment.minecraft.quick_charge.desc", "When applied on crossbow quickly reloading a crossbow.");

        add("enchantment.minecraft.multishot.desc",
            "When applied on crossbow shoot three arrows or firework rockets at the cost of one.");

        add("enchantment.minecraft.piercing.desc", "When applied on crossbow causes arrows to pierce through entities.");

        // CURSE
        add("enchantment.minecraft.binding_curse.desc",
            "When applied on armor the player not remove the item of inventory.");

        add("enchantment.minecraft.vanishing_curse.desc",
            "When applied on tool or armor if player killed the item disappears of inventory.");

        // MACE
        add("enchantment.minecraft.breach.desc",
            "When applied on mace ignores 15 percent of armor damage reduction per level.");

        add("enchantment.minecraft.density.desc",
            "When applied on mace increases 0.5 additional damage per level for each block fallen with tool.");

        add("enchantment.minecraft.wind_burst.desc",
            "When applied on mace the player into the air seven blocks per level after performing a smash attack.");

        // ** CUSTOM player display screen messages **
        add("item.top.metal_detector.no_valuable_values", "§4No Valuables Found!");

        // ** CUSTOM Effects and Potions **
        // FLY effect + potion
//        add("effect.top.fly", "Fly");
//        add("item.minecraft.potion.effect.fly_potion", "Fly Potion");
//        add("item.minecraft.splash_potion.effect.fly_potion", "Fly Splash Potion");
//        add("item.minecraft.lingering_potion.effect.fly_potion", "Fly Lingering Potion");
//        add("item.minecraft.tipped_arrow.effect.fly_potion", "Arrow of Fly");

        // NOTHING effect + potion
//        add("effect.top.nothing", "Nothing");
//        add("item.minecraft.potion.effect.nothing_potion", "Nothing Potion");
//        add("item.minecraft.splash_potion.effect.nothing_potion", "Nothing Splash Potion");
//        add("item.minecraft.lingering_potion.effect.nothing_potion", "Nothing Lingering Potion");
//        add("item.minecraft.tipped_arrow.effect.nothing_potion", "Arrow of Nothing");

        // HASTE potion
//        add("item.minecraft.potion.effect.haste_potion", "Haste Potion");
//        add("item.minecraft.splash_potion.effect.haste_potion", "Haste Splash Potion");
//        add("item.minecraft.lingering_potion.effect.haste_potion", "Haste Lingering Potion");
//        add("item.minecraft.tipped_arrow.effect.haste_potion", "Arrow of Haste");

        // SLIMEY potion
//        add("effect.top.slimey", "Slimey");
//        add("item.minecraft.potion.effect.slimey_potion", "Slimey Potion");
//        add("item.minecraft.splash_potion.effect.slimey_potion", "Slimey Splash Potion");
//        add("item.minecraft.lingering_potion.effect.slimey_potion", "Slimey Lingering Potion");
//        add("item.minecraft.tipped_arrow.effect.slimey_potion", "Arrow of Slimey");

        // ** CUSTOM NETWORK message **
        add(top + "networking.failed", "Mccourse Mod network failed!");

        // ** CUSTOM KEY BINDING **
        add("key.category." + top + "main", itemLines(Top.MOD_ID));
        add("key." + top + "glowing_blocks", "Glowing Blocks");
        add("key." + top + "glowing_mobs", "Glowing Mobs");
        add("key." + top + "special_bottle_stored", "Special Bottle Stored");
        add("key." + top + "special_bottle_restored", "Special Bottle Restored");
        add("key." + top + "unlock", "Unlock");

        // ** CUSTOM mob **
        // GECKO
        add("entity." + top + "gecko", "Gecko");
        add("entity." + top + "blue_gecko", "Blue Gecko");
        add("entity." + top + "green_gecko", "Green Gecko");
        add("entity." + top + "pink_gecko", "Pink Gecko");
        add("entity." + top + "brown_gecko", "Brown Gecko");
        add("entity.minecraft.gecko", "Gecko");
        add("entity.minecraft.blue_gecko", "Blue Gecko");
        add("entity.minecraft.green_gecko", "Green Gecko");
        add("entity.minecraft.pink_gecko", "Pink Gecko");
        add("entity.minecraft.brown_gecko", "Brown Gecko");
        // RHINO
        add("entity." + top + "rhino", "Rhino");
        add("entity." + top + "white_rhino", "White Rhino");
        add("entity.minecraft.rhino", "Rhino");
        add("entity.minecraft.white_rhino", "White Rhino");

        // ** CUSTOM Throwable Projectiles **
        add("entity." + top + "tomahawk", "Tomahawk");
        add("entity.minecraft.tomahawk", "Tomahawk");
        add("entity." + top + "magic_projectile", "Magic Projectile");
        add("entity.minecraft.magic_projectile", "Magic Projectile");
        add("entity." + top + "miner_bow_arrow_entity", "Miner Bow Arrow Projectile");
        add("entity.minecraft.miner_bow_arrow_entity", "Miner Bow Arrow Projectile");

        // ** CUSTOM villager **
        add("entity.minecraft.villager.top.kaupenger", "Kaupenger");
        add("entity.minecraft.villager.top.soundmaster", "Soundmaster");

        // ** CUSTOM boat and chest boat**
        add("entity.minecraft.mod_chest_boat", "Chest Boat");

        // ** CUSTOM sounds **
        add("sounds.top.chisel_use", "Chisel Use");
        add("sounds.top.metal_detector_found_ore", "Metal Detector Found Ore");
        add("sounds.top.magic_block_break", "Magic Block Break");
        add("sounds.top.magic_block_step", "Magic Block Step");
        add("sounds.top.magic_block_place", "Magic Block Place");
        add("sounds.top.magic_block_hit", "Magic Block Hit");
        add("sounds.top.magic_block_fall", "Magic Block Fall");
        add("sounds.top.alexandrite_lamp_break", "Alexandrite Block Break");
        add("sounds.top.alexandrite_lamp_step", "Alexandrite Block Step");
        add("sounds.top.alexandrite_lamp_place", "Alexandrite Block Place");
        add("sounds.top.alexandrite_lamp_hit", "Alexandrite Block Hit");
        add("sounds.top.alexandrite_lamp_fall", "Alexandrite Block Fall");

        // ** CUSTOM block entity container **
        add("top.container.crafting", "");
        add("container.inventory", "");

        // ** CUSTOM DIMENSIONS **
        addDimension(ModDimensions.KAUPENDIM_LEVEL_KEY, "Kaupendim");

        // ** CUSTOM BIOMES **
        addBiome(ModBiomes.TEST_BIOME, "Test Biome");
        addBiome(ModBiomes.TEST_BIOME_2, "Test Biome 2");

        // ** CUSTOM ENCHANTMENT LEVEL **
        vanillaEnchantment();

        // ** CUSTOM CONFIGS **
        add(top + "configuration.title", topUpper + " Configs");
        add(top + "configuration.section." + top + "common.toml", topUpper + " Configs");
        add(top + "configuration.section." + top + "common.toml.title", topUpper + " Configs");
        add(top + "configuration.items", "Item List");
        add(top + "configuration.logDirtBlock", "Log Dirt Block");
        add(top + "configuration.magicNumberIntroduction", "Magic Number Text");
        add(top + "configuration.magicNumber", "Magic Number");

        // ** CUSTOM BLOCK DESCRIPTIONS **
        blockDescription(ModBlocks.BISMUTH_BLOCK, "Nine BISMUTH items compacted on block.");
        blockDescription(ModBlocks.BISMUTH_ORE, "Found on Overworld dimension.");
        blockDescription(ModBlocks.BISMUTH_DEEPSLATE_ORE, "Found on Overworld dimension.");
        blockDescription(ModBlocks.BISMUTH_NETHER_ORE, "Found on Nether dimension.");
        blockDescription(ModBlocks.BISMUTH_END_ORE, "Found on End dimension.");

        blockDescription(ModBlocks.ALEXANDRITE_BLOCK, "Nine ALEXANDRITE items compacted on block.");
        blockDescription(ModBlocks.RAW_ALEXANDRITE_BLOCK, "Nine RAW ALEXANDRITE items compacted on block.");
        blockDescription(ModBlocks.ALEXANDRITE_ORE, "Found on Overworld dimension.");
        blockDescription(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, "Found on Overworld dimension.");
        blockDescription(ModBlocks.NETHER_ALEXANDRITE_ORE, "Found on Nether dimension.");
        blockDescription(ModBlocks.END_STONE_ALEXANDRITE_ORE, "Found on End dimension.");

        blockDescription(ModBlocks.PINK_BLOCK, "Nine PINK items compacted on block.");
        blockDescription(ModBlocks.PINK_ORE, "Found on Overworld dimension.");
        blockDescription(ModBlocks.DEEPSLATE_PINK_ORE, "Found on Overworld dimension.");
        blockDescription(ModBlocks.NETHER_PINK_ORE, "Found on Nether dimension.");
        blockDescription(ModBlocks.END_STONE_PINK_ORE, "Found on End dimension.");

        blockDescription(ModBlocks.MAGIC, "This Block is quite §9MAGICAL§r.");
        blockDescription(ModBlocks.ENCHANT, "Combine armors, tools, etc. with enchanted book max 255 enchantment level.");
        blockDescription(ModBlocks.DISENCHANT_INDIVIDUAL,
                         "Remove armors, tools, etc. enchanted books and transform each enchantment " +
                         "on individual enchanted book.");
        blockDescription(ModBlocks.DISENCHANT_GROUPED,
                         "Remove armors, tools, etc. enchanted books and transform all enchantments " +
                         "an enchanted book.");

        blockDescription(ModBlocks.TOP_ELEVATOR, "Special block that Up and Down x blocks placed.");
        blockDescription(ModBlocks.TOP_GENERATOR, "Special block that generate all blocks.");

        blockDescription(ModBlocks.CRAFTING_PLUS, "Improved Crafting Table 7x7 size.");

        blockDescription(ModBlocks.BISMUTH_STAIRS, "Decorate stair block.");
        blockDescription(ModBlocks.BISMUTH_SLAB, "Decorate slab block.");
        blockDescription(ModBlocks.BISMUTH_PRESSURE_PLATE, "Decorate pressure plate block.");
        blockDescription(ModBlocks.BISMUTH_BUTTON, "Decorate button block.");
        blockDescription(ModBlocks.BISMUTH_FENCE, "Decorate fence block.");
        blockDescription(ModBlocks.BISMUTH_FENCE_GATE, "Decorate fence gate block.");
        blockDescription(ModBlocks.BISMUTH_WALL, "Decorate wall block.");
        blockDescription(ModBlocks.BISMUTH_DOOR, "Decorate door block.");
        blockDescription(ModBlocks.BISMUTH_TRAPDOOR, "Decorate trapdoor block.");

        blockDescription(ModBlocks.ALEXANDRITE_STAIRS, "Decorate stair block.");
        blockDescription(ModBlocks.ALEXANDRITE_SLABS, "Decorate slab block.");
        blockDescription(ModBlocks.ALEXANDRITE_PREASSURE_PLATE, "Decorate pressure plate block.");
        blockDescription(ModBlocks.ALEXANDRITE_BUTTON, "Decorate button block.");
        blockDescription(ModBlocks.ALEXANDRITE_FENCE, "Decorate fence block.");
        blockDescription(ModBlocks.ALEXANDRITE_FENCE_GATE, "Decorate fence gate block.");
        blockDescription(ModBlocks.ALEXANDRITE_WALL, "Decorate wall block.");
        blockDescription(ModBlocks.ALEXANDRITE_DOOR, "Decorate door block.");
        blockDescription(ModBlocks.ALEXANDRITE_TRAPDOOR, "Decorate trapdoor block.");

        blockDescription(ModBlocks.BISMUTH_LAMP, "Decorate lamp block.");
        blockDescription(ModBlocks.ALEXANDRITE_LAMP, "Decorate lamp block.");

        blockDescription(ModBlocks.BLOODWOOD_LOG, "Decorate log block.");
        blockDescription(ModBlocks.BLOODWOOD_WOOD, "Decorate wood block.");
        blockDescription(ModBlocks.STRIPPED_BLOODWOOD_LOG, "Decorate stripped log block.");
        blockDescription(ModBlocks.STRIPPED_BLOODWOOD_WOOD, "Decorate stripped wood block.");
        blockDescription(ModBlocks.BLOODWOOD_PLANKS, "Decorate planks block.");
        blockDescription(ModBlocks.BLOODWOOD_LEAVES, "Decorate leaves block.");
        blockDescription(ModBlocks.BLOODWOOD_SAPLING, "Decorate sapling block.");

        blockDescription(ModBlocks.WALNUT_LOG, "Decorate log block.");
        blockDescription(ModBlocks.WALNUT_WOOD, "Decorate wood block.");
        blockDescription(ModBlocks.STRIPPED_WALNUT_LOG, "Decorate stripped log block.");
        blockDescription(ModBlocks.STRIPPED_WALNUT_WOOD, "Decorate stripped wood block.");
        blockDescription(ModBlocks.WALNUT_PLANKS, "Decorate planks block.");
        blockDescription(ModBlocks.WALNUT_LEAVES, "Decorate leaves block.");
        blockDescription(ModBlocks.WALNUT_SAPLING, "Decorate sapling block.");

        blockDescription(ModBlocks.CHAIR, "Decorate sittable block.");
        blockDescription(ModBlocks.PEDESTAL, "Show anywhere items on pedestal.");
        blockDescription(ModBlocks.GROWTH_CHAMBER, "Extract your item into other item.");
        blockDescription(ModBlocks.GEM_EMPOWERING_STATION, "Transform your item into other item using energy.");
        blockDescription(ModBlocks.KAUPEN_FURNACE, "Improved Furnace with exclusive used fuels.");

        blockDescription(ModBlocks.FORCED_STAINED_GLASS, "Decorate stained glass block.");
        blockDescription(ModBlocks.FORCED_STAINED_GLASS_PANE, "Decorate stained glass pane block.");

        blockDescription(ModBlocks.SOUND, "Plays nice sounds when walking or right-clicking.");

        enderBlockDescription(List.of(ModBlocks.ENDER_PEARL_BLOCK, ModBlocks.GREEN_ENDER_PEARL_BLOCK,
                                      ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK, ModBlocks.BLACK_ENDER_PEARL_BLOCK,
                                      ModBlocks.MAGENTA_ENDER_PEARL_BLOCK, ModBlocks.PURPLE_ENDER_PEARL_BLOCK,
                                      ModBlocks.ORANGE_ENDER_PEARL_BLOCK, ModBlocks.PINK_ENDER_PEARL_BLOCK,
                                      ModBlocks.CYAN_ENDER_PEARL_BLOCK, ModBlocks.BROWN_ENDER_PEARL_BLOCK,
                                      ModBlocks.GRAY_ENDER_PEARL_BLOCK, ModBlocks.RED_ENDER_PEARL_BLOCK,
                                      ModBlocks.YELLOW_ENDER_PEARL_BLOCK, ModBlocks.BLUE_ENDER_PEARL_BLOCK,
                                      ModBlocks.WHITE_ENDER_PEARL_BLOCK));

        blockDescription(ModBlocks.NETHER_STAR_BLOCK, "Nine NETHER STAR items compacted on block.");
        blockDescription(ModBlocks.GUNPOWDER_BLOCK, "Nine GUNPOWDER items compacted on block.");
        blockDescription(ModBlocks.ROTTEN_FLESH_BLOCK, "Nine ROTTEN FLESH items compacted on block.");
        blockDescription(ModBlocks.BLAZE_ROD_BLOCK, "Nine BLAZE ROD items compacted on block.");
        blockDescription(ModBlocks.PHANTOM_MEMBRANE_BLOCK, "Nine PHANTOM MEMBRANE items compacted on block.");
        blockDescription(ModBlocks.STRING_BLOCK, "Nine STRING items compacted on block.");
        blockDescription(ModBlocks.SPIDER_EYE_BLOCK, "Nine SPIDER EYE items compacted on block.");
        blockDescription(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK, "Nine FERMENTED SPIDER EYE items compacted on block.");
        blockDescription(ModBlocks.SUGAR_BLOCK, "Nine SUGAR items compacted on block.");
        blockDescription(ModBlocks.SUGAR_CANE_BLOCK, "Nine SUGAR CANE items compacted on block.");

        blockDescription(ModBlocks.RUBY_BLOCK, "Ruby oxidizable UNAFFECTED block.");
        blockDescription(ModBlocks.RUBY_BLOCK_1, "Ruby oxidizable EXPOSED block.");
        blockDescription(ModBlocks.RUBY_BLOCK_2, "Ruby oxidizable WEATHERED block.");
        blockDescription(ModBlocks.RUBY_BLOCK_3, "Ruby oxidizable DEGRADED block.");

        blockDescription(ModBlocks.WAXED_RUBY_BLOCK, "Waxed Ruby oxidizable UNAFFECTED block.");
        blockDescription(ModBlocks.WAXED_RUBY_BLOCK_1, "Waxed Ruby oxidizable EXPOSED block.");
        blockDescription(ModBlocks.WAXED_RUBY_BLOCK_2, "Waxed Ruby oxidizable WEATHERED block.");
        blockDescription(ModBlocks.WAXED_RUBY_BLOCK_3, "Waxed Ruby oxidizable DEGRADED block.");

        blockDescription(ModBlocks.SNAPDRAGON, "Decorate Snapdragon flower block.");
        blockDescription(ModBlocks.POTTED_SNAPDRAGON, "Decorate Snapdragon on potted block.");

        blockDescription(ModBlocks.COLORED_LEAVES, "Decorate Snapdragon on potted block.");
        blockDescription(ModBlocks.KAUPEN_PORTAL, "Portal block that changed to KAUPENDIM dimension.");

        blockDescription(ModFluids.SOAP_WATER_BLOCK, "Liquid fluid block.");

        // ** CUSTOM ITEM DESCRIPTIONS **
        itemDescription(ModItems.BISMUTH, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        itemDescription(ModItems.RAW_BISMUTH, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        itemDescription(ModItems.ALEXANDRITE, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        itemDescription(ModItems.RAW_ALEXANDRITE, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        itemDescription(ModItems.PINK, "Found on Overworld, Nether, End and Kaupendim dimensions!");

        shiftDescription(ModItems.CHISEL, "Press §eSHIFT§r for more information.",
                         "This Item can chisel Blocks into Bricks");

        itemDescription(ModItems.RESTORE, "Restore wrong crafting table recipe!");
        itemDescription(ModItems.FARMER, "Improved Bone Meal grow tree, crops etc.");
        itemDescription(ModItems.CATTAIL, "Transform oxidizable blocks on waxed blocks.");

        itemDescription(ModItems.RADISH, "Tastes really great!");
        itemDescription(ModItems.KOHLRABI, "Tastes really great!");
        itemDescription(ModItems.COFFEE, "Wake up and have a chance to achieve night vision effect.");

        seedsDescription(List.of(ModItems.RADISH_SEEDS, ModItems.KOHLRABI_SEEDS,
                                 ModItems.GOJI_BERRIES, ModItems.CATTAIL_SEEDS));

        fuelDescription(List.of(ModItems.FROSTFIRE_ICE, ModItems.STARLIGHT_ASHES, ModItems.PEAT_BRICK));

        itemDescription(ModItems.BISMUTH_HELMET, "Armor.");
        itemDescription(ModItems.BISMUTH_CHESTPLATE, "Armor.");
        itemDescription(ModItems.BISMUTH_LEGGINGS, "Armor.");
        itemDescription(ModItems.BISMUTH_BOOTS, "Armor.");

        itemDescription(ModItems.ALEXANDRITE_HELMET, "Armor.");
        itemDescription(ModItems.ALEXANDRITE_CHESTPLATE, "Armor.");
        itemDescription(ModItems.ALEXANDRITE_LEGGINGS, "Armor.");
        itemDescription(ModItems.ALEXANDRITE_BOOTS, "Armor.");

        itemDescription(ModItems.PINK_HELMET, "Armor.");
        itemDescription(ModItems.PINK_CHESTPLATE, "Armor.");
        itemDescription(ModItems.PINK_LEGGINGS, "Armor.");
        itemDescription(ModItems.PINK_BOOTS, "Armor.");

        itemDescription(ModItems.ULTRA_COPPER_HELMET, "Armor.");
        itemDescription(ModItems.ULTRA_COPPER_CHESTPLATE, "Armor.");
        itemDescription(ModItems.ULTRA_COPPER_LEGGINGS, "Armor.");
        itemDescription(ModItems.ULTRA_COPPER_BOOTS, "Armor.");

        itemDescription(ModItems.LAPIS_LAZULI_HELMET, "Armor.");
        itemDescription(ModItems.LAPIS_LAZULI_CHESTPLATE, "Armor.");
        itemDescription(ModItems.LAPIS_LAZULI_LEGGINGS, "Armor.");
        itemDescription(ModItems.LAPIS_LAZULI_BOOTS, "Armor.");

        itemDescription(ModItems.REDSTONE_HELMET, "Armor.");
        itemDescription(ModItems.REDSTONE_CHESTPLATE, "Armor.");
        itemDescription(ModItems.REDSTONE_LEGGINGS, "Armor.");
        itemDescription(ModItems.REDSTONE_BOOTS, "Armor.");

        itemDescription(ModItems.EMERALD_HELMET, "Armor.");
        itemDescription(ModItems.EMERALD_CHESTPLATE, "Armor.");
        itemDescription(ModItems.EMERALD_LEGGINGS, "Armor.");
        itemDescription(ModItems.EMERALD_BOOTS, "Armor.");

        itemDescription(ModItems.BISMUTH_HORSE_ARMOR, "Horse armor.");
        itemDescription(ModItems.ALEXANDRITE_HORSE_ARMOR, "Horse armor.");

        itemDescription(ModItems.KAUPEN_BOW, "Bow.");
        itemDescription(ModItems.MINER_BOW, "Bow.");
        itemDescription(ModItems.ALEXANDRITE_BOW, "Bow.");

        itemDescription(ModItems.TOP_FISHING_ROD, "More faster than vanilla Fishing Rod.");

        itemDescription(ModItems.ALEXANDRITE_SHIELD, "Shield.");

        paxel(List.of(ModItems.BISMUTH_PAXEL, ModItems.ALEXANDRITE_PAXEL, ModItems.PINK_PAXEL,
                      ModItems.COPPER_PAXEL, ModItems.DIAMOND_PAXEL, ModItems.GOLD_PAXEL,
                      ModItems.IRON_PAXEL, ModItems.STONE_PAXEL, ModItems.WOODEN_PAXEL,
                      ModItems.NETHERITE_PAXEL, ModItems.LAPIS_LAZULI_PAXEL, ModItems.REDSTONE_PAXEL,
                      ModItems.EMERALD_PAXEL));

        hammer(List.of(ModItems.BISMUTH_HAMMER, ModItems.ALEXANDRITE_HAMMER, ModItems.PINK_HAMMER,
                                  ModItems.COPPER_HAMMER, ModItems.DIAMOND_HAMMER, ModItems.GOLD_HAMMER,
                                  ModItems.IRON_HAMMER, ModItems.STONE_HAMMER, ModItems.WOODEN_HAMMER,
                                  ModItems.NETHERITE_HAMMER, ModItems.LAPIS_LAZULI_HAMMER, ModItems.REDSTONE_HAMMER,
                                  ModItems.EMERALD_HAMMER));

        itemDescription(ModItems.BISMUTH_SHOVEL, "Shovel.");
        itemDescription(ModItems.ALEXANDRITE_SHOVEL, "Shovel.");
        itemDescription(ModItems.PINK_SHOVEL, "Shovel.");
        itemDescription(ModItems.ULTRA_COPPER_SHOVEL, "Shovel.");
        itemDescription(ModItems.LAPIS_LAZULI_SHOVEL, "Shovel.");
        itemDescription(ModItems.REDSTONE_SHOVEL, "Shovel.");
        itemDescription(ModItems.EMERALD_SHOVEL, "Shovel.");

        itemDescription(ModItems.BISMUTH_AXE, "Axe.");
        itemDescription(ModItems.ALEXANDRITE_AXE, "Axe.");
        itemDescription(ModItems.PINK_AXE, "Axe.");
        itemDescription(ModItems.ULTRA_COPPER_AXE, "Axe.");
        itemDescription(ModItems.LAPIS_LAZULI_AXE, "Axe.");
        itemDescription(ModItems.REDSTONE_AXE, "Axe.");
        itemDescription(ModItems.EMERALD_AXE, "Axe.");

        itemDescription(ModItems.BISMUTH_HOE, "Hoe.");
        itemDescription(ModItems.ALEXANDRITE_HOE, "Hoe.");
        itemDescription(ModItems.PINK_HOE, "Hoe.");
        itemDescription(ModItems.ULTRA_COPPER_HOE, "Hoe.");
        itemDescription(ModItems.LAPIS_LAZULI_HOE, "Hoe.");
        itemDescription(ModItems.REDSTONE_HOE, "Hoe.");
        itemDescription(ModItems.EMERALD_HOE, "Hoe.");

        itemDescription(ModItems.BISMUTH_PICKAXE, "Pickaxe.");
        itemDescription(ModItems.ALEXANDRITE_PICKAXE, "Pickaxe.");
        itemDescription(ModItems.PINK_PICKAXE, "Pickaxe.");
        itemDescription(ModItems.ULTRA_COPPER_PICKAXE, "Pickaxe.");
        itemDescription(ModItems.LAPIS_LAZULI_PICKAXE, "Pickaxe.");
        itemDescription(ModItems.REDSTONE_PICKAXE, "Pickaxe.");
        itemDescription(ModItems.EMERALD_PICKAXE, "Pickaxe.");

        itemDescription(ModItems.BISMUTH_SWORD, "Sword.");
        itemDescription(ModItems.ALEXANDRITE_SWORD, "Sword.");
        itemDescription(ModItems.PINK_SWORD, "Sword.");
        itemDescription(ModItems.ULTRA_COPPER_SWORD, "Sword.");
        itemDescription(ModItems.LAPIS_LAZULI_SWORD, "Sword.");
        itemDescription(ModItems.REDSTONE_SWORD, "Sword.");
        itemDescription(ModItems.EMERALD_SWORD, "Sword.");


        itemDescription(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE, "Armor Trim Smithing Template.");
        itemDescription(ModItems.COPPER_UPGRADE_SMITHING_TEMPLATE, "Upgrade Smithing Template.");

        add("tooltip." + top + "auto_smelt.tooltip",
            "When applied on pickaxe transform all items that can be roasted on furnace.");
        add("tooltip." + top + "more_ores.tooltip",
            "When applied on pickaxe if a stone block is break has a percentage to receive ores.");

        itemDescription(ModItems.BAR_BRAWL_MUSIC_DISC, "Jukebox music disc sound.");

        itemDescription(ModItems.GECKO_SPAWN_EGG, "Spawn Gecko entity.");
        itemDescription(ModItems.RHINO_SPAWN_EGG, "Spawn Rhino entity.");

        itemDescription(ModItems.TOMAHAWK, "Thunder when attacked entities!");
        itemDescription(ModItems.DICE_ITEM, "Spawn dice blocks!");
        itemDescription(ModItems.RADIATION_STAFF, "Spawn special particle!");

        itemDescription(ModItems.LEVEL_CHARGER_GENERIC_PLUS, "Increase all enchantment levels!");
        itemDescription(ModItems.LEVEL_CHARGER_GENERIC_MINUS, "Decrease all enchantment levels!");
        itemDescription(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE, "Increase Fortune enchantment level!");
        itemDescription(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE, "Decrease Fortune enchantment level!");

        itemDescription(ModItems.ULTRA_COMPACTOR, "Compact automatic all ores blocks!");
        itemDescription(ModItems.PINK_ULTRA_COMPACTOR, "Compact manually all ores blocks!");

        itemDescription(ModItems.SPECIAL_BOTTLE, "Restore or store your orb experience!");

        shiftDescription(ModItems.METAL_DETECTOR, "Press §eSHIFT§r for more Information.",
                         "§eRight Click on Blocks to find Valuables!");

        itemDescription(ModItems.DATA_TABLET,
                        "Store ore block founded on Overworld, Nether, End and Kaupendim dimensions!");

        itemDescription(ModItems.GROWTH, "Makes a baby animal an adult animal!");

        itemDescription(ModItems.WALNUT_SIGN, "Decorate sign block.");
        itemDescription(ModItems.WALNUT_HANGING_SIGN, "Decorate hanging sign block.");

        itemDescription(ModItems.TORCH_BALL, "Spawn TORCH blocks.");
        itemDescription(ModItems.BOUNCY_BALLS, "Teleport same as Ender Pearl without cooldown.");
        itemDescription(ModItems.BOUNCY_BALLS_PARTICLES, "Particle used on Bouncy Balls item.");

        itemDescription(ModItems.WALNUT_BOAT, "Travel with special boat block item.");
        itemDescription(ModItems.WALNUT_CHEST_BOAT, "Travel with special chest boat block item.");

        itemDescription(ModItems.LUCK_GENERAL, "Gain random general enchantments.");
        itemDescription(ModItems.LUCK_PICKAXE, "Gain random pickaxe enchantments.");
        itemDescription(ModItems.LUCK_WEAPON, "Gain random sword enchantments.");

        itemDescription(ModFluids.SOAP_WATER_BUCKET, "Bucket.");

        Map<Holder<MobEffect>, String> effects = new HashMap<>();
        // MCCOURSE MOD effects
        effects.put(ModEffects.FLY_EFFECT, "Player fly press twice SPACE clicks.");
        effects.put(ModEffects.NOTHING_EFFECT, "Never Warden spawns and Enderman angry when looking on eyes.");
        effects.put(ModEffects.SLIMEY_EFFECT, "Climbing on blocks, but speed decreases 20 percent.");

        // VANILLA effects
        effects.put(MobEffects.ABSORPTION, "Extra hearts on health bar.");
        effects.put(MobEffects.BAD_OMEN, "Transform on Raid Omen when Player is a village or Trial Omen if near " +
                                         "a non-ominous trial spawner.");
        effects.put(MobEffects.BLINDNESS, "Prevent sprinting and critical hits also impairs a Player's vision.");
        effects.put(MobEffects.CONDUIT_POWER, "Grants Water Breathing, Night Vision and Haste effects.");
        effects.put(MobEffects.DARKNESS, "Distorted vision when sculk shrieker activated or detection range of a Warden.");
        effects.put(MobEffects.DOLPHINS_GRACE, "Increases swing speed.");
        effects.put(MobEffects.FIRE_RESISTANCE, "Increases fire resistance damage.");
        effects.put(MobEffects.GLOWING, "Look \"hit box\" of mob entity positions through blocks.");
        effects.put(MobEffects.HASTE, "Increases 20 percent of attack speed.");
        effects.put(MobEffects.HEALTH_BOOST, "Increases maximum health points.");
        effects.put(MobEffects.HERO_OF_THE_VILLAGE, "Player exclusive effect when defeating a Raid, discount when trading and gifts.");
        effects.put(MobEffects.HUNGER, "Player hunger points decreases more rapidly.");
        effects.put(MobEffects.INFESTED, "Entity received attack damage spawn Silverfish.");
        effects.put(MobEffects.INSTANT_DAMAGE, "Lose three multiply 2 square effect level health points.");
        effects.put(MobEffects.INSTANT_HEALTH, "Restore two health points.");
        effects.put(MobEffects.INVISIBILITY, "Decreases mob normal detection range.");
        effects.put(MobEffects.JUMP_BOOST, "Increases jump height when pressed SPACE.");
        effects.put(MobEffects.LEVITATION, "Uncontrollably float upward by Shulker entity.");
        effects.put(MobEffects.LUCK, "Increases receiving better loot tables.");
        effects.put(MobEffects.MINING_FATIGUE, "Decreases 0.3 square effect level mining efficiency and 10 percent of attack speed.");
        effects.put(MobEffects.NAUSEA, "Player distortion as Nether Portal effect.");
        effects.put(MobEffects.NIGHT_VISION, "Player illumination anywhere location in darkness or underwater.");
        effects.put(MobEffects.OOZING, "Appears two medium Slime after death entities.");
        effects.put(MobEffects.POISON, "Similar as Wither effect, but Player's health is showing, more rapidly and not kill.");
        effects.put(MobEffects.RAID_OMEN, "Start a Raid.");
        effects.put(MobEffects.REGENERATION, "Restore health points for some time.");
        effects.put(MobEffects.RESISTANCE, "Increases resistance damage.");
        effects.put(MobEffects.SATURATION, "Decreases the need to eating and prevent death by hunger.");
        effects.put(MobEffects.SLOW_FALLING, "Player gravity is more slowly and avoid fall damage, " +
                                             "but prevents critical hits and smash attacks.");
        effects.put(MobEffects.SLOWNESS, "Decreases 15 percent of walking speed.");
        effects.put(MobEffects.SPEED, "Increases 20 percent of walking speed.");
        effects.put(MobEffects.STRENGTH, "Increases 3+ attack damage.");
        effects.put(MobEffects.TRIAL_OMEN, "Transform trial spawners to ominous trial spawners.");
        effects.put(MobEffects.UNLUCK, "Decreases getting better loot tables.");
        effects.put(MobEffects.WATER_BREATHING, "Increases breathing time on water.");
        effects.put(MobEffects.WEAKNESS, "Decreases attack damage.");
        effects.put(MobEffects.WEAVING, "Appears COBWEB upon death and reduces cobweb walk speed.");
        effects.put(MobEffects.WIND_CHARGED, "Release a burst of wind upon death.");
        effects.put(MobEffects.WITHER, "As Poison effect, but Player's health is black, more slowly and can kill.");
        potionDescription(effects);
    }

    // CUSTOM METHOD - Register Block and Block Item
    private void blockLang(Supplier<? extends Block> block, String color) {
        String blockId = block.get().getDescriptionId().replace("block.top.", "");
        // Sound -> One word
        String blockName = upperSpaceWord(blockId);
        String versionWithBlock = color + blockName + " Block";
        // Bismuth Block -> Two words
        String names = itemLines(splitWord(blockId));
        String versionWithoutBlock = color + names;
        // Block name -> Example: bismuth_block
        boolean hasSpace = block.get().getDescriptionId().contains("_");
        String blockPrefix = hasSpace ? versionWithoutBlock : versionWithBlock;
        // Format block name + block item name
        addBlock(block, blockPrefix);
        add("item.top." + blockId, blockPrefix);
    }

    // CUSTOM METHOD - Vanilla enchantment levels
    private void vanillaEnchantment() {
        for (int i = 1; i < 256; i++) { add("enchantment.level." + i, String.valueOf(i)); }
    }

    // CUSTOM METHOD - Ores Upgrade Smithing Template
    private void oresUpgradeSmithingTemplate(Supplier<? extends Item> item,
                                             String ore, boolean isIngot) {
        String oreName = upperSpaceWord(ore);
        String blockOrIngot = isIngot ? " Ingot" : " Block";
        String repairItem = oreName + blockOrIngot;
        addItem(item, oreName + " Upgrade Smithing Template");
        add("item." + top + "smithing_template." + ore + "_upgrade.additions_slot_description", "Add " + repairItem);
        add("item." + top + "smithing_template." + ore + "_upgrade.applies_to", oreName + " Equipment");
        add("item." + top + "smithing_template." + ore + "_upgrade.base_slot_description",
            "Add " + oreName + " armor, weapon or tool");
        add("item." + top + "smithing_template." + ore + "_upgrade.ingredients", repairItem);
    }

    // CUSTOM METHOD - Ores Armor Trim Smithing Upgrade
    private void oresArmorTrimSmithingTemplate(Supplier<? extends Item> item, String ore) {
        addItem(item, upperSpaceWord(ore) + " Armor Trim Smithing Template");
        add("item." + top + "smithing_template." + ore + ".armor_trim.additions_slot_description", "Add ingot or crystal");
        add("item." + top + "smithing_template." + ore + ".armor_trim.applies_to", "Armor");
        add("item." + top + "smithing_template." + ore + ".armor_trim.base_slot_description", "Add a piece of armor");
        add("item." + top + "smithing_template." + ore + ".armor_trim.ingredients", "Ingots & Crystals");
        add("item." + top + "smithing_template.applies_to", "Applies to:");
        add("item." + top + "smithing_template.ingredients", "Ingredients:");
        add("item." + top + "smithing_template", topUpper + " Smithing Template");
    }

    // CUSTOM METHOD - Shield
    private <I extends Item> void shield(DeferredItem<I> shield, String name) {
        addItem(shield, name);
        List<String> colors = List.of("black", "white", "blue", "brown", "cyan", "gray",
                                      "green", "light_blue", "light_gray", "lime", "magenta",
                                      "orange", "pink", "purple", "red", "yellow");
        for (String color : colors) {
            String dyeColor = itemLines(splitWord(upperSpaceWord(color)));
            add("item." + shield.getRegisteredName().replace(":", ".") + "." + color, dyeColor + " " + name);
        }
    }

    // CUSTOM METHOD - ELYTRA -> Register item + description
    private <I extends Item> void elytra(DeferredItem<I> elytra, String name) {
        addItem(elytra, name);
        add("item." + top + name.replace(" ", "_").toLowerCase() + "_broken", "Broken " + name);
        itemDescription(elytra, "Elytra.");
    }

    // CUSTOM METHOD - PAINTING
    private void painting(String title, String author) {
        add("painting." + top + title.toLowerCase().replace(" ", "_") + ".title", title);
        add("painting." + top + title.toLowerCase().replace(" ", "_") + ".author", author);
    }

    // CUSTOM METHOD - ENCHANTMENT
    private void enchantment(String name, String description) {
        add("enchantment." + top + name.replace(" ", "_").toLowerCase(), name);
        add("enchantment." + top + name.replace(" ", "_").toLowerCase() + ".desc", description);
    }

    // CUSTOM METHOD - Block description
    private <T extends Block> void blockDescription(DeferredBlock<T> block, String text) {
        add("tooltip.block." + block.getRegisteredName().replace(":", "."), text);
    }

    private <T extends Block> void enderBlockDescription(List<DeferredBlock<T>> block) {
        for (DeferredBlock<T> id : block) {
            blockDescription(id, "Decorate ender block.");
        }
    }

    // CUSTOM METHOD - Item description
    private <I extends Item> void itemDescription(DeferredItem<I> item, String text) {
        add("tooltip.item." + item.getRegisteredName().replace(":", "."), text);
    }

    private <I extends Item> void fuelDescription(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) {
            if (item.get() instanceof FuelItem fuelItem) {
                itemDescription(item, "§6Burn: §r§e" + fuelItem.getBurnTime());
            }
        }
    }

    private <I extends Item> void seedsDescription(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) { itemDescription(item, "Seeds."); }
    }

    private <I extends Item> void shiftDescription(DeferredItem<I> item,
                                                   String text, String shiftText) {
        itemDescription(item, text);
        add("tooltip.item." + item.getRegisteredName().replace(":", ".") + ".shift", shiftText);
    }

    // CUSTOM METHOD - HAMMER item + description
    private <I extends Item> void hammer(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) {
            if (item.get() instanceof HammerItem hammerItem) {
                addItem(item, itemLines(splitWord(item.getRegisteredName().replace(":", ".")
                                                                          .replace(top, ""))));
                int radius = hammerItem.getRadius() * 2 + 1;
                itemDescription(item, "§6Hammer breaks: §r" + radius + "§fx§r" + radius + " §6radius area.§r");
            }
        }
    }

    // CUSTOM METHOD - PAXEL item + description
    private <I extends Item> void paxel(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) {
            addItem(item, itemLines(splitWord(item.getRegisteredName().replace(":", ".")
                                                                      .replace(top, ""))));
            itemDescription(item, "Break blocks as Axe, Pickaxe or Shovel.");
        }
    }

    // CUSTOM METHOD - POTION item + description
    private void potionDescription(Map<Holder<MobEffect>, String> effects) {
        for (Map.Entry<Holder<MobEffect>, String> effect : effects.entrySet()) {
            String id = effect.getKey().getRegisteredName().replace(":", ".");
            String idName = id.replace(top, "").replace(" ", "_");
            String name = itemLines(splitWord(id.replace(top, "")));
            add("effect." + id, name);
            add("item.minecraft.potion.effect." + idName + "_potion", name + " Potion");
            add("item.minecraft.splash_potion.effect." + idName + "_potion", name + " Splash Potion");
            add("item.minecraft.lingering_potion.effect." + idName + "_potion", name + " Lingering Potion");
            add("item.minecraft.tipped_arrow.effect." + idName + "_potion", "Arrow of " + name);
            add("tooltip.effect." + id, effect.getValue());
        }
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(upperSpaceWord(Top.MOD_ID))) + " Languages"; }
}