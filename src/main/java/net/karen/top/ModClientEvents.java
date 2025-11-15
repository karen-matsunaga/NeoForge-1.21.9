package net.karen.top;

import net.karen.top.block.ModBlocks;
import net.karen.top.block.entity.ModBlockEntities;
import net.karen.top.block.entity.renderer.GemEmpoweringStationBlockEntityRenderer;
import net.karen.top.block.entity.renderer.PedestalBlockEntityRenderer;
import net.karen.top.component.custom.AlternateTexture;
import net.karen.top.enchantment.custom.DashEnchantmentEffect;
import net.karen.top.entity.ModEntities;
import net.karen.top.entity.client.*;
import net.karen.top.entity.layers.ModModelLayers;
import net.karen.top.fluid.BaseFluidType;
import net.karen.top.fluid.ModFluidTypes;
import net.karen.top.fluid.ModFluids;
import net.karen.top.network.*;
import net.karen.top.particle.AlexandriteParticles;
import net.karen.top.particle.BismuthParticles;
import net.karen.top.particle.BouncyBallsParticles;
import net.karen.top.particle.ModParticles;
import net.karen.top.screen.ModMenuTypes;
import net.karen.top.screen.custom.*;
import net.karen.top.util.*;
import net.karen.top.worldgen.biome.ModBiomes;
import net.karen.top.worldgen.biome.ModSurfaceRules;
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
import terrablender.api.SurfaceRuleManager;
import java.util.function.Function;

@Mod(value = Top.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Top.MOD_ID, value = Dist.CLIENT)
public class ModClientEvents {
    public ModClientEvents(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        Top.LOGGER.info("HELLO FROM CLIENT SETUP");
        Top.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
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
              // ** CUSTOM Biomes and Surface Rules **
              ModBiomes.registerBiomes();
              SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,
                                                 Top.MOD_ID, ModSurfaceRules.makeRules());
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
        event.register(ModClientTooltipComponent.class, Function.identity());
    }

    // CUSTOM EVENT - Register all custom network (CLIENT -> SERVER)
    @SubscribeEvent
    public static void registerNetwork(RegisterPayloadHandlersEvent event) {
        // Network version
        final PayloadRegistrar registrar = event.registrar("1");
        // Network -> Top Elevator block
        registrar.playToServer(TopElevatorPacketPayload.TYPE,
                               // STREAM CODEC
                               TopElevatorPacketPayload.STREAM_CODEC,
                               // Server Payload Handler
                               TopElevatorPacketPayload::onTopElevatorServerPayloadHandler);

        // Network -> Special Bottle item
        registrar.playToServer(SpecialBottlePacketPayload.TYPE,
                               // STREAM CODEC
                               SpecialBottlePacketPayload.STREAM_CODEC,
                               // Server Payload Handler
                               SpecialBottlePacketPayload::onSpecialBottleServerPayloadHandler);

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

        // Network -> Dash enchantment
        registrar.playToServer(DashEnchantmentPacketPayload.TYPE, // TYPE
                               DashEnchantmentPacketPayload.STREAM_CODEC, // STREAM CODEC
                               DashEnchantmentPacketPayload::onDashEnchantmentServerPayloadHandler); // SERVER PAYLOAD HANDLER
    }

    // CUSTOM EVENT - Register all custom Key Inputs
    @SubscribeEvent
    public static void registerKeyInput(RegisterKeyMappingsEvent event) {
        // KEY BINDING CATEGORIES
        event.registerCategory(ModKeyMapping.KEY_CATEGORY_TOP);

        // KEY BINDING KEYWORDS
        event.register(ModKeyMapping.GLOWING_MOBS_KEY.get()); // Glowing Mobs custom enchantment
        event.register(ModKeyMapping.GLOWING_BLOCKS_KEY.get()); // Glowing Blocks custom enchantment
        event.register(ModKeyMapping.SPECIAL_BOTTLE_STORED_KEY.get()); // Special Bottle stored 10 levels
        event.register(ModKeyMapping.SPECIAL_BOTTLE_RESTORED_KEY.get()); // Special Bottle restored 10 levels
        event.register(ModKeyMapping.UNLOCK_KEY.get()); // Unlock custom enchantment
        event.register(ModKeyMapping.DASH_KEY.get()); // Dash custom enchantment
    }

    // CUSTOM EVENT - Register all custom item model property conditionals
    @SubscribeEvent
    public static void registerConditionalProperties(RegisterConditionalItemModelPropertyEvent event) {
        // The name to reference as the type
        event.register(Utils.topPath("has_data_info"), AlternateTexture.MAP_CODEC); // The map codec
    }

    // CUSTOM EVENT - Register all custom bows zoom
    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        Player player = event.getPlayer();
        if (player.isUsingItem() && player.getUseItem().is(ModTags.Items.BOW_TOOLS)) {
            float fovModifier = 1F;
            int ticksUsingItem = player.getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20F;
            if (deltaTicks > 1F) { deltaTicks = 1F; }
            else { deltaTicks *= deltaTicks; }
            fovModifier *= 1F - deltaTicks * 0.15F;
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
        // EMERALD Elytra
        event.registerLayerDefinition(ModModelLayers.EMERALD_ELYTRA_LAYER, ElytraModel::createLayer);
    }

    // CUSTOM EVENT - Registry all custom block entity renderers
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        // ** CUSTOM Block entity renderers **
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_BE.get(), PedestalBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.GEM_EMPOWERING_STATION_BE.get(),
                                          GemEmpoweringStationBlockEntityRenderer::new);
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
        event.register(ModMenuTypes.GEM_EMPOWERING_STATION_MENU.get(), GemEmpoweringStationScreen::new);
    }

    // CUSTOM EVENT - Registry all custom fluid types
    @SubscribeEvent
    public static void registerFluids(RegisterClientExtensionsEvent event) {
        event.registerFluidType(((BaseFluidType) ModFluidTypes.SOAP_WATER_FLUID_TYPE.get()).getClientFluidTypeExtensions(),
                                ModFluidTypes.SOAP_WATER_FLUID_TYPE.get());
    }

    // CUSTOM EVENT - Registry all custom shield special model renderers
    @SubscribeEvent
    public static void registerSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
        event.register(Utils.topPath("alexandrite_shield"), ShieldSpecialModelRenderer.Unbaked.MAP_CODEC);
    }

    // CUSTOM EVENT - Register all custom colored blocks
    @SubscribeEvent
    public static void registerColoredBlocks(RegisterColorHandlersEvent.Block event) {
        event.register((_, level, pos, _) ->
                       level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos)
                                                    : FoliageColor.FOLIAGE_DEFAULT, ModBlocks.COLORED_LEAVES.get());
    }

    // CUSTOM EVENT - Register all custom RENDER PIPELINES
    @SubscribeEvent
    public static void registerRenderPipelines(RegisterRenderPipelinesEvent event) {
        event.registerPipeline(ModRenderPipeline.LINES_NO_DEPTH_RENDER_PIPELINE);
    }

    // CUSTOM METHOD - DASH custom enchantment
    @SubscribeEvent
    public static void activatedDash(ClientTickEvent.Post event) {
        DashEnchantmentEffect.onKeyPressEvent();
    }
}