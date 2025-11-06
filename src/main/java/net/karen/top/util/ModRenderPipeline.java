package net.karen.top.util;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import net.karen.top.Top;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;

public class ModRenderPipeline {
    // CUSTOM Render Pipeline
    // LINES NO DEPTH -> Block draw line on independent layer
    public static final RenderPipeline LINES_NO_DEPTH_RENDER_PIPELINE =
           RenderPipeline.builder(RenderPipelines.LINES_SNIPPET)
                         .withLocation(ResourceLocation.fromNamespaceAndPath(Top.MOD_ID,
                                       "pipeline/lines_no_depth"))
                         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
                         .build();
}