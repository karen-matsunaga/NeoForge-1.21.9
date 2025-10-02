package net.karen.mccoursemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.mccoursemod.block.entity.PedestalBlockEntity;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity,
                                                                        BlockEntityRenderState> {
    final ItemStackRenderState item = new ItemStackRenderState();
    PedestalBlockEntity pedestalBlockEntity;
    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    @Override
    public void extractRenderState(@NotNull PedestalBlockEntity blockEntity,
                                   @NotNull BlockEntityRenderState renderState,
                                   float partialTick, @NotNull Vec3 vec3,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        this.pedestalBlockEntity = blockEntity;
        BlockEntityRenderer.super.extractRenderState(blockEntity, renderState, partialTick, vec3, crumblingOverlay);
    }

    @Override
    public @NotNull BlockEntityRenderState createRenderState() {
        return new BlockEntityRenderState();
    }

    @Override
    public void submit(@NotNull BlockEntityRenderState blockEntityRenderState, @NotNull PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        PedestalBlockEntity pedestalBlock = this.pedestalBlockEntity;
        ItemStack stack = pedestalBlock.inventory.getItem(0);
        poseStack.pushPose();
        poseStack.translate(0.5f, 1.15f, 0.5f);
        poseStack.scale(0.5f, 0.5f, 0.5f);
        poseStack.mulPose(Axis.YP.rotationDegrees(pedestalBlock.getRenderingRotation()));
//        submitNodeCollector.submitModel(stack, blockEntityRenderState, poseStack,
//                                        null, blockEntityRenderState.lightCoords,
//                                        OverlayTexture.NO_OVERLAY, -1,
//                                        null, blockEntityRenderState.lightCoords, null);

        //if (pedestalBlock.getLevel() != null) {
            //ItemRenderer.renderItem();
//            itemRenderer(stack, ItemDisplayContext.FIXED, getLightLevel(pedestalBlock.getLevel(),
//                            pedestalBlock.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack,
//                    submitNodeCollector, pedestalBlock.getLevel(), 1);
        //}
        poseStack.popPose();
    }
}