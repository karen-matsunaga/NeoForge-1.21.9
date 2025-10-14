package net.karen.mccoursemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.mccoursemod.block.custom.GemEmpoweringStationBlock;
import net.karen.mccoursemod.block.entity.GemEmpoweringStationBlockEntity;
import net.karen.mccoursemod.block.entity.renderstate.GemEmpoweringStationBlockEntityRenderState;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GemEmpoweringStationBlockEntityRenderer
       implements BlockEntityRenderer<GemEmpoweringStationBlockEntity, GemEmpoweringStationBlockEntityRenderState> {
    private final ItemModelResolver itemModelResolver;

    public GemEmpoweringStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(@NotNull GemEmpoweringStationBlockEntity entity,
                                   @NotNull GemEmpoweringStationBlockEntityRenderState state,
                                   float partialTick, @NotNull Vec3 cameraPosition,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTick, cameraPosition, breakProgress);
        state.gemEmpoweringStationBlockEntity = entity;
        state.itemStack = entity.getRenderStack();
        state.gemEmpoweringStationBEBlockPos = state.gemEmpoweringStationBlockEntity.getBlockPos();
        state.gemEmpoweringStationBEBlockState = state.gemEmpoweringStationBlockEntity.getBlockState();
        state.level = entity.getLevel();
        if (state.level != null) { // Render item
            state.lightCoords = getLightLevel(state.level, state.gemEmpoweringStationBEBlockPos);
            this.itemModelResolver.updateForTopItem(state.item, state.itemStack, ItemDisplayContext.FIXED,
                                                    state.level, null, 1);
        }
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int blockLight = level.getBrightness(LightLayer.BLOCK, pos);
        int skyLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(blockLight, Math.max(skyLight, 15));
    }

    @Override
    public @NotNull GemEmpoweringStationBlockEntityRenderState createRenderState() {
        return new GemEmpoweringStationBlockEntityRenderState();
    }

    @Override
    public void submit(GemEmpoweringStationBlockEntityRenderState state, PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        ItemStackRenderState item = state.item;
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.75F, 0.5F);
        poseStack.scale(0.35F, 0.35F, 0.35F);
        poseStack.mulPose(Axis.YN.rotationDegrees(state.gemEmpoweringStationBEBlockState
                                                       .getValue(GemEmpoweringStationBlock.FACING).toYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(270));
        item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
}