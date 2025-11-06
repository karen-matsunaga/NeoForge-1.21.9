package net.karen.top.mixin;

import net.karen.top.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin {
    @Unique HappyGhast neoForge_1_21_9$happyGhast = (HappyGhast) (Object) this;

    // DEFAULT METHOD - Custom Happy Ghast FLY speed movement
    @Inject(method = "travel", at = @At("HEAD"))
    private void setFlySpeedTravel(Vec3 travelVector, CallbackInfo ci) {
        float f = (float) neoForge_1_21_9$happyGhast.getAttributeValue(Attributes.FLYING_SPEED) * 5.0F / 3.0F;
        MobEffectInstance happyGhastFly = neoForge_1_21_9$happyGhast.getEffect(ModEffects.FLY_EFFECT);
        if (happyGhastFly != null) {
            int happyGhastFlyLevel = happyGhastFly.getAmplifier();
            if (neoForge_1_21_9$happyGhast.hasEffect(ModEffects.FLY_EFFECT)) {
                if (happyGhastFlyLevel > 0 && happyGhastFlyLevel <= 2) { // FLY EFFECT 1 and 2
                    float baseLevel = f + (0.02F * happyGhastFlyLevel);
                    neoForge_1_21_9$travelFlying(travelVector, baseLevel, baseLevel, baseLevel);
                }
                else { // FLY EFFECT 3+
                    float amplifier = f + (0.02F * 1);
                    neoForge_1_21_9$travelFlying(travelVector, amplifier, amplifier, amplifier);
                }
            }
        }
        else { neoForge_1_21_9$travelFlying(travelVector, f, f, f); }
    }

    // CUSTOM METHOD - Default Happy Ghast FLY speed movement
    @Unique protected void neoForge_1_21_9$travelFlying(Vec3 relative, float inWaterAmount,
                                                        float inLavaAmount, float amount) {
        if (neoForge_1_21_9$happyGhast.isInWater()) { // HAPPY GHAST on WATER
            neoForge_1_21_9$happyGhast.moveRelative(inWaterAmount, relative);
            neoForge_1_21_9$happyGhast.move(MoverType.SELF, neoForge_1_21_9$happyGhast.getDeltaMovement());
            neoForge_1_21_9$happyGhast.setDeltaMovement(neoForge_1_21_9$happyGhast.getDeltaMovement().scale(0.8F));
        }
        else if (neoForge_1_21_9$happyGhast.isInLava()) { // HAPPY GHAST on LAVA
            neoForge_1_21_9$happyGhast.moveRelative(inLavaAmount, relative);
            neoForge_1_21_9$happyGhast.move(MoverType.SELF, neoForge_1_21_9$happyGhast.getDeltaMovement());
            neoForge_1_21_9$happyGhast.setDeltaMovement(neoForge_1_21_9$happyGhast.getDeltaMovement().scale(0.5F));
        }
        else {
            neoForge_1_21_9$happyGhast.moveRelative(amount, relative);
            neoForge_1_21_9$happyGhast.move(MoverType.SELF, neoForge_1_21_9$happyGhast.getDeltaMovement());
            neoForge_1_21_9$happyGhast.setDeltaMovement(neoForge_1_21_9$happyGhast.getDeltaMovement().scale(0.91F));
        }
    }
}