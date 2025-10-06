package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import java.util.List;

public class OresSmithingTemplateItem extends SmithingTemplateItem {
    public static final Component ORES_UPGRADE_APPLIES_TO =
           lines("smithing_template.ores_upgrade.applies_to", ChatUtils.gold);

    public static final Component ORES_UPGRADE_INGREDIENTS =
           lines("smithing_template.ores_upgrade.ingredients", ChatUtils.gold);

    public static final Component ORES_UPGRADE_BASE_SLOT_DESCRIPTION =
           lines("smithing_template.ores_upgrade.base_slot_description", ChatUtils.gold);

    public static final Component ORES_UPGRADE_ADDITIONS_SLOT_DESCRIPTION =
           lines("smithing_template.ores_upgrade.additions_slot_description", ChatUtils.gold);

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

    public OresSmithingTemplateItem(Component appliesTo, Component ingredients,
                                    Component baseSlotDescription, Component additionsSlotDescription,
                                    List<ResourceLocation> baseSlotEmptyIcons,
                                    List<ResourceLocation> additionalSlotEmptyIcons, Properties properties) {
        super(appliesTo, ingredients, baseSlotDescription, additionsSlotDescription,
              baseSlotEmptyIcons, additionalSlotEmptyIcons, properties);
    }

    public static SmithingTemplateItem createOresUpgradeTemplate(Item.Properties properties) {
        return new SmithingTemplateItem(ORES_UPGRADE_APPLIES_TO, ORES_UPGRADE_INGREDIENTS,
                                        ORES_UPGRADE_BASE_SLOT_DESCRIPTION, ORES_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                                        createOresUpgradeIconList(), List.of(EMPTY_SLOT_INGOT), // Ores Upgrade Material List
                                        properties);
    }

    private static List<ResourceLocation> createOresUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                       EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    // CUSTOM METHOD - Texture empty slot
    private static ResourceLocation emptySlot(String item) {
       return ResourceLocation.withDefaultNamespace("container/slot/" + item);
    }

    // CUSTOM METHOD - Ores Upgrade Smithing Template item description
    private static Component lines(String text, ChatFormatting color) {
        return Component.translatable(Util.makeDescriptionId("item",
                                                             ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, text)))
                        .withStyle(color);
    }
}