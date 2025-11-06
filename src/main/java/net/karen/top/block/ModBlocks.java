package net.karen.top.block;

import net.karen.top.Top;
import net.karen.top.block.custom.*;
import net.karen.top.item.ModItems;
import net.karen.top.block.custom.TopGeneratorBlock;
import net.karen.top.sound.ModSounds;
import net.karen.top.util.ModTags;
import net.karen.top.worldgen.tree.ModTreeGrowers;
import net.karen.top.util.ModWoodTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import static net.karen.top.util.ChatUtils.*;

public class ModBlocks {
    // Registry all custom BLOCKS
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Top.MOD_ID);

    // ** CUSTOM ores **
    // BISMUTH
    public static final DeferredBlock<Block> BISMUTH_BLOCK =
           oresItemBlock("bismuth_block", 4.0F, 4.0F, SoundType.AMETHYST, bismuthColor, bismuthColor);

    public static final DeferredBlock<Block> BISMUTH_ORE =
           oreBlock("bismuth_ore", 2, 4, 3.0F, SoundType.STONE, bismuthColor, bismuthColor);

    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE =
           oreBlock("bismuth_deepslate_ore", 3, 6, 4.0F, SoundType.DEEPSLATE, bismuthColor, bismuthColor);

    public static final DeferredBlock<Block> BISMUTH_END_ORE =
           oreBlock("bismuth_end_ore", 5, 9, 7.0F, SoundType.GLASS, bismuthColor, bismuthColor);

    public static final DeferredBlock<Block> BISMUTH_NETHER_ORE =
           oreBlock("bismuth_nether_ore", 1, 5, 3.0F, SoundType.NETHERRACK, bismuthColor, bismuthColor);

    // ALEXANDRITE
    public static final DeferredBlock<Block> ALEXANDRITE_BLOCK =
           oresItemBlock("alexandrite_block", 5.0F, 6.0F, SoundType.METAL,
                         alexandriteColor, alexandriteColor);

    public static final DeferredBlock<Block> RAW_ALEXANDRITE_BLOCK =
           oresItemBlock("raw_alexandrite_block", 5.0F, 6.0F, SoundType.METAL,
                         alexandriteColor, alexandriteColor);

    public static final DeferredBlock<Block> ALEXANDRITE_ORE =
           oreBlock("alexandrite_ore", 2, 5, 5.0F, SoundType.STONE,
                    alexandriteColor, alexandriteColor);

    public static final DeferredBlock<Block> DEEPSLATE_ALEXANDRITE_ORE =
           oreBlock("deepslate_alexandrite_ore", 3, 7, 5.0F, SoundType.DEEPSLATE,
                    alexandriteColor, alexandriteColor);

    public static final DeferredBlock<Block> END_STONE_ALEXANDRITE_ORE =
           oreBlock("end_stone_alexandrite_ore", 5, 8, 5.0F, SoundType.GLASS,
                    alexandriteColor, alexandriteColor);

    public static final DeferredBlock<Block> NETHER_ALEXANDRITE_ORE =
           oreBlock("nether_alexandrite_ore", 3, 6, 5.0F, SoundType.NETHERRACK,
                    alexandriteColor, alexandriteColor);

    // PINK
    public static final DeferredBlock<Block> PINK_BLOCK =
           oresItemBlock("pink_block", 5.0F, 6.0F, SoundType.AMETHYST, pinkColor, pinkColor);

    public static final DeferredBlock<Block> PINK_ORE =
           oreBlock("pink_ore", 2, 5, 5.0F, SoundType.STONE, pinkColor, pinkColor);

    public static final DeferredBlock<Block> DEEPSLATE_PINK_ORE =
           oreBlock("deepslate_pink_ore", 3, 7, 5.0F, SoundType.DEEPSLATE, pinkColor, pinkColor);

    public static final DeferredBlock<Block> END_STONE_PINK_ORE =
           oreBlock("end_stone_pink_ore", 5, 8, 5.0F, SoundType.GLASS, pinkColor, pinkColor);

    public static final DeferredBlock<Block> NETHER_PINK_ORE =
           oreBlock("nether_pink_ore", 3, 6, 5.0F, SoundType.NETHERRACK, pinkColor, pinkColor);

    // ** CUSTOM advanced block **
    public static final DeferredBlock<Block> MAGIC =
           registerBlock("magic", properties ->
                         new MagicBlock(properties.strength(2F).sound(ModSounds.MAGIC_BLOCK_SOUNDS)), magicColor, magicColor);

    // CUSTOM Enchant block
    public static final DeferredBlock<Block> ENCHANT =
           registerBlock("enchant", properties ->
                         new EnchantBlock(properties.strength(5.0F, 3600000.0F)
                                                    .requiresCorrectToolForDrops()),
                         enchantColor, enchantColor);

    // CUSTOM Disenchant individual block
    public static final DeferredBlock<Block> DISENCHANT_INDIVIDUAL =
           disenchantBlock("disenchant_individual", 1, disenchantIndColor, disenchantIndColor);

    // CUSTOM Disenchant grouped block
    public static final DeferredBlock<Block> DISENCHANT_GROUPED =
           disenchantBlock("disenchant_grouped", 2, disenchantGroColor, disenchantGroColor);

    // CUSTOM Top Elevator block
    public static final DeferredBlock<Block> TOP_ELEVATOR =
           registerBlock("top_elevator", properties ->
                         new TopElevatorBlock(properties.mapColor(MapColor.WOOL).sound(SoundType.WOOL)
                                                             .strength(5F, 1200.F)
                                                             .requiresCorrectToolForDrops()),
                         elevatorColor, elevatorColor);

    // CUSTOM Top Generator block
    public static final DeferredBlock<Block> TOP_GENERATOR =
           registerBlock("top_generator", properties ->
                         new TopGeneratorBlock(properties.mapColor(MapColor.STONE)
                                                                 .instrument(NoteBlockInstrument.BASEDRUM)
                                                                 .strength(5F, 3600000.0F),
                                                                           "ALL", ModTags.Blocks.ALL_ORES),
                         generatorColor, generatorColor);

    // CUSTOM Crafting Plus custom Crafting Table
    public static final DeferredBlock<Block> CRAFTING_PLUS =
           registerBlock("crafting_plus", properties ->
                         new CraftingPlusBlock(properties.mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS)
                                                         .strength(2.5F).sound(SoundType.WOOD).ignitedByLava()),
                         craftingColor, craftingColor);

    // ** CUSTOM Block Family -> (Button, Door, Fence, Fence Gate, Pressure Plate, Slab, Stairs, Trapdoor and Wall) **
    // BISMUTH
    public static final DeferredBlock<StairBlock> BISMUTH_STAIRS =
           registerBlock("bismuth_stairs", properties ->
                         new StairBlock(ModBlocks.BISMUTH_BLOCK.get().defaultBlockState(),
                                        properties.strength(2F).requiresCorrectToolForDrops().sound(SoundType.METAL)),
                         bismuthColor, bismuthColor);

    public static final DeferredBlock<SlabBlock> BISMUTH_SLAB =
           slabBlock("bismuth_slab", bismuthColor, bismuthColor);

    public static final DeferredBlock<PressurePlateBlock> BISMUTH_PRESSURE_PLATE =
           pressurePlateBlock("bismuth_pressure_plate", bismuthColor, bismuthColor);

    public static final DeferredBlock<ButtonBlock> BISMUTH_BUTTON =
           buttonBlock("bismuth_button", 20, bismuthColor, bismuthColor);

    public static final DeferredBlock<FenceBlock> BISMUTH_FENCE =
           fenceBlock("bismuth_fence", bismuthColor, bismuthColor);

    public static final DeferredBlock<FenceGateBlock> BISMUTH_FENCE_GATE =
           fenceGateBlock("bismuth_fence_gate", bismuthColor, bismuthColor);

    public static final DeferredBlock<WallBlock> BISMUTH_WALL =
           wallBlock("bismuth_wall", bismuthColor, bismuthColor);

    public static final DeferredBlock<DoorBlock> BISMUTH_DOOR =
           doorBlock("bismuth_door", bismuthColor, bismuthColor);

    public static final DeferredBlock<TrapDoorBlock> BISMUTH_TRAPDOOR =
           trapDoorBlock("bismuth_trapdoor", bismuthColor, bismuthColor);

    // ALEXANDRITE
    public static final DeferredBlock<StairBlock> ALEXANDRITE_STAIRS =
           registerBlock("alexandrite_stairs", properties ->
                         new StairBlock(ModBlocks.ALEXANDRITE_BLOCK.get().defaultBlockState(),
                                        properties.strength(2F).requiresCorrectToolForDrops().sound(SoundType.METAL)),
                         alexandriteColor, alexandriteColor);

    public static final DeferredBlock<SlabBlock> ALEXANDRITE_SLABS =
           slabBlock("alexandrite_slabs", alexandriteColor, alexandriteColor);

    public static final DeferredBlock<PressurePlateBlock> ALEXANDRITE_PREASSURE_PLATE =
           pressurePlateBlock("alexandrite_pressure_plate", alexandriteColor, alexandriteColor);

    public static final DeferredBlock<ButtonBlock> ALEXANDRITE_BUTTON =
           buttonBlock("alexandrite_button", 10, alexandriteColor, alexandriteColor);

    public static final DeferredBlock<FenceBlock> ALEXANDRITE_FENCE =
           fenceBlock("alexandrite_fence", alexandriteColor, alexandriteColor);

    public static final DeferredBlock<FenceGateBlock> ALEXANDRITE_FENCE_GATE =
           fenceGateBlock("alexandrite_fence_gate", alexandriteColor, alexandriteColor);

    public static final DeferredBlock<WallBlock> ALEXANDRITE_WALL =
           wallBlock("alexandrite_wall", alexandriteColor, alexandriteColor);

    public static final DeferredBlock<DoorBlock> ALEXANDRITE_DOOR =
           doorBlock("alexandrite_door", alexandriteColor, alexandriteColor);

    public static final DeferredBlock<TrapDoorBlock> ALEXANDRITE_TRAPDOOR =
           trapDoorBlock("alexandrite_trapdoor", alexandriteColor, alexandriteColor);

    // ** CUSTOM blockstate block **
    public static final DeferredBlock<Block> BISMUTH_LAMP =
           lampBlock("bismuth_lamp", MapColor.COLOR_ORANGE, ModSounds.MAGIC_BLOCK_SOUNDS,
                     bismuthColor, bismuthColor);

    public static final DeferredBlock<Block> ALEXANDRITE_LAMP =
           lampBlock("alexandrite_lamp", MapColor.COLOR_BLUE, ModSounds.ALEXANDRITE_LAMP_SOUNDS,
                     alexandriteColor, alexandriteColor);

    // ** CUSTOM crop block **
    // RADISH
    public static final DeferredBlock<Block> RADISH_CROP = BLOCKS.registerBlock("radish_crop",
           properties -> new RadishCropBlock(properties.mapColor(MapColor.PLANT).noCollision()
                                                                 .randomTicks().instabreak().sound(SoundType.CROP)
                                                                 .pushReaction(PushReaction.DESTROY)));
    // KOHLRABI
    public static final DeferredBlock<Block> KOHLRABI_CROP = BLOCKS.registerBlock("kohlrabi_crop",
           properties -> new KohlrabiCropBlock(properties.mapColor(MapColor.PLANT).noCollision()
                                                                   .randomTicks().instabreak().sound(SoundType.CROP)
                                                                   .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM Crop block with two height **
    // CATTAIL
    public static final DeferredBlock<Block> CATTAIL_CROP = BLOCKS.registerBlock("cattail_crop",
           properties -> new CattailCropBlock(properties.mapColor(MapColor.PLANT).noCollision()
                                                                  .randomTicks().instabreak().sound(SoundType.CROP)
                                                                  .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM bush crop block **
    public static final DeferredBlock<Block> GOJI_BERRY_BUSH = BLOCKS.registerBlock("goji_berry_bush",
           properties -> new GojiBerryBushBlock(properties.mapColor(MapColor.PLANT).noCollision()
                                                                    .randomTicks().instabreak().sound(SoundType.CROP)
                                                                    .pushReaction(PushReaction.DESTROY)));

    // ** CUSTOM log **
    // BLOODWOOD
    public static final DeferredBlock<Block> BLOODWOOD_LOG =
           logWoodBlocks("bloodwood_log", SoundType.CHERRY_WOOD, bloodColor, bloodColor);

    // CUSTOM wood
    public static final DeferredBlock<Block> BLOODWOOD_WOOD =
           logWoodBlocks("bloodwood_wood", SoundType.CHERRY_WOOD, bloodColor, bloodColor);

    // CUSTOM stripped log
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_LOG =
           logWoodBlocks("stripped_bloodwood_log", SoundType.CHERRY_WOOD, bloodColor, bloodColor);

    // CUSTOM stripped wood
    public static final DeferredBlock<Block> STRIPPED_BLOODWOOD_WOOD =
           logWoodBlocks("stripped_bloodwood_wood", SoundType.CHERRY_WOOD, bloodColor, bloodColor);

    // CUSTOM planks
    public static final DeferredBlock<Block> BLOODWOOD_PLANKS =
           plankBlock("bloodwood_planks", bloodColor, bloodColor);

    // CUSTOM leaves
    public static final DeferredBlock<Block> BLOODWOOD_LEAVES =
           leaveBlock("bloodwood_leaves", bloodColor, bloodColor);

    // CUSTOM sapling
    public static final DeferredBlock<Block> BLOODWOOD_SAPLING =
           saplingBlock("bloodwood_sapling", ModTreeGrowers.BLOODWOOD, () -> Blocks.NETHERRACK, bloodColor, bloodColor);

    // WALNUT
    public static final DeferredBlock<Block> WALNUT_LOG =
           logWoodBlocks("walnut_log", SoundType.WOOD, walnutColor, walnutColor);

    public static final DeferredBlock<Block> WALNUT_WOOD =
           logWoodBlocks("walnut_wood", SoundType.WOOD, walnutColor, walnutColor);

    public static final DeferredBlock<Block> STRIPPED_WALNUT_LOG =
           logWoodBlocks("stripped_walnut_log", SoundType.WOOD, walnutColor, walnutColor);

    public static final DeferredBlock<Block> STRIPPED_WALNUT_WOOD =
           logWoodBlocks("stripped_walnut_wood", SoundType.WOOD, walnutColor, walnutColor);

    public static final DeferredBlock<Block> WALNUT_PLANKS =
           plankBlock("walnut_planks", walnutColor, walnutColor);

    public static final DeferredBlock<Block> WALNUT_LEAVES =
           leaveBlock("walnut_leaves", walnutColor, walnutColor);

    public static final DeferredBlock<Block> WALNUT_SAPLING =
           saplingBlock("walnut_sapling", ModTreeGrowers.WALNUT, () -> Blocks.END_STONE, walnutColor, walnutColor);

    // ** CUSTOM sign and hanging sign **
    public static final DeferredBlock<Block> WALNUT_SIGN =
           BLOCKS.registerBlock("walnut_sign", properties ->
                                new ModStandingSignBlock(ModWoodTypes.WALNUT,
                                                         properties.mapColor(MapColor.WOOD).forceSolidOn()
                                                                   .instrument(NoteBlockInstrument.BASS)
                                                                   .noCollision().strength(1.0F).ignitedByLava()));

    public static final DeferredBlock<Block> WALNUT_WALL_SIGN =
           BLOCKS.registerBlock("walnut_wall_sign", properties ->
                                new ModWallSignBlock(ModWoodTypes.WALNUT,
                                                     properties.mapColor(MapColor.WOOD).forceSolidOn()
                                                               .instrument(NoteBlockInstrument.BASS).noCollision()
                                                               .strength(1.0F).ignitedByLava()));

    public static final DeferredBlock<Block> WALNUT_HANGING_SIGN =
           BLOCKS.registerBlock("walnut_hanging_sign", properties ->
                                new ModHangingSignBlock(ModWoodTypes.WALNUT,
                                                        properties.mapColor(ModBlocks.WALNUT_LOG.get().defaultMapColor())
                                                                  .forceSolidOn().instrument(NoteBlockInstrument.BASS)
                                                                  .noCollision().strength(1.0F).ignitedByLava()));

    public static final DeferredBlock<Block> WALNUT_WALL_HANGING_SIGN =
           BLOCKS.registerBlock("walnut_wall_hanging_sign", properties ->
                                new ModWallHangingSignBlock(ModWoodTypes.WALNUT,
                                                            properties.mapColor(ModBlocks.WALNUT_LOG.get().defaultMapColor())
                                                                      .forceSolidOn().instrument(NoteBlockInstrument.BASS)
                                                                      .noCollision().strength(1.0F).ignitedByLava()));

    // ** CUSTOM sittable block model **
    public static final DeferredBlock<Block> CHAIR =
           registerBlock("chair", properties -> new ChairBlock(properties.noOcclusion()), chairColor, chairColor);

    // ** CUSTOM block entity **
    public static final DeferredBlock<Block> PEDESTAL =
           registerBlock("pedestal", properties ->
                         new PedestalBlock(properties.noOcclusion()), pedestalColor, pedestalColor);

    // ** CUSTOM crafting block entity **
    public static final DeferredBlock<Block> GROWTH_CHAMBER =
           registerBlock("growth_chamber", properties ->
                         new GrowthChamberBlock(properties.requiresCorrectToolForDrops()
                                                          .strength(4.0F, 4.0F)),
                         growthChamberColor, growthChamberColor);

    public static final DeferredBlock<Block> GEM_EMPOWERING_STATION =
           registerBlock("gem_empowering_station", properties ->
                         new GemEmpoweringStationBlock(properties.requiresCorrectToolForDrops()
                                                                 .strength(4.0F, 4.0F)
                                                                 .noOcclusion()),
                         gemEmpoweringStationColor, gemEmpoweringStationColor);

    public static final DeferredBlock<Block> KAUPEN_FURNACE =
           registerBlock("kaupen_furnace", properties ->
                         new KaupenFurnaceBlock(properties.requiresCorrectToolForDrops()
                                                          .strength(4.0F, 4.0F)),
                         kaupenFurnaceColor, kaupenFurnaceColor);

    // ** CUSTOM glass block **
    public static final DeferredBlock<Block> FORCED_STAINED_GLASS =
           registerBlock("forced_stained_glass", props ->
                         new StainedGlassBlock(DyeColor.GREEN,
                                               props.strength(3.0F, 10.0F)
                                                    .mapColor(DyeColor.GREEN).instrument(NoteBlockInstrument.HAT)
                                                    .sound(SoundType.GLASS).noOcclusion().isValidSpawn(Blocks::never)
                                                    .isRedstoneConductor((_, _, _) -> false)
                                                    .isSuffocating((_, _, _) -> false)
                                                    .isViewBlocking((_, _, _) -> false)),
                         forcedGlassColor, forcedGlassColor);

    public static final DeferredBlock<Block> FORCED_STAINED_GLASS_PANE =
           registerBlock("forced_stained_glass_pane", properties ->
                         new IronBarsBlock(properties.strength(3.0F, 10.0F)
                                                     .instrument(NoteBlockInstrument.HAT)
                                                     .sound(SoundType.GLASS).noOcclusion()),
                         forcedGlassColor, forcedGlassColor);

    // SOUND block
    public static final DeferredBlock<Block> SOUND =
           registerBlock("sound", properties ->
                         new SoundBlock(properties.strength(3.0F, 20.F)
                                                  .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)),
                         soundColor, soundColor);

    // END PEARL blocks
    public static final DeferredBlock<Block> ENDER_PEARL_BLOCK =
           enderPearlBlock("ender_pearl_block", MapColor.EMERALD, enderColor, enderColor);

    public static final DeferredBlock<Block> GREEN_ENDER_PEARL_BLOCK =
           enderPearlBlock("green_ender_pearl_block", MapColor.COLOR_GREEN, greenEnderColor, greenEnderColor);

    public static final DeferredBlock<Block> LIME_GREEN_ENDER_PEARL_BLOCK =
           enderPearlBlock("lime_green_ender_pearl_block", MapColor.COLOR_LIGHT_GREEN, limeEnderColor, limeEnderColor);

    public static final DeferredBlock<Block> BLACK_ENDER_PEARL_BLOCK =
           enderPearlBlock("black_ender_pearl_block", MapColor.COLOR_BLACK, blackEnderColor, blackEnderColor);

    public static final DeferredBlock<Block> MAGENTA_ENDER_PEARL_BLOCK =
           enderPearlBlock("magenta_ender_pearl_block", MapColor.COLOR_MAGENTA, magentaEnderColor, magentaEnderColor);

    public static final DeferredBlock<Block> PURPLE_ENDER_PEARL_BLOCK =
           enderPearlBlock("purple_ender_pearl_block", MapColor.COLOR_PURPLE, purpleEnderColor, purpleEnderColor);

    public static final DeferredBlock<Block> ORANGE_ENDER_PEARL_BLOCK =
           enderPearlBlock("orange_ender_pearl_block", MapColor.COLOR_ORANGE, orangeEnderColor, orangeEnderColor);

    public static final DeferredBlock<Block> PINK_ENDER_PEARL_BLOCK =
           enderPearlBlock("pink_ender_pearl_block", MapColor.COLOR_PINK, pinkEnderColor, pinkEnderColor);

    public static final DeferredBlock<Block> CYAN_ENDER_PEARL_BLOCK =
           enderPearlBlock("cyan_ender_pearl_block", MapColor.COLOR_CYAN, cyanEnderColor, cyanEnderColor);

    public static final DeferredBlock<Block> BROWN_ENDER_PEARL_BLOCK =
           enderPearlBlock("brown_ender_pearl_block", MapColor.COLOR_BROWN, brownEnderColor, brownEnderColor);

    public static final DeferredBlock<Block> GRAY_ENDER_PEARL_BLOCK =
           enderPearlBlock("gray_ender_pearl_block", MapColor.COLOR_GRAY, grayEnderColor, grayEnderColor);

    public static final DeferredBlock<Block> RED_ENDER_PEARL_BLOCK =
           enderPearlBlock("red_ender_pearl_block", MapColor.COLOR_RED, redEnderColor, redEnderColor);

    public static final DeferredBlock<Block> YELLOW_ENDER_PEARL_BLOCK =
           enderPearlBlock("yellow_ender_pearl_block", MapColor.COLOR_YELLOW, yellowEnderColor, yellowEnderColor);

    public static final DeferredBlock<Block> BLUE_ENDER_PEARL_BLOCK =
           enderPearlBlock("blue_ender_pearl_block", MapColor.COLOR_BLUE, blueEnderColor, blueEnderColor);

    public static final DeferredBlock<Block> WHITE_ENDER_PEARL_BLOCK =
           enderPearlBlock("white_ender_pearl_block", MapColor.COLOR_LIGHT_GRAY, whiteEnderColor, whiteEnderColor);

    // ** CUSTOM mob blocks **
    public static final DeferredBlock<Block> NETHER_STAR_BLOCK =
           mobBlock("nether_star_block", MapColor.METAL, NoteBlockInstrument.BELL, SoundType.METAL,
                    netherColor, netherColor);

    public static final DeferredBlock<Block> GUNPOWDER_BLOCK =
           mobBlock("gunpowder_block", MapColor.METAL, NoteBlockInstrument.CREEPER, SoundType.METAL,
                    gunpowderColor, gunpowderColor);

    public static final DeferredBlock<Block> ROTTEN_FLESH_BLOCK =
           mobBlock("rotten_flesh_block", MapColor.METAL, NoteBlockInstrument.ZOMBIE, SoundType.METAL,
                    rottenColor, rottenColor);

    public static final DeferredBlock<Block> BLAZE_ROD_BLOCK =
           mobBlock("blaze_rod_block", MapColor.METAL, NoteBlockInstrument.BIT, SoundType.METAL,
                    blazeColor, blazeColor);

    public static final DeferredBlock<Block> PHANTOM_MEMBRANE_BLOCK =
           mobBlock("phantom_membrane_block", MapColor.METAL, NoteBlockInstrument.BIT, SoundType.METAL,
                    phantomColor, phantomColor);

    public static final DeferredBlock<Block> STRING_BLOCK =
           mobBlock("string_block", MapColor.WOOL, NoteBlockInstrument.DIDGERIDOO, SoundType.WOOL,
                    stringColor, stringColor);

    public static final DeferredBlock<Block> SPIDER_EYE_BLOCK =
           mobBlock("spider_eye_block", MapColor.COLOR_RED, NoteBlockInstrument.GUITAR, SoundType.SLIME_BLOCK,
                    spiderColor, spiderColor);

    public static final DeferredBlock<Block> FERMENTED_SPIDER_EYE_BLOCK =
           mobBlock("fermented_spider_eye_block", MapColor.COLOR_RED, NoteBlockInstrument.GUITAR, SoundType.SLIME_BLOCK,
                    fermentedColor, fermentedColor);

    public static final DeferredBlock<Block> SUGAR_BLOCK =
           mobBlock("sugar_block", MapColor.COLOR_LIGHT_GRAY, NoteBlockInstrument.BELL, SoundType.SAND,
                    sugarColor, sugarColor);

    public static final DeferredBlock<Block> SUGAR_CANE_BLOCK =
           mobBlock("sugar_cane_block", MapColor.METAL, NoteBlockInstrument.BELL, SoundType.SAND,
                    sugarCaneColor, sugarCaneColor);

    // ** CUSTOM oxidizable block **
    public static final DeferredBlock<Block> RUBY_BLOCK =
           oxidizableBlock("ruby_block", GemDegradable.GemDegradationLevel.UNAFFECTED, rubyColor, rubyColor);

    public static final DeferredBlock<Block> RUBY_BLOCK_1 =
           oxidizableBlock("ruby_block_1", GemDegradable.GemDegradationLevel.EXPOSED, ruby1Color, ruby1Color);

    public static final DeferredBlock<Block> RUBY_BLOCK_2 =
           oxidizableBlock("ruby_block_2", GemDegradable.GemDegradationLevel.WEATHERED, ruby2Color, ruby2Color);

    public static final DeferredBlock<Block> RUBY_BLOCK_3 =
           oxidizableBlock("ruby_block_3", GemDegradable.GemDegradationLevel.DEGRADED, ruby3Color, ruby3Color);

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK =
           waxedOxidizableBlock("waxed_ruby_block", rubyColor, rubyColor);

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK_1 =
           waxedOxidizableBlock("waxed_ruby_block_1", ruby1Color, ruby1Color);

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK_2 =
           waxedOxidizableBlock("waxed_ruby_block_2", ruby2Color, ruby2Color);

    public static final DeferredBlock<Block> WAXED_RUBY_BLOCK_3 =
           waxedOxidizableBlock("waxed_ruby_block_3", ruby3Color, ruby3Color);

    // ** CUSTOM flowers and pot flowers **
    // SNAPDRAGON
    public static final DeferredBlock<Block> SNAPDRAGON =
           registerBlock("snapdragon", properties ->
                         new FlowerBlock(MobEffects.BLINDNESS, 6.0F,
                                         properties.mapColor(MapColor.PLANT).noCollision().instabreak()
                                                   .sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ)
                                                   .pushReaction(PushReaction.DESTROY)),
                         snapdragonColor, snapdragonColor);

    public static final DeferredBlock<Block> POTTED_SNAPDRAGON =
           registerBlock("potted_snapdragon", properties ->
                         new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, SNAPDRAGON,
                                            properties.instabreak().noOcclusion().pushReaction(PushReaction.DESTROY)),
                         snapdragonColor, snapdragonColor);

    // ** CUSTOM colored block **
    public static final DeferredBlock<Block> COLORED_LEAVES =
           registerBlock("colored_leaves", properties ->
                         new Block(properties.noOcclusion()), coloredLoreColor, coloredCustomNameColor);

    // ** CUSTOM portal block **
    public static final DeferredBlock<Block> KAUPEN_PORTAL =
           registerBlock("kaupen_portal", properties ->
                         new KaupenPortalBlock(properties.randomTicks().strength(-1.0F).sound(SoundType.NETHERRACK)
                                                         .lightLevel(_ -> 15)
                                                         .pushReaction(PushReaction.BLOCK).liquid().forceSolidOn()
                                                         .noLootTable().noOcclusion().noCollision()),
                         kaupenPortalLoreColor, kaupenPortalCustomNameColor);

    // ** CUSTOM block projectile **
    public static final DeferredBlock<Block> DICE =
           BLOCKS.registerBlock("dice", properties ->
                                new DiceBlock(properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                                                        .requiresCorrectToolForDrops()
                                                        .strength(1.5F, 6.0F).noLootTable()));

    // ** CUSTOM METHOD - OXIDIZABLE blocks **
    private static DeferredBlock<Block> oxidizableBlock(String name,
                                                        GemDegradable.GemDegradationLevel gemDegradation,
                                                        int lore, int customName) {
        return registerBlock(name, properties ->
                             new DegradableRubyBlock(gemDegradation, properties.mapColor(MapColor.STONE)
                                                                               .instrument(NoteBlockInstrument.BASEDRUM)
                                                                               .requiresCorrectToolForDrops()
                                                                               .strength(1.5F, 6.0F)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - WAXED OXIDIZABLE blocks **
    private static DeferredBlock<Block> waxedOxidizableBlock(String name,
                                                             int lore, int customName) {
        return registerBlock(name, properties ->
                             new Block(properties.mapColor(MapColor.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                                                 .requiresCorrectToolForDrops().strength(1.5F, 6.0F)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - DISENCHANT blocks **
    private static DeferredBlock<Block> disenchantBlock(String name, int type,
                                                        int lore, int customName) {
        return registerBlock(name, properties ->
                             new DisenchantBlock(properties.strength(5.0F, 3600000.0F)
                                                           .requiresCorrectToolForDrops(), type),
                             lore, customName);
    }

    // ** CUSTOM METHOD - ORES blocks **
    private static DeferredBlock<Block> oresItemBlock(String name,
                                                      float strength, float explosion, SoundType soundType,
                                                      int lore, int customName) {
        return registerBlock(name, properties ->
                             new Block(properties.mapColor(MapColor.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE)
                                                                          .requiresCorrectToolForDrops()
                                                                          .strength(strength, explosion).sound(soundType)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - SLAB blocks **
    private static DeferredBlock<SlabBlock> slabBlock(String name,
                                                      int lore, int customName) {
        return registerBlock(name, properties ->
                             new SlabBlock(properties.strength(2F).requiresCorrectToolForDrops().sound(SoundType.METAL)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - PRESSURE PLATE blocks **
    private static DeferredBlock<PressurePlateBlock> pressurePlateBlock(String name,
                                                                        int lore, int customName) {
        return registerBlock(name, properties ->
                             new PressurePlateBlock(BlockSetType.IRON, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                              .sound(SoundType.METAL)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - BUTTON blocks **
    private static DeferredBlock<ButtonBlock> buttonBlock(String name, int tick,
                                                          int lore, int customName) {
        return registerBlock(name, properties ->
                             new ButtonBlock(BlockSetType.IRON, tick, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                .noCollision().sound(SoundType.METAL)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - FENCE blocks **
    private static DeferredBlock<FenceBlock> fenceBlock(String name, int lore, int customName) {
        return registerBlock(name, properties ->
                             new FenceBlock(properties.strength(2F).requiresCorrectToolForDrops()),
                             lore, customName);
    }

    // ** CUSTOM METHOD - FENCE GATE blocks **
    private static DeferredBlock<FenceGateBlock> fenceGateBlock(String name,
                                                                int lore, int customName) {
        return registerBlock(name, properties ->
                             new FenceGateBlock(WoodType.ACACIA, properties.strength(2F).requiresCorrectToolForDrops()),
                             lore, customName);
    }

    // ** CUSTOM METHOD - WALL blocks **
    private static DeferredBlock<WallBlock> wallBlock(String name, int lore, int customName) {
        return registerBlock(name, properties ->
                             new WallBlock(properties.strength(2F).requiresCorrectToolForDrops()),
                             lore, customName);
    }

    // ** CUSTOM METHOD - DOOR blocks **
    private static DeferredBlock<DoorBlock> doorBlock(String name, int lore, int customName) {
        return registerBlock(name, properties ->
                             new DoorBlock(BlockSetType.IRON, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                     .noOcclusion()),
                             lore, customName);
    }

    // ** CUSTOM METHOD - TRAPDOOR blocks **
    private static DeferredBlock<TrapDoorBlock> trapDoorBlock(String name, int lore, int customName) {
        return registerBlock(name, properties ->
                             new TrapDoorBlock(BlockSetType.IRON, properties.strength(2F).requiresCorrectToolForDrops()
                                                                                         .noOcclusion()),
                             lore, customName);
    }

    // ** CUSTOM METHOD - LAMP blocks **
    private static DeferredBlock<Block> lampBlock(String name,
                                                  MapColor mapColor, SoundType soundType,
                                                  int lore, int customName) {
        return registerBlock(name, properties ->
                             new BismuthLampBlock(properties.strength(2F).mapColor(mapColor).sound(soundType)
                                                                         .requiresCorrectToolForDrops()
                                                                         .lightLevel(state ->
                                                                                     state.getValue(BismuthLampBlock.CLICKED)
                                                                                     ? 15 : 0)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - LEAVE blocks **
    private static DeferredBlock<Block> leaveBlock(String name, int lore, int customName) {
        return registerBlock(name, properties ->
                             new UntintedParticleLeavesBlock(0.01F,
                                                             ParticleTypes.CHERRY_LEAVES,
                                                             properties.mapColor(MapColor.PLANT).strength(0.2F).randomTicks()
                                                                       .sound(SoundType.CHERRY_LEAVES).noOcclusion()
                                                                       .isValidSpawn(Blocks::ocelotOrParrot)
                                                                       .ignitedByLava().pushReaction(PushReaction.DESTROY)) {
                             @Override
                             public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) { return true; }

                             @Override
                             public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) { return 60; }

                             @Override
                             public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                                           @NotNull BlockPos pos, @NotNull Direction direction) { return 30; }
        }, lore, customName);
    }

    // ** CUSTOM METHOD - LOG, WOOD, STRIPPED LOG, STRIPPED WOOD blocks **
    private static DeferredBlock<Block> logWoodBlocks(String name, SoundType soundType,
                                                      int lore, int customName) {
        return registerBlock(name, properties ->
                             new ModFlammableRotatedPillarBlock(properties.instrument(NoteBlockInstrument.BASS).strength(2.0F)
                                                                          .sound(soundType).ignitedByLava()),
                             lore, customName);
    }

    // ** CUSTOM METHOD - PLANK blocks **
    private static DeferredBlock<Block> plankBlock(String name, int lore, int customName) {
        return registerBlock(name, properties -> new Block(properties) {
                             @Override
                             public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) { return true; }

                             @Override
                             public int getFlammability(@NotNull BlockState state, @NotNull BlockGetter level,
                                                        @NotNull BlockPos pos, @NotNull Direction direction) { return 20; }

                             @Override
                             public int getFireSpreadSpeed(@NotNull BlockState state, @NotNull BlockGetter level,
                                                           @NotNull BlockPos pos, @NotNull Direction direction) { return 5; }
        }, lore, customName);
    }

    // ** CUSTOM METHOD - SAPLING blocks **
    private static DeferredBlock<Block> saplingBlock(String name,
                                                     TreeGrower tree, Supplier<Block> block,
                                                     int lore, int customName) {
        return registerBlock(name, properties ->
                             new ModSaplingBlock(tree, properties.mapColor(MapColor.PLANT)
                                                                 .noCollision().randomTicks()
                                                                 .instabreak().sound(SoundType.GRASS)
                                                                 .pushReaction(PushReaction.DESTROY), block),
                             lore, customName);
    }

    // ** CUSTOM METHOD - ORES blocks **
    private static DeferredBlock<Block> oreBlock(String name, int min, int max,
                                                 float strength, SoundType soundType,
                                                 int lore, int customName) {
        return registerBlock(name, properties ->
                             new DropExperienceBlock(UniformInt.of(min, max), properties.strength(strength)
                                                                                        .requiresCorrectToolForDrops()
                                                                                        .sound(soundType)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - ENDER PEARL blocks **
    private static DeferredBlock<Block> enderPearlBlock(String name, MapColor color,
                                                        int lore, int customName) {
        return registerBlock(name, properties ->
                             new Block(properties.mapColor(color).instrument(NoteBlockInstrument.BELL)
                                                 .requiresCorrectToolForDrops().strength(5.0F, 6.0F)
                                                 .sound(SoundType.METAL).lightLevel(_ -> 50)),
                             lore, customName);
    }

    // ** CUSTOM METHOD - MOB blocks **
    private static DeferredBlock<Block> mobBlock(String name, MapColor color,
                                                 NoteBlockInstrument noteBlock, SoundType soundType,
                                                 int lore, int customName) {
       return registerBlock(name, properties ->
                            new Block(properties.mapColor(color).instrument(noteBlock)
                                                .requiresCorrectToolForDrops().strength(5.0F, 6.0F)
                                                .sound(soundType)),
                            lore, customName);
    }

    // ** CUSTOM METHOD - Registry all custom BLOCKS **
    public static <T extends Block> DeferredBlock<T> registerBlock(String name,
                                                                   Function<BlockBehaviour.Properties, T> block,
                                                                   int lore, int customName) {
        DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, block);
        registerBlockItem(name, toReturn, lore, customName);
        return toReturn;
    }

    // ** CUSTOM METHOD - Registry all custom BLOCK ITEMS **
    public static <T extends Block> void registerBlockItem(String name,
                                                           DeferredBlock<T> block, int lore, int customName) {
        String registerBlock = block.getRegisteredName();
        // LORE
        String blockLore = registerBlock.replace(":", ".");
        List<Component> loreComponent = List.of(componentTranslatableIntColor("tooltip.block." + blockLore, lore));
        // CUSTOM NAME
        String blockId = registerBlock.replace("top:", "");
        String names = itemLines(splitWord(blockId));
        ModItems.ITEMS.registerItem(name, properties ->
                                    new BlockItem(block.get(),
                                                  properties.useBlockDescriptionPrefix()
                                                            // LORE
                                                            .component(DataComponents.LORE,
                                                                       new ItemLore(List.of(Component.nullToEmpty("")),
                                                                                    loreComponent))
                                                            // CUSTOM NAME
                                                            .component(DataComponents.CUSTOM_NAME,
                                                                       componentTranslatableIntColor(names, customName))));
    }

    // ** CUSTOM METHOD - Registry all custom BLOCKS on EVENT **
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}