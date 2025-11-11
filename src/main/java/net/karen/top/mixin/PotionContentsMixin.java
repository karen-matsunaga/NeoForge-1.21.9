package net.karen.top.mixin;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.neoforge.common.util.AttributeUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;
import java.util.function.Consumer;
import static net.karen.top.util.ChatUtils.*;
import static net.minecraft.world.item.alchemy.PotionContents.getPotionDescription;

@Mixin(PotionContents.class)
public class PotionContentsMixin {
    @Inject(method = "addPotionTooltip", at = @At("HEAD"), cancellable = true)
    private static void potionDescription(Iterable<MobEffectInstance> effects, Consumer<Component> consumer,
                                          float durationFactor, float ticksPerSecond, CallbackInfo ci) {
        ci.cancel(); // Cancels DEFAULT potion tooltip
        List<Pair<Holder<Attribute>, AttributeModifier>> list = Lists.newArrayList();
        boolean flag = true;
        for (MobEffectInstance mobeffectinstance : effects) {
            flag = false;
            Holder<MobEffect> holder = mobeffectinstance.getEffect();
            int i = mobeffectinstance.getAmplifier();
            holder.value().createModifiers(i, (attHolder, attModifier) ->
                                           list.add(new Pair<>(attHolder, attModifier)));
            MutableComponent mutableComponent = getPotionDescription(holder, i);
            if (!mobeffectinstance.endsWithin(20)) {
                mutableComponent = Component.translatable("potion.withDuration",
                                                          mutableComponent,
                                                          MobEffectUtil.formatDuration(mobeffectinstance,
                                                                                       durationFactor, ticksPerSecond));
            }
            ChatFormatting potionTooltipColor = holder.value().getCategory().getTooltipFormatting();
            if (potionTooltipColor == MobEffectCategory.BENEFICIAL.getTooltipFormatting()) {
                consumer.accept(potionIcon(holder).append(mutableComponent.withColor(copperColor)));
            }
            else if (potionTooltipColor == MobEffectCategory.HARMFUL.getTooltipFormatting()) {
                consumer.accept(potionIcon(holder).append(mutableComponent.withColor(redBedrockColor)));
            }
            else if (potionTooltipColor == MobEffectCategory.NEUTRAL.getTooltipFormatting()){
                consumer.accept(potionIcon(holder).append(mutableComponent.withColor(blueBedrockColor)));
            }
            consumer.accept(componentTranslatableIntColor("tooltip.effect." +
                                                          holder.getRegisteredName().replace(":", "."),
                                                          darkGreenColor));
        }
        if (flag) {
            consumer.accept(componentTranslatableIntColor("effect.none", ARGB.color(206, 202, 202)));
        }
        if (!list.isEmpty()) {
            consumer.accept(CommonComponents.EMPTY);
            consumer.accept(componentTranslatable("potion.whenDrank", aqua));
            AttributeUtil.addPotionTooltip(list, consumer);
        }
    }
}