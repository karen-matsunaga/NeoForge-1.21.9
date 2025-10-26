package net.karen.mccoursemod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.karen.mccoursemod.MccourseMod;
import net.minecraft.client.model.ShieldModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Set;

public class ShieldSpecialModelRenderer implements SpecialModelRenderer<DataComponentMap> {
    private final MaterialSet materials;
    private final ShieldModel model;
    public static final Material ALEXANDRITE_SHIELD_BASE =
           new Material(Sheets.SHIELD_SHEET,
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "entity/alexandrite_shield_base"));
    public static final Material ALEXANDRITE_SHIELD_BASE_NO_PATTERN =
           new Material(Sheets.SHIELD_SHEET,
                        ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID,
                                                              "entity/alexandrite_shield_base_nopattern"));

    public ShieldSpecialModelRenderer(MaterialSet materials, ShieldModel model) {
        this.materials = materials;
        this.model = model;
    }

    @Nullable
    public DataComponentMap extractArgument(ItemStack stack) { return stack.immutableComponents(); }

    @Override
    public void submit(@Nullable DataComponentMap components, @NotNull ItemDisplayContext context,
                       @NotNull PoseStack poseStack, @NotNull SubmitNodeCollector submitNodeCollector,
                       int i, int i1, boolean b, int i2) {
        BannerPatternLayers bannerpatternlayers =
              components != null ? components.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY)
                                 : BannerPatternLayers.EMPTY;
        DyeColor dyecolor = components != null ? components.get(DataComponents.BASE_COLOR) : null;
        boolean flag = !bannerpatternlayers.layers().isEmpty() || dyecolor != null;
        poseStack.pushPose();
        poseStack.scale(1.0F, -1.0F, -1.0F);
        Material material = flag ? ALEXANDRITE_SHIELD_BASE : ALEXANDRITE_SHIELD_BASE_NO_PATTERN;
        submitNodeCollector.submitModelPart(this.model.handle(), poseStack,
                                            this.model.renderType(material.atlasLocation()),
                                            i, i1, this.materials.get(material), false,
                                            false, -1, null, i2);
        if (flag) {
            BannerRenderer.submitPatterns(this.materials, poseStack, submitNodeCollector,
                                          i, i1, this.model, Unit.INSTANCE, material,
                                          false, Objects.requireNonNullElse(dyecolor, DyeColor.WHITE),
                                          bannerpatternlayers, b, null, i2);
        }
        else {
            submitNodeCollector.submitModelPart(this.model.plate(), poseStack,
                                                this.model.renderType(material.atlasLocation()),
                                                i, i1, this.materials.get(material), false,
                                                b, -1, null, i2);
        }
        poseStack.popPose();
    }

    public void getExtents(@NotNull Set<Vector3f> vector3fs) {
        PoseStack posestack = new PoseStack();
        posestack.scale(1.0F, -1.0F, -1.0F);
        this.model.root().getExtentsForGui(posestack, vector3fs);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final ShieldSpecialModelRenderer.Unbaked INSTANCE = new ShieldSpecialModelRenderer.Unbaked();
        public static final MapCodec<ShieldSpecialModelRenderer.Unbaked> MAP_CODEC = MapCodec.unit(INSTANCE);

        @Override
        public SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
            return new ShieldSpecialModelRenderer(context.materials(),
                                                  new ShieldModel(context.entityModelSet().bakeLayer(ModelLayers.SHIELD)));
        }

        public @NotNull MapCodec<ShieldSpecialModelRenderer.Unbaked> type() { return MAP_CODEC; }
    }
}