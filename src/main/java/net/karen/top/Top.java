package net.karen.top;

import net.karen.top.block.ModBlocks;
import net.karen.top.block.entity.ModBlockEntities;
import net.karen.top.component.ModDataComponentTypes;
import net.karen.top.effect.ModEffects;
import net.karen.top.enchantment.ModEnchantmentEffects;
import net.karen.top.entity.ModEntities;
import net.karen.top.fluid.ModFluidTypes;
import net.karen.top.fluid.ModFluids;
import net.karen.top.item.ModCreativeModeTabs;
import net.karen.top.item.ModItems;
import net.karen.top.loot.ModLootModifiers;
import net.karen.top.particle.ModParticles;
import net.karen.top.potion.ModPotions;
import net.karen.top.recipe.ModRecipes;
import net.karen.top.screen.ModMenuTypes;
import net.karen.top.sound.ModSounds;
import net.karen.top.villager.ModVillagers;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Top.MOD_ID)
public class Top {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "top";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Top(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        ModBlocks.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        ModCreativeModeTabs.register(modEventBus);
        // Register the Deferred Register to the mod event bus so enchantments get registered
        ModEnchantmentEffects.register(modEventBus);
        // Register the Deferred Register to the mod event bus so particles get registered
        ModParticles.register(modEventBus);
        // Register the Deferred Register to the mod event bus so data components get registered
        ModDataComponentTypes.register(modEventBus);
        // Register the Deferred Register to the mod event bus so effects get registered
        ModEffects.register(modEventBus);
        // Register the Deferred Register to the mod event bus so potions get registered
        ModPotions.register(modEventBus);
        // Register the Deferred Register to the mod event bus so sounds get registered
        ModSounds.register(modEventBus);
        // Register the Deferred Register to the mod event bus so entities get registered
        ModEntities.register(modEventBus);
        // Register the Deferred Register to the mod event bus so villagers get registered
        ModVillagers.register(modEventBus);
        // Register the Deferred Register to the mod event bus so loot modifiers get registered
        ModLootModifiers.register(modEventBus);
        // Register the Deferred Register to the mod event bus so block entities get registered
        ModBlockEntities.register(modEventBus);
        // Register the Deferred Register to the mod event bus so menu types get registered
        ModMenuTypes.register(modEventBus);
        // Register the Deferred Register to the mod event bus so recipes get registered
        ModRecipes.register(modEventBus);
        // Register the Deferred Register to the mod event bus so fluids get registered
        ModFluids.register(modEventBus);
        // Register the Deferred Register to the mod event bus so fluid types get registered
        ModFluidTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, TopConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        if (TopConfig.LOG_DIRT_BLOCK.getAsBoolean()) {
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        }
        LOGGER.info("{}{}", TopConfig.MAGIC_NUMBER_INTRODUCTION.get(), TopConfig.MAGIC_NUMBER.getAsInt());
        TopConfig.ITEM_STRINGS.get().forEach((item) -> LOGGER.info("ITEM >> {}", item));

        // ** CUSTOM Crafting Plus 7x7 size **
        event.enqueueWork(() -> ShapedRecipePattern.setCraftingSize(7, 7));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.RAW_BISMUTH);
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BISMUTH_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
