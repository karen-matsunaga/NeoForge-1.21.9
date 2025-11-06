package net.karen.top.util;

import net.karen.top.Top;
import net.minecraft.client.renderer.RenderType;
import java.util.OptionalDouble;

public class ModRenderType {
    // CUSTOM Render Type
    public static final RenderType LINES_NO_DEPTH_RENDER_TYPE =
           RenderType.create(Top.MOD_ID + "lines_no_depth", 3 * 512,
                             ModRenderPipeline.LINES_NO_DEPTH_RENDER_PIPELINE,
                             RenderType.CompositeState.builder()
                                       .setLayeringState(RenderType.VIEW_OFFSET_Z_LAYERING)
                                       .setLineState(new RenderType.LineStateShard(OptionalDouble.of(2)))
                                       .createCompositeState(false));
}