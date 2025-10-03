package net.karen.mccoursemod.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.mccoursemod.block.entity.PedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
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

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity,
                                                                        BlockEntityRenderState> {

    PedestalBlockEntity pedestalBlockEntity;
    public final ItemStackRenderState item = new ItemStackRenderState();

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void extractRenderState(@NotNull PedestalBlockEntity entity,
                                   @NotNull BlockEntityRenderState state,
                                   float partialTick, @NotNull Vec3 vec3,
                                   @Nullable ModelFeatureRenderer.CrumblingOverlay crumblingOverlay) {
        this.pedestalBlockEntity = entity;
        // Render item
        ItemModelResolver itemModelResolver = Minecraft.getInstance().getItemModelResolver();
        itemModelResolver.updateForTopItem(item, entity.inventory.getResource(0).toStack(),
                                           ItemDisplayContext.GUI, entity.getLevel(), null, 1);
        state.blockPos = entity.getBlockPos();
        state.blockState = entity.getBlockState();
        if (entity.getLevel() != null) { state.lightCoords = getLightLevel(entity.getLevel(), entity.getBlockPos()); }
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTick, vec3, crumblingOverlay);
    }

    @Override
    public @NotNull BlockEntityRenderState createRenderState() { return new BlockEntityRenderState(); }

    @Override
    public void submit(@NotNull BlockEntityRenderState state, @NotNull PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector, @NotNull CameraRenderState cameraRenderState) {
        if (!item.isEmpty()) {
            PedestalBlockEntity pedestalBlock = this.pedestalBlockEntity;
            poseStack.pushPose();
            poseStack.translate(0.5F, 1.15F, 0.5F);
            poseStack.scale(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(pedestalBlock.getRenderingRotation()));
            item.submit(poseStack, submitNodeCollector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
            poseStack.popPose();
        }
    }

    // CUSTOM METHOD - Item light
    private int getLightLevel(Level world, BlockPos pos) {
        int bLight = world.getBrightness(LightLayer.BLOCK, pos);
        int sLight = world.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, Math.max(sLight, 15));
    }
}