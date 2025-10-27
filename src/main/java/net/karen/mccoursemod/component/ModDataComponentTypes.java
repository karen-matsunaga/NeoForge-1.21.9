package net.karen.mccoursemod.component;

import com.mojang.serialization.Codec;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.component.custom.Coordinates;
import net.karen.mccoursemod.component.custom.FoundBlock;
import net.karen.mccoursemod.enchantment.custom.AutoSmeltEnchantmentEffect;
import net.karen.mccoursemod.enchantment.custom.MoreOresEnchantmentEffect;
import net.karen.mccoursemod.enchantment.custom.RainbowEnchantmentEffect;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    // Data Component Type Registries
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
           DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MccourseMod.MOD_ID);

    // Enchantment Data Component Type Registries
    public static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENT_TYPES =
           DeferredRegister.createDataComponents(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, MccourseMod.MOD_ID);

    // COORDINATES data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Coordinates>> COORDINATES =
           register("coordinates", builder ->
                    builder.persistent(Coordinates.CODEC).networkSynchronized(Coordinates.STREAM_CODEC));

    // FOUND BLOCK data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FoundBlock>> FOUND_BLOCK =
           register("found_block", builder ->
                    builder.persistent(FoundBlock.CODEC).networkSynchronized(FoundBlock.STREAM_CODEC));

    // UNLOCK data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> UNLOCK =
           register("unlock", builder ->
                    builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL));

    // Mccourse Mod Bottle -> STORED LEVELS data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> STORED_LEVELS =
           register("stored_levels", builder ->
                    builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

    // MORE ORES ENCHANTMENT EFFECT data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<MoreOresEnchantmentEffect>>
           MORE_ORES_ENCHANTMENT_EFFECT =
           registerEnch("more_ores_enchantment_effect", builder ->
                        builder.persistent(MoreOresEnchantmentEffect.CODEC)
                               .networkSynchronized(MoreOresEnchantmentEffect.STREAM_CODEC));

    // RAINBOW ENCHANTMENT EFFECT data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<RainbowEnchantmentEffect>>
           RAINBOW_ENCHANTMENT_EFFECT =
           registerEnch("rainbow_enchantment_effect", builder ->
                        builder.persistent(RainbowEnchantmentEffect.CODEC)
                               .networkSynchronized(RainbowEnchantmentEffect.STREAM_CODEC));

    // AUTO SMELT ENCHANTMENT EFFECT data component
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<AutoSmeltEnchantmentEffect>>
           AUTO_SMELT_ENCHANTMENT_EFFECT =
           registerEnch("auto_smelt_enchantment_effect", builder ->
                        builder.persistent(AutoSmeltEnchantmentEffect.CODEC)
                               .networkSynchronized(AutoSmeltEnchantmentEffect.STREAM_CODEC));

    // CUSTOM METHOD - Registry all custom DATA COMPONENT
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>>
                   register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    // CUSTOM METHOD - Registry all custom ENCHANTMENT DATA COMPONENT
    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>>
                   registerEnch(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return ENCHANTMENT_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    // CUSTOM METHOD - Registry all custom DATA COMPONENT and ENCHANTMENT DATA COMPONENT on bus group event
    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
        ENCHANTMENT_COMPONENT_TYPES.register(eventBus);
    }
}