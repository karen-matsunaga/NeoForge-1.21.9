package net.karen.mccoursemod.item.custom;

import net.karen.mccoursemod.MccourseMod;
import net.minecraft.Util;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.*;
import java.util.List;

public class OresSmithingTemplateItem extends SmithingTemplateItem {
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

    // CUSTOM METHOD - Upgrade armor or tool empty slot
    public static List<ResourceLocation> createOresUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE,
                       EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    // CUSTOM METHOD - Upgrade material empty slot
    public static List<ResourceLocation> createOresUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    // CUSTOM METHOD - Texture empty slot
    public static ResourceLocation emptySlot(String item) {
       return ResourceLocation.withDefaultNamespace("container/slot/" + item);
    }

    // CUSTOM METHOD - Ores Upgrade Smithing Template item description
    public static Component lines(String text, int red, int green, int blue) {
        return Component.translatable(Util.makeDescriptionId("item",
                                                             ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, text)))
                        .withStyle(Style.EMPTY.withColor(ARGB.color(red, green, blue)));
    }
}