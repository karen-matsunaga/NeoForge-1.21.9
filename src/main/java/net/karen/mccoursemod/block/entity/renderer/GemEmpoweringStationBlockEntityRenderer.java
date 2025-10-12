package net.karen.mccoursemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.mccoursemod.block.custom.GemEmpoweringStationBlock;
import net.karen.mccoursemod.block.entity.GemEmpoweringStationBlockEntity;
import net.karen.mccoursemod.block.entity.render.GemEmpoweringStationBlockEntityRenderState;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GemEmpoweringStationBlockEntityRenderer implements BlockEntityRenderer<GemEmpoweringStationBlockEntity,
                                                                GemEmpoweringStationBlockEntityRenderState> {
    private final ItemModelResolver itemModelResolver;

    public GemEmpoweringStationBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public void extractRenderState(@NotNull GemEmpoweringStationBlockEntity blockEntity,
                                   @NotNull GemEmpoweringStationBlockEntityRenderState state,
                                   float partialTick, @NotNull Vec3 cameraPosition,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay breakProgress) {
        state.gemEmpoweringStationBlockEntity = blockEntity;
        state.itemStack = blockEntity.getRenderStack();
        state.level = blockEntity.getLevel();
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTick, cameraPosition, breakProgress);
    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    @Override
    public @NotNull GemEmpoweringStationBlockEntityRenderState createRenderState() {
        return new GemEmpoweringStationBlockEntityRenderState();
    }

    @Override
    public void submit(GemEmpoweringStationBlockEntityRenderState blockEntity, PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        ItemStackRenderState item = blockEntity.item;
        ItemStack itemStack = blockEntity.itemStack;
        GemEmpoweringStationBlockEntity gemEmpoweringStationBlockEntity = blockEntity.gemEmpoweringStationBlockEntity;
        Level level = blockEntity.level;
        poseStack.pushPose();
        poseStack.translate(0.5F, 0.75F, 0.5F);
        poseStack.scale(0.35F, 0.35F, 0.35F);
        poseStack.mulPose(Axis.YN.rotationDegrees(gemEmpoweringStationBlockEntity.getBlockState()
                                                                                 .getValue(GemEmpoweringStationBlock.FACING)
                                                                                 .toYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(270));
        if (level != null) { // Render item
            blockEntity.lightCoords = getLightLevel(level, gemEmpoweringStationBlockEntity.getBlockPos());
            this.itemModelResolver.updateForTopItem(item, itemStack, ItemDisplayContext.FIXED, level, null, 1);
        }
        item.submit(poseStack, submitNodeCollector, blockEntity.lightCoords, OverlayTexture.NO_OVERLAY, 1);
        poseStack.popPose();
    }
}