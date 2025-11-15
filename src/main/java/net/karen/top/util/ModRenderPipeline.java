package net.karen.top.util;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import net.minecraft.client.renderer.RenderPipelines;

public class ModRenderPipeline {
    // CUSTOM Render Pipeline
    // LINES NO DEPTH -> Block draw line on independent layer (X-RAY block)
    public static final RenderPipeline LINES_NO_DEPTH_RENDER_PIPELINE =
           RenderPipeline.builder(RenderPipelines.LINES_SNIPPET).withLocation(Utils.topPath("pipeline/lines_no_depth"))
                                                                .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST).build();
}