package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.entity.custom.TomahawkProjectileEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class TomahawkProjectileRenderer extends EntityRenderer<TomahawkProjectileEntity,
                                                               EntityRenderState> {
    private final TomahawkProjectileModel model;
    private TomahawkProjectileEntity entity;
    private float partial;

    public TomahawkProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TomahawkProjectileModel(context.bakeLayer(TomahawkProjectileModel.LAYER_LOCATION));
    }

    @Override
    public @NotNull EntityRenderState createRenderState() {
        return new EntityRenderState();
    }

    @Override
    public void extractRenderState(@NotNull TomahawkProjectileEntity entity,
                                   @NotNull EntityRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        this.entity = entity;
        this.partial = partialTick;
    }

    @Override
    public void submit(@NotNull EntityRenderState state, @NotNull PoseStack poseStack,
                       @NotNull SubmitNodeCollector submitNodeCollector,
                       @NotNull CameraRenderState cameraRenderState) {
        poseStack.pushPose();
        if (!entity.neverIsInGround() && entity != null) {
            poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partial, entity.yRotO, entity.getYRot())));
            poseStack.mulPose(Axis.XP.rotationDegrees(entity.getRenderingRotation() * 5f));
            poseStack.translate(0, -1.0f, 0);
        }
        else {
            if (entity != null && entity.groundedOffset != null) {
                poseStack.mulPose(Axis.YP.rotationDegrees(entity.groundedOffset.y));
                poseStack.mulPose(Axis.XP.rotationDegrees(entity.groundedOffset.x));
                poseStack.translate(0, -1.0f, 0);
            }
        }
        this.model.renderType(this.getTextureLocation());
        poseStack.popPose();
        super.submit(state, poseStack, submitNodeCollector, cameraRenderState);
    }

    // CUSTOM METHOD - Tomahawk texture
    public ResourceLocation getTextureLocation() {
        return ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "textures/entity/tomahawk/tomahawk.png");
    }
}