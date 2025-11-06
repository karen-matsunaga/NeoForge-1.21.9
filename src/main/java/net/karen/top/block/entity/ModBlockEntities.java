package net.karen.top.block.entity;

import net.karen.top.Top;
import net.karen.top.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
           DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Top.MOD_ID);

    // Registry all custom BLOCK ENTITIES
    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE =
           BLOCK_ENTITIES.register("pedestal_be",
           () -> new BlockEntityType<>(PedestalBlockEntity::new, ModBlocks.PEDESTAL.get()));

    public static final Supplier<BlockEntityType<GrowthChamberBlockEntity>> GROWTH_CHAMBER_BE =
           BLOCK_ENTITIES.register("growth_chamber_be",
           () -> new BlockEntityType<>(GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER.get()));

    public static final Supplier<BlockEntityType<GemEmpoweringStationBlockEntity>> GEM_EMPOWERING_STATION_BE =
           BLOCK_ENTITIES.register("gem_empowering_station_be",
           () -> new BlockEntityType<>(GemEmpoweringStationBlockEntity::new, ModBlocks.GEM_EMPOWERING_STATION.get()));

    public static final Supplier<BlockEntityType<KaupenFurnaceBlockEntity>> KAUPEN_FURNACE_BLOCK_ENTITY =
           BLOCK_ENTITIES.register("kaupen_furnace_block_entity",
           () -> new BlockEntityType<>(KaupenFurnaceBlockEntity::new, ModBlocks.KAUPEN_FURNACE.get()));

    // Register all custom SIGN and HANGING SIGN
    public static final Supplier<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
           BLOCK_ENTITIES.register("mod_sign",
           () -> new BlockEntityType<>(ModSignBlockEntity::new, ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()));

    public static final Supplier<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
           BLOCK_ENTITIES.register("mod_hanging_sign",
           () -> new BlockEntityType<>(ModHangingSignBlockEntity::new,
                                       ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get()));

    // CUSTOM METHOD - Registry all custom BLOCK ENTITIES on event
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}