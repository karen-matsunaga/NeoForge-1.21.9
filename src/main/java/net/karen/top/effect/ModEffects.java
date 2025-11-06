package net.karen.top.effect;

import net.karen.top.Top;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
           DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Top.MOD_ID);

    // Registry all custom effects
    // FLY effect
    public static final Holder<MobEffect> FLY_EFFECT = MOB_EFFECTS.register("fly",
           () -> new FlyEffect(MobEffectCategory.BENEFICIAL, 0xFFFF00).addAttributeModifier(Attributes.FLYING_SPEED,
                               ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, "fly"), 1.00F,
                               AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    // SLIMEY effect
    public static final Holder<MobEffect> SLIMEY_EFFECT = MOB_EFFECTS.register("slimey",
           () -> new SlimeyEffect(MobEffectCategory.NEUTRAL, 0x36ebab).addAttributeModifier(Attributes.MOVEMENT_SPEED,
                                  ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, "slimey"), -0.25F,
                                  AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    // NOTHING effect
    public static final Holder<MobEffect> NOTHING_EFFECT = MOB_EFFECTS.register("nothing",
           () -> new NothingEffect(MobEffectCategory.NEUTRAL, 0x333366));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}