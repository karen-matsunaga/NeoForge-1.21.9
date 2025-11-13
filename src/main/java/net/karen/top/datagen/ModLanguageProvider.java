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
        // ** CUSTOM CREATIVE TABS **
        add("creativetab." + top + "bismuth_items", topUpper + " Items");
        add("creativetab." + top + "bismuth_blocks", topUpper + " Blocks");

        // ** CUSTOM Top Enchantment names **
        topEnch("Lightning Striker",
                "When applied on sword when player hits on entities appears lightning, but player receive damage if attacked.");
        topEnch("Auto Smelt", "When applied on pickaxe transform all items that can be roasted on furnace.");
        topEnch("More Ores", "When applied on pickaxe if a stone block is break has a percentage to receive ores.");
        topEnch("Block Fly", "When applied on pickaxe if player has flying speed mining equals if player has on ground.");
        topEnch("Magnet", "When applied on pickaxe all mined blocks automatically store on Player's inventory.");
        topEnch("Rainbow", "When applied on pickaxe the broken ore is replaced and turned on an ore block.");
        topEnch("Immortal",
                "When applied to tools, armor, etc., it prevents the loss of the item in cactus, TNT, lava, void, etc.");
        topEnch("Peaceful Mobs", "When applied on leggings armor all entities not attack Player.");
        topEnch("Lightstring", "When applied on bow increases bow loading speed.");
        topEnch("Glowing Mobs",
                "When applied on helmet armor all entities are detected with X-Ray of mobs. " +
                "(Press M key to activated or disabled)");
        topEnch("Magnetism",
                "When applied on leggings armor searches for items and Experience Orbs on the ground within " +
                "a radius and returns them to the Player's inventory.");
        topEnch("Xp Boost",
                "When applied to tools, armor, etc. gain more experience orb when killing mobs, mining blocks, " +
                "cooking items, walking, mining, attacking, etc.");
        topEnch("Unlock",
                "When applied to tools, armor, etc., item is locked from being dropped " +
                "until you press the V key to unlock the item drop.");
        topEnch("Glowing Blocks", "When applied on helmet armor all ores blocks are detected with X-Ray of blocks. " +
                                              "(Press G key to activated or disabled)");
        topEnch("Mobs Critical", "When applied on sword when player attack all entities receive critical damage.");
        topEnch("Dash", "When applied on boots armor receive impulse walk speed.");

        // ** VANILLA ENCHANTMENTS **
        // MINING
        ench("efficiency", "When applied on axe, pickaxe, shovel or hoe increases mining speed.");
        ench("silk_touch", "When applied on axe, pickaxe, shovel or hoe receives the broken block.");
        ench("fortune", "When applied on axe, pickaxe, shovel or hoe receives bonus loot drops of ores.");

        // DURABILITY
        ench("mending", "When applied on tools or armor regenerates tools, equipments, etc. only player received XP.");
        ench("unbreaking", "When applied on tools or armor increases durability of tools, equipments, etc.");

        // ARMOR EXCLUSIVE
        ench("protection", "When applied on armor add bonus damage reduction.");
        ench("fire_protection", "When applied on armor add bonus damage reduction of fire.");
        ench("blast_protection", "When applied on armor add bonus damage reduction of explosions and fireworks.");
        ench("projectile_protection", "When applied on armor add bonus damage reduction of projectiles.");

        // CHESTPLATE
        ench("thorns", "When applied on armor if entities attacked player receives damage.");

        // HELMET
        ench("respiration", "When applied on helmet armor extending breathing time underwater.");
        ench("aqua_affinity", "When applied on helmet armor increases underwater mining speed.");

        // LEGGINGS
        ench("swift_sneak", "When applied on leggings armor walk more quickly while sneaking.");

        // BOOTS
        ench("feather_falling",
             "When applied on boots armor reduces fall damage the player takes, but it does not affect falling speed.");
        ench("depth_strider", "When applied on boots armor increases underwater movement speed.");
        ench("frost_walker", "When applied on boots armor creates frosted ice blocks when walking over water, " +
             "and causes the wearer to be immune to damage from certain blocks such as campfires and magma blocks " +
             "when stepped on, but not working with lava.");
        ench("soul_speed", "When applied on boots armor walk more quickly on soul sand and soul soil blocks.");

        // SWORD
        ench("sharpness", "When applied on sword or axe increases melee damage attack on entities.");
        ench("smite", "When applied on sword or axe increases damage dealt to undead mobs " +
                                  "also Skeleton, Zombie, Wither, Phantom, Zoglin, etc.");
        ench("bane_of_arthropods", "When applied on sword or axe increases damage to arthropod mobs also " +
                                               "Spiders, Bees, Silverfish, Endermites, etc.");
        ench("knockback", "When applied on sword or axe increases knockback distance of entities.");
        ench("fire_aspect", "When applied on sword or axe an entity received fire attack when hit.");
        ench("looting", "When applied on sword or axe increases amount of drop loot of entities.");

        // SWEEPING EDGE ENCHANTMENT
        ench("sweeping", "When applied on sword or axe increases sweep attack damage on entities.");
        ench("sweeping_edge", "When applied on sword or axe increases sweep attack damage on entities.");

        // BOW
        ench("power", "When applied on bow increases arrow damage.");
        ench("punch", "When applied on bow increases an arrow's knockback, but it not affect damage dealt of arrows.");
        ench("flame", "When applied on bow shoots flaming arrows.");
        ench("infinity", "When applied on bow one arrow is needed to used the enchantment that prevents regular arrows " +
                                     "from being consumed when slot.");

        // FISHING ROD
        ench("luck_of_the_sea",
             "When applied on fishing rod increases luck while fishing to received enchantments, armors, tools, etc.");
        ench("lure", "When applied on fishing rod decreases the wait time for a bite on the hook.");

        // TRIDENT
        ench("loyalty", "When applied on trident causing it to return to the owner once thrown.");
        ench("impaling", "When applied on trident deal extra damage on each hit against aquatic mobs also axolotls, " +
                                    "dolphins, guardians, squid, turtles, all variants of fish, etc. Except drowned is an undead mob.");
        ench("riptide", "When applied on trident hurls the user in the direction the user is facing, " +
                                    "but only when they are wet.");
        ench("channeling", "When applied on trident produces lightning when thrown at a mob or " +
                                       "lightning rod while a thunderstorm is occurring.");

        // CROSSBOW
        ench("quick_charge", "When applied on crossbow quickly reloading a crossbow.");
        ench("multishot", "When applied on crossbow shoot three arrows or firework rockets at the cost of one.");
        ench("piercing", "When applied on crossbow causes arrows to pierce through entities.");

        // CURSE
        ench("binding_curse", "When applied on armor the player not remove the item of inventory.");
        ench("vanishing_curse", "When applied on tool or armor if player killed the item disappears of inventory.");

        // MACE
        ench("breach", "When applied on mace ignores 15 percent of armor damage reduction per level.");
        ench("density", "When applied on mace increases 0.5 additional damage per level for each block fallen with tool.");
        ench("wind_burst", "When applied on mace the player into the air seven blocks per level " +
                                       "after performing a smash attack.");

        // ** CUSTOM player display screen messages **
        add("item." + top + "metal_detector.no_valuable_values", "§4No Valuables Found!");

        // ** CUSTOM NETWORK message **
        add(top + "networking.failed", itemLines(Top.MOD_ID) + " network failed!");

        // ** CUSTOM KEY BINDING **
        add("key.category." + top + "main", itemLines(Top.MOD_ID));
        add("key." + top + "glowing_blocks", "Glowing Blocks");
        add("key." + top + "glowing_mobs", "Glowing Mobs");
        add("key." + top + "special_bottle_stored", "Special Bottle Stored");
        add("key." + top + "special_bottle_restored", "Special Bottle Restored");
        add("key." + top + "unlock", "Unlock");
        add("key." + top + "dash", "Dash");

        // ** CUSTOM mob **
        // GECKO
        add("entity.minecraft.gecko", "Gecko");
        add("entity.minecraft.blue_gecko", "Blue Gecko");
        add("entity.minecraft.green_gecko", "Green Gecko");
        add("entity.minecraft.pink_gecko", "Pink Gecko");
        add("entity.minecraft.brown_gecko", "Brown Gecko");
        // RHINO
        add("entity.minecraft.rhino", "Rhino");
        add("entity.minecraft.white_rhino", "White Rhino");

        // ** CUSTOM Throwable Projectiles **
        add("entity.minecraft.tomahawk", "Tomahawk");
        add("entity.minecraft.magic_projectile", "Magic Projectile");
        add("entity.minecraft.miner_bow_arrow_entity", "Miner Bow Arrow Projectile");

        // ** CUSTOM villager **
        add("entity.minecraft.villager." + top + "kaupenger", "Kaupenger");
        add("entity.minecraft.villager." + top + "soundmaster", "Soundmaster");

        // ** CUSTOM boat and chest boat**
        add("entity.minecraft.mod_chest_boat", "Chest Boat");

        // ** CUSTOM sounds **
        add("sounds." + top + "chisel_use", "Chisel Use");
        add("sounds." + top + "metal_detector_found_ore", "Metal Detector Found Ore");
        add("sounds." + top + "magic_block_break", "Magic Block Break");
        add("sounds." + top + "magic_block_step", "Magic Block Step");
        add("sounds." + top + "magic_block_place", "Magic Block Place");
        add("sounds." + top + "magic_block_hit", "Magic Block Hit");
        add("sounds." + top + "magic_block_fall", "Magic Block Fall");
        add("sounds." + top + "alexandrite_lamp_break", "Alexandrite Block Break");
        add("sounds." + top + "alexandrite_lamp_step", "Alexandrite Block Step");
        add("sounds." + top + "alexandrite_lamp_place", "Alexandrite Block Place");
        add("sounds." + top + "alexandrite_lamp_hit", "Alexandrite Block Hit");
        add("sounds." + top + "alexandrite_lamp_fall", "Alexandrite Block Fall");

        // ** CUSTOM block entity container **
        add(top + "container.crafting", "");
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

        // ** CUSTOM BLOCKS + DESCRIPTIONS **
        // ** CUSTOM ores **
        // BISMUTH
        block(ModBlocks.BISMUTH_BLOCK, "Nine BISMUTH items compacted on block.");
        block(ModBlocks.BISMUTH_ORE, "Found on Overworld dimension.");
        block(ModBlocks.BISMUTH_DEEPSLATE_ORE, "Found on Overworld dimension.");
        block(ModBlocks.BISMUTH_NETHER_ORE, "Found on Nether dimension.");
        block(ModBlocks.BISMUTH_END_ORE, "Found on End dimension.");

        // ALEXANDRITE
        block(ModBlocks.ALEXANDRITE_BLOCK, "Nine ALEXANDRITE items compacted on block.");
        block(ModBlocks.RAW_ALEXANDRITE_BLOCK, "Nine RAW ALEXANDRITE items compacted on block.");
        block(ModBlocks.ALEXANDRITE_ORE, "Found on Overworld dimension.");
        block(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, "Found on Overworld dimension.");
        block(ModBlocks.NETHER_ALEXANDRITE_ORE, "Found on Nether dimension.");
        block(ModBlocks.END_STONE_ALEXANDRITE_ORE, "Found on End dimension.");

        // PINK
        block(ModBlocks.PINK_BLOCK, "Nine PINK items compacted on block.");
        block(ModBlocks.PINK_ORE, "Found on Overworld dimension.");
        block(ModBlocks.DEEPSLATE_PINK_ORE, "Found on Overworld dimension.");
        block(ModBlocks.NETHER_PINK_ORE, "Found on Nether dimension.");
        block(ModBlocks.END_STONE_PINK_ORE, "Found on End dimension.");

        // ** CUSTOM Advanced blocks **
        block(ModBlocks.MAGIC, "This Block is quite §9MAGICAL§r.");
        block(ModBlocks.ENCHANT, "Combine armors, tools, etc. with enchanted book max 255 enchantment level.");
        block(ModBlocks.DISENCHANT_INDIVIDUAL,
              "Remove armors, tools, etc. enchanted books and transform each enchantment on individual enchanted book.");
        block(ModBlocks.DISENCHANT_GROUPED,
              "Remove armors, tools, etc. enchanted books and transform all enchantments an enchanted book.");
        block(ModBlocks.TOP_ELEVATOR, "Special block that Up and Down x blocks placed.");
        block(ModBlocks.TOP_GENERATOR, "Special block that generate all blocks.");
        block(ModBlocks.CRAFTING_PLUS, "Improved Crafting Table 7x7 size.");

        // ** CUSTOM Block Families **
        // BISMUTH
        block(ModBlocks.BISMUTH_STAIRS, "Decorate stair block.");
        block(ModBlocks.BISMUTH_SLAB, "Decorate slab block.");
        block(ModBlocks.BISMUTH_PRESSURE_PLATE, "Decorate pressure plate block.");
        block(ModBlocks.BISMUTH_BUTTON, "Decorate button block.");
        block(ModBlocks.BISMUTH_FENCE, "Decorate fence block.");
        block(ModBlocks.BISMUTH_FENCE_GATE, "Decorate fence gate block.");
        block(ModBlocks.BISMUTH_WALL, "Decorate wall block.");
        block(ModBlocks.BISMUTH_DOOR, "Decorate door block.");
        block(ModBlocks.BISMUTH_TRAPDOOR, "Decorate trapdoor block.");

        // ALEXANDRITE
        block(ModBlocks.ALEXANDRITE_STAIRS, "Decorate stair block.");
        block(ModBlocks.ALEXANDRITE_SLABS, "Decorate slab block.");
        block(ModBlocks.ALEXANDRITE_PREASSURE_PLATE, "Decorate pressure plate block.");
        block(ModBlocks.ALEXANDRITE_BUTTON, "Decorate button block.");
        block(ModBlocks.ALEXANDRITE_FENCE, "Decorate fence block.");
        block(ModBlocks.ALEXANDRITE_FENCE_GATE, "Decorate fence gate block.");
        block(ModBlocks.ALEXANDRITE_WALL, "Decorate wall block.");
        block(ModBlocks.ALEXANDRITE_DOOR, "Decorate door block.");
        block(ModBlocks.ALEXANDRITE_TRAPDOOR, "Decorate trapdoor block.");

        // ** CUSTOM Blockstate block **
        block(ModBlocks.BISMUTH_LAMP, "Decorate lamp block.");
        block(ModBlocks.ALEXANDRITE_LAMP, "Decorate lamp block.");

        // ** CUSTOM Log block **
        // BLOODWOOD
        block(ModBlocks.BLOODWOOD_LOG, "Decorate log block.");
        block(ModBlocks.BLOODWOOD_WOOD, "Decorate wood block.");
        block(ModBlocks.STRIPPED_BLOODWOOD_LOG, "Decorate stripped log block.");
        block(ModBlocks.STRIPPED_BLOODWOOD_WOOD, "Decorate stripped wood block.");
        block(ModBlocks.BLOODWOOD_PLANKS, "Decorate planks block.");
        block(ModBlocks.BLOODWOOD_LEAVES, "Decorate leaves block.");
        block(ModBlocks.BLOODWOOD_SAPLING, "Decorate sapling block.");

        // WALNUT
        block(ModBlocks.WALNUT_LOG, "Decorate log block.");
        block(ModBlocks.WALNUT_WOOD, "Decorate wood block.");
        block(ModBlocks.STRIPPED_WALNUT_LOG, "Decorate stripped log block.");
        block(ModBlocks.STRIPPED_WALNUT_WOOD, "Decorate stripped wood block.");
        block(ModBlocks.WALNUT_PLANKS, "Decorate planks block.");
        block(ModBlocks.WALNUT_LEAVES, "Decorate leaves block.");
        block(ModBlocks.WALNUT_SAPLING, "Decorate sapling block.");

        // ** CUSTOM sittable block model **
        block(ModBlocks.CHAIR, "Decorate sittable block.");

        // ** CUSTOM block entity **
        block(ModBlocks.PEDESTAL, "Show anywhere items on pedestal.");
        block(ModBlocks.GROWTH_CHAMBER, "Extract your item into other item.");
        block(ModBlocks.GEM_EMPOWERING_STATION, "Transform your item into other item using energy.");

        // ** CUSTOM furnace **
        block(ModBlocks.KAUPEN_FURNACE, "Improved Furnace with exclusive used fuels.");

        // ** CUSTOM glass block **
        block(ModBlocks.FORCED_STAINED_GLASS, "Decorate stained glass block.");
        block(ModBlocks.FORCED_STAINED_GLASS_PANE, "Decorate stained glass pane block.");

        block(ModBlocks.SOUND, "Plays nice sounds when walking or right-clicking.");

        enderBlockDescription(List.of(ModBlocks.ENDER_PEARL_BLOCK, ModBlocks.GREEN_ENDER_PEARL_BLOCK,
                                      ModBlocks.LIME_GREEN_ENDER_PEARL_BLOCK, ModBlocks.BLACK_ENDER_PEARL_BLOCK,
                                      ModBlocks.MAGENTA_ENDER_PEARL_BLOCK, ModBlocks.PURPLE_ENDER_PEARL_BLOCK,
                                      ModBlocks.ORANGE_ENDER_PEARL_BLOCK, ModBlocks.PINK_ENDER_PEARL_BLOCK,
                                      ModBlocks.CYAN_ENDER_PEARL_BLOCK, ModBlocks.BROWN_ENDER_PEARL_BLOCK,
                                      ModBlocks.GRAY_ENDER_PEARL_BLOCK, ModBlocks.RED_ENDER_PEARL_BLOCK,
                                      ModBlocks.YELLOW_ENDER_PEARL_BLOCK, ModBlocks.BLUE_ENDER_PEARL_BLOCK,
                                      ModBlocks.WHITE_ENDER_PEARL_BLOCK));

        // ** CUSTOM mob blocks **
        block(ModBlocks.NETHER_STAR_BLOCK, "Nine NETHER STAR items compacted on block.");
        block(ModBlocks.GUNPOWDER_BLOCK, "Nine GUNPOWDER items compacted on block.");
        block(ModBlocks.ROTTEN_FLESH_BLOCK, "Nine ROTTEN FLESH items compacted on block.");
        block(ModBlocks.BLAZE_ROD_BLOCK, "Nine BLAZE ROD items compacted on block.");
        block(ModBlocks.PHANTOM_MEMBRANE_BLOCK, "Nine PHANTOM MEMBRANE items compacted on block.");
        block(ModBlocks.STRING_BLOCK, "Nine STRING items compacted on block.");
        block(ModBlocks.SPIDER_EYE_BLOCK, "Nine SPIDER EYE items compacted on block.");
        block(ModBlocks.FERMENTED_SPIDER_EYE_BLOCK, "Nine FERMENTED SPIDER EYE items compacted on block.");
        block(ModBlocks.SUGAR_BLOCK, "Nine SUGAR items compacted on block.");
        block(ModBlocks.SUGAR_CANE_BLOCK, "Nine SUGAR CANE items compacted on block.");

        // ** CUSTOM oxidizable blocks **
        block(ModBlocks.RUBY_BLOCK, "Ruby oxidizable UNAFFECTED block.");
        block(ModBlocks.RUBY_BLOCK_1, "Ruby oxidizable EXPOSED block.");
        block(ModBlocks.RUBY_BLOCK_2, "Ruby oxidizable WEATHERED block.");
        block(ModBlocks.RUBY_BLOCK_3, "Ruby oxidizable DEGRADED block.");

        block(ModBlocks.WAXED_RUBY_BLOCK, "Waxed Ruby oxidizable UNAFFECTED block.");
        block(ModBlocks.WAXED_RUBY_BLOCK_1, "Waxed Ruby oxidizable EXPOSED block.");
        block(ModBlocks.WAXED_RUBY_BLOCK_2, "Waxed Ruby oxidizable WEATHERED block.");
        block(ModBlocks.WAXED_RUBY_BLOCK_3, "Waxed Ruby oxidizable DEGRADED block.");

        // ** CUSTOM flowers and pot flowers **
        block(ModBlocks.SNAPDRAGON, "Decorate Snapdragon flower block.");
        block(ModBlocks.POTTED_SNAPDRAGON, "Decorate Snapdragon on potted block.");

        // ** CUSTOM colored blocks **
        block(ModBlocks.COLORED_LEAVES, "Decorate Snapdragon on potted block.");

        // ** CUSTOM portal **
        block(ModBlocks.KAUPEN_PORTAL, "Portal block that changed to KAUPENDIM dimension.");

        // ** CUSTOM Fluid block **
        block(ModFluids.SOAP_WATER_BLOCK, "Liquid fluid block.");

        // ** CUSTOM Crop block **
        // One height
        blockLang(ModBlocks.RADISH_CROP);
        blockLang(ModBlocks.KOHLRABI_CROP);
        // Two height
        blockLang(ModBlocks.CATTAIL_CROP);

        // ** CUSTOM Bush block **
        blockLang(ModBlocks.GOJI_BERRY_BUSH);

        // ** CUSTOM block projectile **
        blockLang(ModBlocks.DICE);

        // ** CUSTOM Sign and Hanging sign **
        signBlock(ModBlocks.WALNUT_SIGN, "Decorate sign block.");
        signBlock(ModBlocks.WALNUT_HANGING_SIGN, "Decorate hanging sign block.");
        blockLang(ModBlocks.WALNUT_WALL_SIGN);
        blockLang(ModBlocks.WALNUT_WALL_HANGING_SIGN);

        // ** CUSTOM ITEM + DESCRIPTIONS **
        // ** CUSTOM ore items **
        // BISMUTH
        item(ModItems.BISMUTH, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        item(ModItems.RAW_BISMUTH, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        // ALEXANDRITE
        item(ModItems.ALEXANDRITE, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        item(ModItems.RAW_ALEXANDRITE, "Found on Overworld, Nether, End and Kaupendim dimensions!");
        // PINK
        item(ModItems.PINK, "Found on Overworld, Nether, End and Kaupendim dimensions!");

        // ** CUSTOM Crop block **
        // One height
        item(ModItems.RADISH, "Tastes really great!");
        item(ModItems.KOHLRABI, "Tastes really great!");
        // Two height
        item(ModItems.CATTAIL, "Transform oxidizable blocks on waxed blocks.");

        // ** CUSTOM Foods **
        item(ModItems.COFFEE, "Wake up and have a chance to achieve night vision effect.");

        // ** CUSTOM Seeds + CUSTOM Bush **
        seedsDescription(List.of(ModItems.RADISH_SEEDS, ModItems.KOHLRABI_SEEDS, ModItems.GOJI_BERRIES, ModItems.CATTAIL_SEEDS));

        // ** CUSTOM fuels **
        fuelDescription(List.of(ModItems.FROSTFIRE_ICE, ModItems.STARLIGHT_ASHES, ModItems.PEAT_BRICK));

        // ** CUSTOM Armors **
        // BISMUTH
        item(ModItems.BISMUTH_HELMET, "Armor.");
        item(ModItems.BISMUTH_CHESTPLATE, "Armor.");
        item(ModItems.BISMUTH_LEGGINGS, "Armor.");
        item(ModItems.BISMUTH_BOOTS, "Armor.");
        // ALEXANDRITE
        item(ModItems.ALEXANDRITE_HELMET, "Armor.");
        item(ModItems.ALEXANDRITE_CHESTPLATE, "Armor.");
        item(ModItems.ALEXANDRITE_LEGGINGS, "Armor.");
        item(ModItems.ALEXANDRITE_BOOTS, "Armor.");
        // PINK
        item(ModItems.PINK_HELMET, "Armor.");
        item(ModItems.PINK_CHESTPLATE, "Armor.");
        item(ModItems.PINK_LEGGINGS, "Armor.");
        item(ModItems.PINK_BOOTS, "Armor.");
        // COPPER
        item(ModItems.ULTRA_COPPER_HELMET, "Armor.");
        item(ModItems.ULTRA_COPPER_CHESTPLATE, "Armor.");
        item(ModItems.ULTRA_COPPER_LEGGINGS, "Armor.");
        item(ModItems.ULTRA_COPPER_BOOTS, "Armor.");
        // LAPIS LAZULI
        item(ModItems.LAPIS_LAZULI_HELMET, "Armor.");
        item(ModItems.LAPIS_LAZULI_CHESTPLATE, "Armor.");
        item(ModItems.LAPIS_LAZULI_LEGGINGS, "Armor.");
        item(ModItems.LAPIS_LAZULI_BOOTS, "Armor.");
        // REDSTONE
        item(ModItems.REDSTONE_HELMET, "Armor.");
        item(ModItems.REDSTONE_CHESTPLATE, "Armor.");
        item(ModItems.REDSTONE_LEGGINGS, "Armor.");
        item(ModItems.REDSTONE_BOOTS, "Armor.");
        // EMERALD
        item(ModItems.EMERALD_HELMET, "Armor.");
        item(ModItems.EMERALD_CHESTPLATE, "Armor.");
        item(ModItems.EMERALD_LEGGINGS, "Armor.");
        item(ModItems.EMERALD_BOOTS, "Armor.");

        // ** CUSTOM Horse armor **
        // BISMUTH
        item(ModItems.BISMUTH_HORSE_ARMOR, "Horse armor.");
        // ALEXANDRITE
        item(ModItems.ALEXANDRITE_HORSE_ARMOR, "Horse armor.");

        // ** CUSTOM Bow **
        item(ModItems.KAUPEN_BOW, "Bow.");
        item(ModItems.MINER_BOW, "Bow.");
        item(ModItems.ALEXANDRITE_BOW, "Bow.");

        // ** CUSTOM Fishing Rod **
        item(ModItems.TOP_FISHING_ROD, "More faster than vanilla Fishing Rod.");

        // ** CUSTOM Shield **
        shield(ModItems.ALEXANDRITE_SHIELD, "Alexandrite Shield");

        // ** CUSTOM Paxel **
        paxel(List.of(ModItems.BISMUTH_PAXEL, ModItems.ALEXANDRITE_PAXEL, ModItems.PINK_PAXEL,
                      ModItems.COPPER_PAXEL, ModItems.DIAMOND_PAXEL, ModItems.GOLD_PAXEL,
                      ModItems.IRON_PAXEL, ModItems.STONE_PAXEL, ModItems.WOODEN_PAXEL,
                      ModItems.NETHERITE_PAXEL, ModItems.LAPIS_LAZULI_PAXEL, ModItems.REDSTONE_PAXEL,
                      ModItems.EMERALD_PAXEL));

        // ** CUSTOM Hammer **
        hammer(List.of(ModItems.BISMUTH_HAMMER, ModItems.ALEXANDRITE_HAMMER, ModItems.PINK_HAMMER,
                       ModItems.COPPER_HAMMER, ModItems.DIAMOND_HAMMER, ModItems.GOLD_HAMMER,
                       ModItems.IRON_HAMMER, ModItems.STONE_HAMMER, ModItems.WOODEN_HAMMER,
                       ModItems.NETHERITE_HAMMER, ModItems.LAPIS_LAZULI_HAMMER, ModItems.REDSTONE_HAMMER,
                       ModItems.EMERALD_HAMMER));

        // ** CUSTOM Shovel **
        item(ModItems.BISMUTH_SHOVEL, "Shovel.");
        item(ModItems.ALEXANDRITE_SHOVEL, "Shovel.");
        item(ModItems.PINK_SHOVEL, "Shovel.");
        item(ModItems.ULTRA_COPPER_SHOVEL, "Shovel.");
        item(ModItems.LAPIS_LAZULI_SHOVEL, "Shovel.");
        item(ModItems.REDSTONE_SHOVEL, "Shovel.");
        item(ModItems.EMERALD_SHOVEL, "Shovel.");

        // ** CUSTOM Axe **
        item(ModItems.BISMUTH_AXE, "Axe.");
        item(ModItems.ALEXANDRITE_AXE, "Axe.");
        item(ModItems.PINK_AXE, "Axe.");
        item(ModItems.ULTRA_COPPER_AXE, "Axe.");
        item(ModItems.LAPIS_LAZULI_AXE, "Axe.");
        item(ModItems.REDSTONE_AXE, "Axe.");
        item(ModItems.EMERALD_AXE, "Axe.");

        // ** CUSTOM Hoe **
        item(ModItems.BISMUTH_HOE, "Hoe.");
        item(ModItems.ALEXANDRITE_HOE, "Hoe.");
        item(ModItems.PINK_HOE, "Hoe.");
        item(ModItems.ULTRA_COPPER_HOE, "Hoe.");
        item(ModItems.LAPIS_LAZULI_HOE, "Hoe.");
        item(ModItems.REDSTONE_HOE, "Hoe.");
        item(ModItems.EMERALD_HOE, "Hoe.");

        // ** CUSTOM Pickaxe **
        item(ModItems.BISMUTH_PICKAXE, "Pickaxe.");
        item(ModItems.ALEXANDRITE_PICKAXE, "Pickaxe.");
        item(ModItems.PINK_PICKAXE, "Pickaxe.");
        item(ModItems.ULTRA_COPPER_PICKAXE, "Pickaxe.");
        item(ModItems.LAPIS_LAZULI_PICKAXE, "Pickaxe.");
        item(ModItems.REDSTONE_PICKAXE, "Pickaxe.");
        item(ModItems.EMERALD_PICKAXE, "Pickaxe.");

        // ** CUSTOM Sword **
        item(ModItems.BISMUTH_SWORD, "Sword.");
        item(ModItems.ALEXANDRITE_SWORD, "Sword.");
        item(ModItems.PINK_SWORD, "Sword.");
        item(ModItems.ULTRA_COPPER_SWORD, "Sword.");
        item(ModItems.LAPIS_LAZULI_SWORD, "Sword.");
        item(ModItems.REDSTONE_SWORD, "Sword.");
        item(ModItems.EMERALD_SWORD, "Sword.");

        // ** CUSTOM TOOLTIP **
        add("tooltip." + top + "auto_smelt.tooltip",
            "When applied on pickaxe transform all items that can be roasted on furnace.");
        add("tooltip." + top + "more_ores.tooltip",
            "When applied on pickaxe if a stone block is break has a percentage to receive ores.");

        // ** CUSTOM MUSIC DISC **
        item(ModItems.BAR_BRAWL_MUSIC_DISC, "Jukebox music disc sound.");
        add("item." + top + "bar_brawl_music_disc.desc", "Bryan Tech - Bar Brawl (CC0)");

        // ** CUSTOM Mob **
        // GECKO
        item(ModItems.GECKO_SPAWN_EGG, "Spawn Gecko entity.");
        // RHINO
        item(ModItems.RHINO_SPAWN_EGG, "Spawn Rhino entity.");

        // ** CUSTOM Throwable Projectiles **
        item(ModItems.TOMAHAWK, "Thunder when attacked entities!");
        item(ModItems.TORCH_BALL, "Spawn TORCH blocks.");
        item(ModItems.BOUNCY_BALLS, "Teleport same as Ender Pearl without cooldown.");
        item(ModItems.BOUNCY_BALLS_PARTICLES, "Particle used on Bouncy Balls item.");
        item(ModItems.DICE_ITEM, "Spawn dice blocks!");

        // ** CUSTOM Animated Textures **
        item(ModItems.RADIATION_STAFF, "Spawn special particle!");

        // ** CUSTOM ADVANCED ITEMS **
        item(ModItems.GROWTH, "Makes a baby animal an adult animal!");
        item(ModItems.LEVEL_CHARGER_GENERIC_PLUS, "Increase all enchantment levels!");
        item(ModItems.LEVEL_CHARGER_GENERIC_MINUS, "Decrease all enchantment levels!");
        item(ModItems.LEVEL_CHARGER_SPECIF_PLUS_FORTUNE, "Increase Fortune enchantment level!");
        item(ModItems.LEVEL_CHARGER_SPECIF_MINUS_FORTUNE, "Decrease Fortune enchantment level!");
        item(ModItems.SPECIAL_BOTTLE, "Restore or store your orb experience!");
        shift(ModItems.CHISEL, "This Item can chisel Blocks into Bricks");
        item(ModItems.DATA_TABLET, "Store ore block founded on Overworld, Nether, End and Kaupendim dimensions!");
        shift(ModItems.METAL_DETECTOR, "§eRight Click on Blocks to find Valuables!");
        item(ModItems.ULTRA_COMPACTOR, "Compact automatic all ores blocks!");
        item(ModItems.PINK_ULTRA_COMPACTOR, "Compact manually all ores blocks!");
        item(ModItems.RESTORE, "Restore wrong crafting table recipe!");
        item(ModItems.FARMER, "Improved Bone Meal grow tree, crops etc.");
        item(ModItems.LUCK_GENERAL, "Gain random general enchantments.");
        item(ModItems.LUCK_PICKAXE, "Gain random pickaxe enchantments.");
        item(ModItems.LUCK_WEAPON, "Gain random sword enchantments.");

        // ** CUSTOM Boat **
        item(ModItems.WALNUT_BOAT, "Travel with special boat block item.");
        item(ModItems.WALNUT_CHEST_BOAT, "Travel with special chest boat block item.");

        // ** CUSTOM Fluid **
        item(ModFluids.SOAP_WATER_BUCKET, "Bucket.");
        add(ModFluidTypes.SOAP_WATER_FLUID_TYPE.get().getDescriptionId(), "Soap Water Fluid");

        // ** CUSTOM Elytra armor **
        // DIAMOND
        elytra(ModItems.DIAMOND_ELYTRA, "Diamond Elytra");
        // EMERALD
        elytra(ModItems.EMERALD_ELYTRA, "Emerald Elytra");

        // ** CUSTOM TRIM SMITHING TEMPLATE **
        // ** ARMOR TRIM SMITHING TEMPLATE **
        oresArmorTrimSmithingTemplate(ModItems.KAUPEN_ARMOR_TRIM_SMITHING_TEMPLATE, "kaupen");

        // ** UPGRADE SMITHING TEMPLATE **
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

        // ** CUSTOM Effects and Potions **
        Map<Holder<MobEffect>, String> top = new HashMap<>();
        Map<Holder<MobEffect>, String> vanilla = new HashMap<>();

        // TOP effects
        // FLY effect + potion
        top.put(ModEffects.FLY_EFFECT, "Player fly press twice SPACE clicks.");
        // NOTHING effect + potion
        top.put(ModEffects.NOTHING_EFFECT, "Never Warden spawns and Enderman angry when looking on eyes.");
        // SLIMEY effect + potion
        top.put(ModEffects.SLIMEY_EFFECT, "Climbing on blocks, but speed decreases 20 percent.");
        // HASTE effect + potion (VANILLA effect, but without potion)
        top.put(MobEffects.HASTE, "Increases 20 percent of attack speed.");

        // VANILLA effects
        vanilla.put(MobEffects.ABSORPTION, "Extra hearts on health bar.");
        vanilla.put(MobEffects.BAD_OMEN,
                    "Transform on Raid Omen when Player is a village or Trial Omen if near a non-ominous trial spawner.");
        vanilla.put(MobEffects.BLINDNESS, "Prevent sprinting and critical hits also impairs a Player's vision.");
        vanilla.put(MobEffects.CONDUIT_POWER, "Grants Water Breathing, Night Vision and Haste effects.");
        vanilla.put(MobEffects.DARKNESS, "Distorted vision when sculk shrieker activated or detection range of a Warden.");
        vanilla.put(MobEffects.DOLPHINS_GRACE, "Increases swing speed.");
        vanilla.put(MobEffects.FIRE_RESISTANCE, "Increases fire resistance damage.");
        vanilla.put(MobEffects.GLOWING, "Look \"hit box\" of mob entity positions through blocks.");
        vanilla.put(MobEffects.HEALTH_BOOST, "Increases maximum health points.");
        vanilla.put(MobEffects.HERO_OF_THE_VILLAGE,
                    "Player exclusive effect when defeating a Raid, discount when trading and gifts.");
        vanilla.put(MobEffects.HUNGER, "Player hunger points decreases more rapidly.");
        vanilla.put(MobEffects.INFESTED, "Entity received attack damage spawn Silverfish.");
        vanilla.put(MobEffects.INSTANT_DAMAGE, "Lose three multiply 2 square effect level health points.");
        vanilla.put(MobEffects.INSTANT_HEALTH, "Restore two health points.");
        vanilla.put(MobEffects.INVISIBILITY, "Decreases mob normal detection range.");
        vanilla.put(MobEffects.JUMP_BOOST, "Increases jump height when pressed SPACE.");
        vanilla.put(MobEffects.LEVITATION, "Uncontrollably float upward by Shulker entity.");
        vanilla.put(MobEffects.LUCK, "Increases receiving better loot tables.");
        vanilla.put(MobEffects.MINING_FATIGUE,
                    "Decreases 0.3 square effect level mining efficiency and 10 percent of attack speed.");
        vanilla.put(MobEffects.NAUSEA, "Player distortion as Nether Portal effect.");
        vanilla.put(MobEffects.NIGHT_VISION, "Player illumination anywhere location in darkness or underwater.");
        vanilla.put(MobEffects.OOZING, "Appears two medium Slime after death entities.");
        vanilla.put(MobEffects.POISON, "Similar as Wither effect, but Player's health is showing, more rapidly and not kill.");
        vanilla.put(MobEffects.RAID_OMEN, "Start a Raid.");
        vanilla.put(MobEffects.REGENERATION, "Restore health points for some time.");
        vanilla.put(MobEffects.RESISTANCE, "Increases resistance damage.");
        vanilla.put(MobEffects.SATURATION, "Decreases the need to eating and prevent death by hunger.");
        vanilla.put(MobEffects.SLOW_FALLING,
                    "Player gravity is more slowly and avoid fall damage, but prevents critical hits and smash attacks.");
        vanilla.put(MobEffects.SLOWNESS, "Decreases 15 percent of walking speed.");
        vanilla.put(MobEffects.SPEED, "Increases 20 percent of walking speed.");
        vanilla.put(MobEffects.STRENGTH, "Increases 3+ attack damage.");
        vanilla.put(MobEffects.TRIAL_OMEN, "Transform trial spawners to ominous trial spawners.");
        vanilla.put(MobEffects.UNLUCK, "Decreases getting better loot tables.");
        vanilla.put(MobEffects.WATER_BREATHING, "Increases breathing time on water.");
        vanilla.put(MobEffects.WEAKNESS, "Decreases attack damage.");
        vanilla.put(MobEffects.WEAVING, "Appears COBWEB upon death and reduces cobweb walk speed.");
        vanilla.put(MobEffects.WIND_CHARGED, "Release a burst of wind upon death.");
        vanilla.put(MobEffects.WITHER, "As Poison effect, but Player's health is black, more slowly and can kill.");
        topPotionDescription(top);
        vanillaPotionDescription(vanilla);
    }

    // CUSTOM METHOD - Register Block and Block Item
    private void blockLang(Supplier<? extends Block> block) {
        String blockId = block.get().getDescriptionId().replace("block." + top, "");
        // Sound -> One word
        String blockName = upperSpaceWord(blockId);
        String versionWithBlock = blockName + " Block";
        // Bismuth Block -> Two words
        String versionWithoutBlock = itemLines(splitWord(blockId));
        // Block name -> Example: bismuth_block
        boolean hasSpace = block.get().getDescriptionId().contains("_");
        String blockPrefix = hasSpace ? versionWithoutBlock : versionWithBlock;
        // Format block name + block item name
        addBlock(block, blockPrefix);
        add("item.top." + blockId, blockPrefix);
    }

    // CUSTOM METHOD - Register Item
    private void itemLang(Supplier<? extends Item> item) {
        String itemId = item.get().getDescriptionId().replace("item." + top, "");
        // Alexandrite -> One word | Kohlrabi seeds -> Two words
        String itemName = itemLines(splitWord(itemId));
        // Format item name
        addItem(item, itemName);
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
        add("tooltip.item." + top + ore + "_upgrade_smithing_template", "Upgrade Smithing Template.");
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
        add("tooltip.item." + top + ore + "_armor_trim_smithing_template", "Armor Trim Smithing Template.");
    }

    // CUSTOM METHOD - Shield
    private <I extends Item> void shield(DeferredItem<I> shield, String name) {
        item(shield, name);
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
        add("item." + top + name.replace(" ", "_").toLowerCase() + "_broken", "Broken " + name);
        item(elytra, "Elytra.");
    }

    // CUSTOM METHOD - PAINTING
    private void painting(String title, String author) {
        add("painting." + top + title.toLowerCase().replace(" ", "_") + ".title", title);
        add("painting." + top + title.toLowerCase().replace(" ", "_") + ".author", author);
    }

    // CUSTOM METHOD - TOP ENCHANTMENT
    private void topEnch(String name, String desc) {
        add("enchantment." + top + name.replace(" ", "_").toLowerCase(), name);
        add("enchantment." + top + name.replace(" ", "_").toLowerCase() + ".desc", desc);
    }

    // CUSTOM METHOD - VANILLA ENCHANTMENT
    private void ench(String name, String desc) {
        add("enchantment.minecraft." + name + ".desc", desc);
    }

    // CUSTOM METHOD - Block name + description
    private <T extends Block> void block(DeferredBlock<T> block, String text) {
        blockLang(block);
        add("tooltip.block." + block.getRegisteredName().replace(":", "."), text);
    }

    // CUSTOM METHOD - ENDER BLOCK description
    private <T extends Block> void enderBlockDescription(List<DeferredBlock<T>> block) {
        for (DeferredBlock<T> id : block) { block(id, "Decorate ender block."); }
    }

    // CUSTOM METHOD - SIGN description
    private <T extends Block> void signBlock(DeferredBlock<T> block,
                                             String text) {
        blockLang(block);
        add("tooltip.item." + block.getRegisteredName().replace(":", ".")
                                                       .replace("block.", ""), text);
    }

    // CUSTOM METHOD - Item description
    private <I extends Item> void item(DeferredItem<I> item, String text) {
        itemLang(item);
        add("tooltip.item." + item.getRegisteredName().replace(":", "."), text);
    }

    // CUSTOM METHOD - FUEL description
    private <I extends Item> void fuelDescription(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) {
            if (item.get() instanceof FuelItem fuelItem) {
                item(item, "§6Burn: §r§e" + fuelItem.getBurnTime());
            }
        }
    }

    // CUSTOM METHOD - SEEDS description
    private <I extends Item> void seedsDescription(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) { item(item, "Seeds."); }
    }

    // CUSTOM METHOD - SHIFT description
    private <I extends Item> void shift(DeferredItem<I> item, String shiftText) {
        item(item, "Press §eSHIFT§r for more information.");
        add("tooltip.item." + item.getRegisteredName().replace(":", ".") + ".shift", shiftText);
    }

    // CUSTOM METHOD - HAMMER item + description
    private <I extends Item> void hammer(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) {
            if (item.get() instanceof HammerItem hammerItem) {
                int radius = hammerItem.getRadius() * 2 + 1;
                item(item, "§6Hammer breaks: §r" + radius + "§fx§r" + radius + " §6radius area.§r");
            }
        }
    }

    // CUSTOM METHOD - PAXEL item + description
    private <I extends Item> void paxel(List<DeferredItem<I>> items) {
        for (DeferredItem<I> item : items) {
            item(item, "Break blocks as Axe, Pickaxe or Shovel.");
        }
    }

    // CUSTOM METHOD - POTION description
    private void potionDescription(Map<Holder<MobEffect>, String> effects) {
        for (Map.Entry<Holder<MobEffect>, String> effect : effects.entrySet()) {
             String id = effect.getKey().getRegisteredName().replace(":", ".");
             add("tooltip.effect." + id, effect.getValue());
        }
    }

    // CUSTOM METHOD - POTION item + description (TOP)
    private void topPotionDescription(Map<Holder<MobEffect>, String> effects) {
        for (Map.Entry<Holder<MobEffect>, String> effect : effects.entrySet()) {
            String id = effect.getKey().getRegisteredName().replace(":", ".");
            String idName = id.replace(top, "").replace("minecraft.", "")
                                                          .replace(" ", "_");
            String name = itemLines(splitWord(id.replace(top, "").replace("minecraft.", "")));
            add("effect." + id, name);
            add("item.minecraft.potion.effect." + idName + "_potion", name + " Potion");
            add("item.minecraft.splash_potion.effect." + idName + "_potion", name + " Splash Potion");
            add("item.minecraft.lingering_potion.effect." + idName + "_potion", name + " Lingering Potion");
            add("item.minecraft.tipped_arrow.effect." + idName + "_potion", "Arrow of " + name);
            potionDescription(Map.of(effect.getKey(), effect.getValue()));
        }
    }

    // CUSTOM METHOD - POTION description (VANILLA)
    private void vanillaPotionDescription(Map<Holder<MobEffect>, String> effects) {
        for (Map.Entry<Holder<MobEffect>, String> effect : effects.entrySet()) {
            potionDescription(Map.of(effect.getKey(), effect.getValue()));
        }
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(upperSpaceWord(Top.MOD_ID))) + " Languages"; }
}