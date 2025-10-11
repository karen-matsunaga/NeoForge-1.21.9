package net.karen.mccoursemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.mccoursemod.block.entity.PedestalBlockEntity;
import net.karen.mccoursemod.block.entity.render.PedestalBlockEntityRenderState;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.*;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity,
                                                                        PedestalBlockEntityRenderState> {
    private final ItemModelResolver itemModelResolver;

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(@NotNull PedestalBlockEntity entity, @NotNull PedestalBlockEntityRenderState state,
                                   float partialTick, @NotNull Vec3 vec3,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTick, vec3, crumblingOverlay);
        state.pedestalBlockEntity = entity;
        state.level = entity.getLevel();
        state.itemStack = entity.getItem();
    }

    @Override
    public @NotNull PedestalBlockEntityRenderState createRenderState() { return new PedestalBlockEntityRenderState(); }

    @Override
    public void submit(@NotNull PedestalBlockEntityRenderState state, @NotNull PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 1.15F, 0.5F);
        poseStack.scale(0.5F, 0.5F, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(state.pedestalBlockEntity.getRenderingRotation()));
        if (state.level != null) { // Render item
            state.lightCoords = getLightLevel(state.level, state.pedestalBlockEntity.getBlockPos());
            this.itemModelResolver.updateForTopItem(state.item, state.itemStack,
                                                    ItemDisplayContext.FIXED, state.level, null, 1);
        }
        state.item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }

    // CUSTOM METHOD - ITEM light
    private int getLightLevel(Level world, BlockPos pos) {
        int blockLight = world.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = world.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, Math.max(skyLight, 15));
    }
}