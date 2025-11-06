package net.karen.top.item.custom;

import net.karen.top.Top;
import net.minecraft.Util;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import java.util.function.Consumer;

public class OresSmithingTemplateItem extends SmithingTemplateItem {
    // EMPTY SLOT TEXTURES
    private static final ResourceLocation EMPTY_SLOT_HELMET = emptySlot("helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = emptySlot("chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = emptySlot("leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = emptySlot("boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = emptySlot("hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = emptySlot("axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = emptySlot("sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = emptySlot("shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = emptySlot("pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = emptySlot("ingot");
    private static final ResourceLocation EMPTY_SLOT_REDSTONE_DUST = emptySlot("redstone_dust");
    private static final ResourceLocation EMPTY_SLOT_QUARTZ = emptySlot("quartz");
    private static final ResourceLocation EMPTY_SLOT_EMERALD = emptySlot("emerald");
    private static final ResourceLocation EMPTY_SLOT_DIAMOND = emptySlot("diamond");
    private static final ResourceLocation EMPTY_SLOT_LAPIS_LAZULI = emptySlot("lapis_lazuli");
    private static final ResourceLocation EMPTY_SLOT_AMETHYST_SHARD = emptySlot("amethyst_shard");
    // (APPLIES TO) ITEM TYPE + (INGREDIENTS) ITEM REPAIR
    private final Component appliesTo;
    private final Component ingredients;

    public OresSmithingTemplateItem(Component appliesTo, Component ingredients,
                                    Component baseSlotDescription, Component additionsSlotDescription,
                                    List<ResourceLocation> baseSlotEmptyIcons,
                                    List<ResourceLocation> additionalSlotEmptyIcons, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription,
              baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
        this.appliesTo = appliesTo;
        this.ingredients = ingredients;
    }

    // CUSTOM METHOD - Ores Upgrade armor or tool empty slot
    public static List<ResourceLocation> createOresUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                       EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    // CUSTOM METHOD - Ores Upgrade material empty slot
    public static List<ResourceLocation> createOresUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    // CUSTOM METHOD - Ores Armor trim armor empty slot
    public static List<ResourceLocation> createOresTrimmableArmorIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_BOOTS);
    }

    // CUSTOM METHOD - Ores Armor trim material empty slot
    public static List<ResourceLocation> createOresTrimmableMaterialIconList() {
        return List.of(EMPTY_SLOT_INGOT, EMPTY_SLOT_REDSTONE_DUST, EMPTY_SLOT_LAPIS_LAZULI,
                       EMPTY_SLOT_QUARTZ, EMPTY_SLOT_DIAMOND, EMPTY_SLOT_EMERALD, EMPTY_SLOT_AMETHYST_SHARD);
    }

    // CUSTOM METHOD - Texture empty slot
    public static ResourceLocation emptySlot(String item) {
       return ResourceLocation.withDefaultNamespace("container/slot/" + item);
    }

    // CUSTOM METHOD - Ores Upgrade Smithing Template item description
    public static Component lines(String text, int lore) {
        return Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, text)))
                        .withStyle(Style.EMPTY.withColor(lore));
    }

    // CUSTOM METHOD - Ores Smithing Template item description
    public void appendHoverText(@NotNull ItemStack stack,
                                Item.@NotNull TooltipContext context, @NotNull TooltipDisplay tooltipDisplay,
                                Consumer<Component> tooltipAdder, @NotNull TooltipFlag flag) {
        tooltipAdder.accept(lines("smithing_template", ARGB.color(125, 218, 88)));
        tooltipAdder.accept(lines("smithing_template.applies_to", ARGB.color(93, 226, 231)).copy()
                    .append(CommonComponents.space().append(this.appliesTo)));
        tooltipAdder.accept(lines("smithing_template.ingredients", ARGB.color(233, 93, 210)).copy()
                    .append(CommonComponents.space().append(this.ingredients)));
    }
}