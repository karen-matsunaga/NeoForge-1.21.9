package net.karen.mccoursemod;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.block.entity.ModBlockEntities;
import net.karen.mccoursemod.block.entity.renderer.PedestalBlockEntityRenderer;
import net.karen.mccoursemod.component.custom.AlternateTexture;
import net.karen.mccoursemod.entity.ModEntities;
import net.karen.mccoursemod.entity.client.*;
import net.karen.mccoursemod.entity.layers.ModModelLayers;
import net.karen.mccoursemod.fluid.BaseFluidType;
import net.karen.mccoursemod.fluid.ModFluidTypes;
import net.karen.mccoursemod.fluid.ModFluids;
import net.karen.mccoursemod.network.LevelChargerSlotPacketPayload;
import net.karen.mccoursemod.network.MccourseModBottlePacketPayload;
import net.karen.mccoursemod.network.MccourseModElevatorPacketPayload;
import net.karen.mccoursemod.network.UnlockEnchantmentPacketPayload;
import net.karen.mccoursemod.particle.AlexandriteParticles;
import net.karen.mccoursemod.particle.BismuthParticles;
import net.karen.mccoursemod.particle.BouncyBallsParticles;
import net.karen.mccoursemod.particle.ModParticles;
import net.karen.mccoursemod.screen.ModMenuTypes;
import net.karen.mccoursemod.screen.custom.CraftingPlusScreen;
import net.karen.mccoursemod.screen.custom.GrowthChamberScreen;
import net.karen.mccoursemod.screen.custom.KaupenFurnaceScreen;
import net.karen.mccoursemod.screen.custom.PedestalScreen;
import net.karen.mccoursemod.util.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ElytraModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import java.util.function.Function;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = MccourseMod.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = MccourseMod.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {
    public ModClientEvents(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        MccourseMod.LOGGER.info("HELLO FROM CLIENT SETUP");
        MccourseMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        // ** CUSTOM entity mob **
        // GECKO
        EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);
        // RHINO
        EntityRenderers.register(ModEntities.RHINO.get(), RhinoRenderer::new);
        // ** CUSTOM Throwable Projectiles **
        EntityRenderers.register(ModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
        EntityRenderers.register(ModEntities.TORCH_BALL.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.BOUNCY_BALLS.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.DICE_PROJECTILE.get(), ThrownItemRenderer::new);
        EntityRenderers.register(ModEntities.MAGIC_PROJECTILE.get(), MagicProjectileRenderer::new);
        // ** CUSTOM Sittable blocks **
        EntityRenderers.register(ModEntities.CHAIR_ENTITY.get(), ChairRenderer::new);
        // ** CUSTOM Boats **
        EntityRenderers.register(ModEntities.MOD_BOAT.get(),
                context -> new ModBoatRenderer(context, ModModelLayers.WALNUT_BOAT_LAYER));
        EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(),
                context -> new ModBoatRenderer(context, ModModelLayers.WALNUT_CHEST_BOAT_LAYER));
        event.enqueueWork(() -> {
            // ** CUSTOM Wood types -> Sign and Hanging Sign **
            Sheets.addWoodType(ModWoodTypes.WALNUT);
            // ** CUSTOM Fluid **
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_SOAP_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_SOAP_WATER.get(), ChunkSectionLayer.TRANSLUCENT);
            // ** CUSTOM Flower and Pot Flowers **
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SNAPDRAGON.getId(), ModBlocks.POTTED_SNAPDRAGON);
        });
    }

    // CUSTOM EVENT - Register all custom particles
    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.BISMUTH_PARTICLES.get(), BismuthParticles.Provider::new);
        event.registerSpriteSet(ModParticles.ALEXANDRITE_PARTICLES.get(), AlexandriteParticles.Provider::new);
        event.registerSpriteSet(ModParticles.BOUNCY_BALLS_PARTICLES.get(), BouncyBallsParticles.Provider::new);
    }

    // CUSTOM EVENT - Register all custom image tooltip
    @SubscribeEvent
    public static void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(ImageTooltipComponent.class, Function.identity());
        event.register(MultiImageTooltipComponent.class, Function.identity());
    }

    // CUSTOM EVENT - Register all custom network (CLIENT -> SERVER)
    @SubscribeEvent
    public static void registerNetwork(RegisterPayloadHandlersEvent event) {
        // Network version
        final PayloadRegistrar registrar = event.registrar("1");
        // Network -> Mccourse Mod Elevator block
        registrar.playToServer(MccourseModElevatorPacketPayload.TYPE,
                // STREAM CODEC
                MccourseModElevatorPacketPayload.STREAM_CODEC,
                // Server Payload Handler
                MccourseModElevatorPacketPayload::onMccourseModElevatorServerPayloadHandler);

        // Network -> Mccourse Mod Bottle item
        registrar.playToServer(MccourseModBottlePacketPayload.TYPE,
                // STREAM CODEC
                MccourseModBottlePacketPayload.STREAM_CODEC,
                // Server Payload Handler
                MccourseModBottlePacketPayload::onMccourseModBottleServerPayloadHandler);

        // Network -> Level Charger items
        registrar.playToServer(LevelChargerSlotPacketPayload.TYPE,
                // STREAM CODEC
                LevelChargerSlotPacketPayload.STREAM_CODEC,
                // Server Payload Handler
                LevelChargerSlotPacketPayload::onLevelChargerServerPayloadHandler);

        // Network -> Unlock enchantment
        registrar.playToServer(UnlockEnchantmentPacketPayload.TYPE,
                // STREAM CODEC
                UnlockEnchantmentPacketPayload.STREAM_CODEC,
                // Server Payload Handler
                UnlockEnchantmentPacketPayload::onUnlockEnchantmentServerPayloadHandler);
    }

    // CUSTOM EVENT - Register all custom Key Inputs
    @SubscribeEvent
    public static void registerKeyInput(RegisterKeyMappingsEvent event) {
        // KEY BINDING CATEGORIES
        event.registerCategory(KeyBinding.KEY_CATEGORY_MCCOURSE);

        // KEY BINDING KEYWORDS
        event.register(KeyBinding.GLOWING_MOBS_KEY.get()); // Glowing Mobs custom enchantment
        event.register(KeyBinding.GLOWING_BLOCKS_KEY.get()); // Glowing Blocks custom enchantment
        event.register(KeyBinding.MCCOURSE_BOTTLE_STORED_KEY.get()); // Mccourse Bottle stored 10 levels
        event.register(KeyBinding.MCCOURSE_BOTTLE_RESTORED_KEY.get()); // Mccourse Bottle restored 10 levels
        event.register(KeyBinding.UNLOCK_KEY.get()); // Unlock custom enchantment
    }

    // CUSTOM EVENT - Register all custom item model property conditionals
    @SubscribeEvent
    public static void registerConditionalProperties(RegisterConditionalItemModelPropertyEvent event) {
        // The name to reference as the type
        event.register(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "has_data_info"),
                AlternateTexture.MAP_CODEC); // The map codec
    }

    // CUSTOM EVENT - Register all custom bows zoom
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        if (player.isUsingItem() && player.getUseItem().is(ModTags.Items.BOW_TOOLS)) {
            float fovModifier = 1f;
            int ticksUsingItem = player.getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20f;
            if (deltaTicks > 1f) { deltaTicks = 1f; }
            else { deltaTicks *= deltaTicks; }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }

    // CUSTOM EVENT - Registry all custom entity renderer layers
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // CUSTOM Gecko mob
        // GECKO
        event.registerLayerDefinition(GeckoModel.LAYER_LOCATION, GeckoModel::createBodyLayer);
        // RHINO
        event.registerLayerDefinition(RhinoModel.LAYER_LOCATION, RhinoModel::createBodyLayer);
        // CUSTOM Throwable Projectiles
        event.registerLayerDefinition(TomahawkProjectileModel.LAYER_LOCATION, TomahawkProjectileModel::createBodyLayer);
        event.registerLayerDefinition(MagicProjectileModel.MAGIC_PROJECTILE_LAYER, MagicProjectileModel::createBodyLayer);
        // CUSTOM Boat and Chest Boat
        event.registerLayerDefinition(ModModelLayers.WALNUT_BOAT_LAYER, BoatModel::createBoatModel);
        event.registerLayerDefinition(ModModelLayers.WALNUT_CHEST_BOAT_LAYER, BoatModel::createChestBoatModel);
        // CUSTOM ELYTRA
        // DIAMOND Elytra
        event.registerLayerDefinition(ModModelLayers.DIAMOND_ELYTRA_LAYER, ElytraModel::createLayer);
    }

    // CUSTOM EVENT - Registry all custom block entity renderers
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        // ** CUSTOM Block entity renderers **
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        // ** CUSTOM Sign and Hanging Sing renderers **
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    // CUSTOM EVENT - Registry all custom screens
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenuTypes.PEDESTAL_MENU.get(), PedestalScreen::new);
        event.register(ModMenuTypes.GROWTH_CHAMBER_MENU.get(), GrowthChamberScreen::new);
        event.register(ModMenuTypes.KAUPEN_FURNACE_MENU.get(), KaupenFurnaceScreen::new);
        event.register(ModMenuTypes.CRAFTING_PLUS_MENU.get(), CraftingPlusScreen::new);
    }

    // CUSTOM EVENT - Registry all custom fluid types
    @SubscribeEvent
    public static void registerFluids(RegisterClientExtensionsEvent event) {
        event.registerFluidType(((BaseFluidType) ModFluidTypes.SOAP_WATER_FLUID_TYPE.get())
                        .getClientFluidTypeExtensions(),
                ModFluidTypes.SOAP_WATER_FLUID_TYPE.get());
    }

    // CUSTOM EVENT - Registry all custom shield special model renderers
    @SubscribeEvent
    public static void registerSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "alexandrite_shield"),
                ShieldSpecialModelRenderer.Unbaked.MAP_CODEC);
    }

    // CUSTOM EVENT - Register all custom colored blocks
    @SubscribeEvent
    public static void registerColoredBlocks(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) ->
                level != null && pos != null
                        ? BiomeColors.getAverageFoliageColor(level, pos)
                        : FoliageColor.FOLIAGE_DEFAULT, ModBlocks.COLORED_LEAVES.get());
    }

    // CUSTOM EVENT - Register all custom RENDER PIPELINES
    @SubscribeEvent
    public static void registerRenderPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(ModRenderPipeline.LINES_NO_DEPTH_RENDER_PIPELINE);
    }
}
