package net.karen.top.entity.client;

import net.karen.top.entity.variant.RhinoVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class RhinoRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public RhinoVariant variant;
}