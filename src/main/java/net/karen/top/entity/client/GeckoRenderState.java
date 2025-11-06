package net.karen.top.entity.client;

import net.karen.top.entity.variant.GeckoVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class GeckoRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public GeckoVariant variant;
}