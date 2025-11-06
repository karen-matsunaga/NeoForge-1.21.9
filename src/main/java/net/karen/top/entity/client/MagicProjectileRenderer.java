package net.karen.top.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.top.Top;
import net.karen.top.entity.custom.MagicProjectileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class MagicProjectileRenderer extends EntityRenderer<MagicProjectileEntity, EntityRenderState> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Top.MOD_ID,
                                                 "textures/entity/magic_projectile/magic_projectile.png");
    private final MagicProjectileModel model;
    private MagicProjectileEntity entity;
    private float partial;

    public MagicProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new MagicProjectileModel(context.bakeLayer(MagicProjectileModel.MAGIC_PROJECTILE_LAYER));
        this.shadowRadius = 0.5f;
    }

    @Override
    public @NotNull EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void extractRenderState(@NotNull MagicProjectileEntity entity,
                                   @NotNull EntityRenderState reusedState, float partialTick) {
        this.entity = entity;
        this.partial = partialTick;
        super.extractRenderState(entity, reusedState, partialTick);
    }

    @Override
    public void submit(@NotNull EntityRenderState state, @NotNull PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector,
                       @NotNull CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partial, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partial, entity.xRotO, entity.getXRot()) + 90.0F));
        // Render item
        submitNodeCollector.submitModel(this.model, state, poseStack,
                                        RenderType.entityCutout(this.getTextureLocation()),
                                        state.lightCoords, OverlayTexture.NO_OVERLAY, -1,
                                        null, state.outlineColor, null);
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, cameraRenderState);
    }

    // CUSTOM METHOD - Magic texture
    public ResourceLocation getTextureLocation() { return TEXTURE; }
}