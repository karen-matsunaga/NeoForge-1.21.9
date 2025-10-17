package net.karen.mccoursemod.util;

import com.mojang.datafixers.util.Either;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ChatUtils {
    // VANILLA colors
    public static ChatFormatting blue = ChatFormatting.BLUE, darkBlue = ChatFormatting.DARK_BLUE,
                                 aqua = ChatFormatting.AQUA, darkAqua = ChatFormatting.DARK_AQUA,
                                 purple = ChatFormatting.LIGHT_PURPLE, darkPurple = ChatFormatting.DARK_PURPLE,
                                 green = ChatFormatting.GREEN, darkGreen = ChatFormatting.DARK_GREEN,
                                 gray = ChatFormatting.GRAY, darkGray = ChatFormatting.DARK_GRAY,
                                 yellow = ChatFormatting.YELLOW, gold = ChatFormatting.GOLD,
                                 red = ChatFormatting.RED, darkRed = ChatFormatting.DARK_RED,
                                 black = ChatFormatting.BLACK, white = ChatFormatting.WHITE,
                                 bold = ChatFormatting.BOLD, italic = ChatFormatting.ITALIC;

    public static int greenColor = ARGB.color(85, 255, 85);
    public static int darkGreenColor = ARGB.color(0, 170, 0);
    public static int whiteColor = ARGB.color(255, 255, 255);
    public static int redColor = ARGB.color(255, 85, 85);

    public static int blueBedrockColor = ARGB.color(33, 73, 123);
    public static int redBedrockColor = ARGB.color(151, 22, 7);

    // RGB colors
    public static final int[] COLORS = { 0xff5555, 0xffaa00, 0xffff55, 0x55ff55,
                                         0x55ffff, 0x5555ff, 0xff55ff };

    // ORES colors
    public static int bismuthColor = ARGB.color(232, 84, 128);
    public static int alexandriteColor = ARGB.color(48, 213, 200);
    public static int pinkColor = ARGB.color(244, 51, 193);

    public static int magicColor = ARGB.color(155, 39, 85);

    public static int enchantColor = ARGB.color(45, 172, 62);
    public static int disenchantIndColor = ARGB.color(155, 45, 172);
    public static int disenchantGroColor = ARGB.color(47, 178, 181);

    public static int elevatorColor = ARGB.color(255, 255, 0);
    public static int generatorColor = ARGB.color(229, 117, 188);

    public static int craftingColor = ARGB.color(64, 174, 60);

    public static int bloodColor = ARGB.color(146, 28, 56);
    public static int walnutColor = ARGB.color(210, 174, 99);

    public static int chairColor = ARGB.color(242, 162, 76);

    public static int pedestalColor = ARGB.color(138, 140, 142);
    public static int growthChamberColor = ARGB.color(63, 62, 66);
    public static int gemEmpoweringStationColor = ARGB.color(180, 212, 225);
    public static int kaupenFurnaceColor = ARGB.color(78, 145, 255);

    public static int forcedGlassColor = ARGB.color(163, 155, 245, 55);
    public static int soundColor = ARGB.color(107, 213, 214);

    public static int enderColor = ARGB.color(16, 94, 81);
    public static int greenEnderColor = ARGB.color(44, 205, 83);
    public static int limeEnderColor = ARGB.color(115, 254, 37);
    public static int blackEnderColor = ARGB.color(44, 44, 44);
    public static int magentaEnderColor = ARGB.color(254, 37, 242);
    public static int purpleEnderColor = ARGB.color(142, 44, 205);
    public static int orangeEnderColor = ARGB.color(254, 96, 37);
    public static int pinkEnderColor = ARGB.color(244, 140, 224);
    public static int cyanEnderColor = ARGB.color(52, 133, 153);
    public static int brownEnderColor = ARGB.color(139, 64, 35);
    public static int grayEnderColor = ARGB.color(113, 113, 113);
    public static int redEnderColor = ARGB.color(205, 44, 59);
    public static int yellowEnderColor = ARGB.color(195, 205, 44);
    public static int blueEnderColor = ARGB.color(44, 131, 205);
    public static int whiteEnderColor = ARGB.color(229, 229, 229);

    public static int netherColor = ARGB.color(253, 255, 168);
    public static int gunpowderColor = ARGB.color(138, 138, 138);
    public static int rottenColor = ARGB.color(180, 68, 32);
    public static int blazeColor = ARGB.color(255, 193, 0);
    public static int phantomColor = ARGB.color(220, 217, 192);
    public static int stringColor = ARGB.color(219, 219, 219);
    public static int spiderColor = ARGB.color(123, 13, 39);
    public static int fermentedColor = ARGB.color(193, 67, 84);
    public static int sugarColor = ARGB.color(213, 213, 223);
    public static int sugarCaneColor = ARGB.color(124, 204, 53);

    public static int rubyColor = ARGB.color(199, 49, 49);
    public static int ruby1Color = ARGB.color(149, 26, 35);
    public static int ruby2Color = ARGB.color(159, 30, 31);
    public static int ruby3Color = ARGB.color(221, 131, 50);

    public static int snapdragonColor = ARGB.color(166, 94, 225);

    public static int coloredLoreColor = ARGB.color(50, 193, 133);
    public static int coloredCustomNameColor = ARGB.color(50, 193, 164);

    public static int kaupenPortalLoreColor = ARGB.color(101, 158, 205);
    public static int kaupenPortalCustomNameColor = ARGB.color(61, 138, 201);

    public static int chiselLoreColor = ARGB.color(241, 131, 8);
    public static int restoreLoreColor = ARGB.color(149, 240, 244);
    public static int farmerLoreColor = ARGB.color(55, 60, 155);
    public static int cattailLoreColor = ARGB.color(116, 90, 51);

    public static int elytraLoreColor = ARGB.color(20, 255, 196);
    public static int kaupenTrimLoreColor = ARGB.color(131, 195, 223);
    public static int copperUpgradeSmithingLoreColor = ARGB.color(156, 69, 41);

    public static int fishingRodLoreColor = ARGB.color(170, 0, 170);

    public static int copperLoreColor = ARGB.color(156, 69, 41);
    public static int lapisLoreColor = ARGB.color(39, 63, 178);
    public static int redstoneLoreColor = ARGB.color(218, 48, 75);
    public static int diamondLoreColor = ARGB.color(39, 178, 154);
    public static int goldLoreColor = ARGB.color(255, 255, 35);
    public static int ironLoreColor = ARGB.color(130, 130, 130);
    public static int stoneLoreColor = ARGB.color(99, 99, 99);
    public static int woodenLoreColor = ARGB.color(136, 102, 38);
    public static int netheriteLoreColor = ARGB.color(74, 41, 64);

    public static int barBrawlLoreColor = ARGB.color(99, 155, 255);

    public static int growthLoreColor = ARGB.color(224, 140, 56);

    public static int geckoLoreColor = ARGB.color(97, 255, 150);
    public static int rhinoLoreColor = ARGB.color(122, 135, 135);

    public static int luckGeneralLoreColor = ARGB.color(76, 165, 228);
    public static int luckPickaxeLoreColor = ARGB.color(93, 220, 242);
    public static int luckWeaponLoreColor = ARGB.color(242, 93, 110);

    public static int torchBallLoreColor = ARGB.color(255, 222, 89);
    public static int bouncyBallsLoreColor = ARGB.color(241, 150, 255);
    public static int dataTabletLoreColor = ARGB.color(132, 126, 135);
    public static int tomahawkLoreColor = ARGB.color(106, 112, 118);
    public static int radiationStaffLoreColor = ARGB.color(118, 193, 66);
    public static int diceLoreColor = ARGB.color(234, 229, 222);
    public static int mccourseModBottleLoreColor = ARGB.color(89, 204, 148);

    public static int metalDetectorLoreColor = ARGB.color(68, 68, 68);
    public static int metalDetectorColor = ARGB.color(154, 92, 198);

    // Glowing Blocks X-RAY block bounding boxes
    public static final Map<TagKey<Block>, Integer> renderColors =
           Map.ofEntries(Map.entry(Tags.Blocks.ORES_COAL, color(169, 169,169)),
                         Map.entry(Tags.Blocks.ORES_COPPER, color(255, 140, 0)),
                         Map.entry(Tags.Blocks.ORES_DIAMOND, color(0, 254, 255)),
                         Map.entry(Tags.Blocks.ORES_EMERALD, color(49, 200, 49)),
                         Map.entry(Tags.Blocks.ORES_GOLD, color(255, 215, 0)),
                         Map.entry(Tags.Blocks.ORES_IRON, color(211, 211, 211)),
                         Map.entry(Tags.Blocks.ORES_LAPIS, color(0, 0, 255)),
                         Map.entry(Tags.Blocks.ORES_REDSTONE, color(179, 0, 0)),
                         Map.entry(Tags.Blocks.ORES_NETHERITE_SCRAP, color(210, 44, 248)),
                         Map.entry(ModTags.Blocks.MCCOURSE_MOD_ORES, color(255, 192, 235)),
                         Map.entry(ModTags.Blocks.SPECIAL_METAL_DETECTOR_VALUABLES, color(204, 0, 0)));

    // CUSTOM METHOD - Glowing Blocks X-RAY block bounding box colors
    public static int color(int red, int green, int blue) {
        return ARGB.color(255, red, green, blue);
    }

    // CUSTOM METHOD - COMPONENT LITERAL without color
    public static Component standardLiteral(String message) {
        return Component.literal(message);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE without color
    public static Component standardTranslatable(String message) {
        return Component.translatable(message);
    }

    // CUSTOM METHOD - COMPONENT LITERAL with one color
    public static Component componentLiteral(String message, ChatFormatting color) {
        return Component.literal(message).withStyle(color);
    }

    // CUSTOM METHOD - COMPONENT LITERAL with int color
    public static Component componentLiteralIntColor(String message, int color) {
        return Component.literal(message).withStyle(Style.EMPTY).withColor(color);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE with one color
    public static Component componentTranslatable(String message, ChatFormatting color) {
        return Component.translatable(message).withStyle(color);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE with int color
    public static Component componentTranslatableIntColor(String message, int color) {
        return Component.translatable(message).withStyle(Style.EMPTY.withItalic(false)).withColor(color);
    }

    // CUSTOM METHOD - COMPONENT LITERAL with one color
    public static Component componentLiteralBold(String message, ChatFormatting color) {
        return Component.literal(message).withStyle(color).withStyle(bold);
    }

    // CUSTOM METHOD - COMPONENT TRANSLATABLE with one color
    public static Component componentTranslatableBold(String message, ChatFormatting color) {
        return Component.translatable(message).withStyle(color).withStyle(bold);
    }

    // CUSTOM METHOD - Message appears on screen without BOLD format
    public static void player(Player player, String message, ChatFormatting color) {
        player.displayClientMessage(componentLiteral(message, color), true);
    }

    // CUSTOM METHOD - Message appears on screen without COLOR
    public static void playerDefault(Player player, String message) {
        player.displayClientMessage(standardLiteral(message), true);
    }

    // CUSTOM METHOD - Message appears on screen without BOLD format (en_us.json file)
    public static void playerDisplayTranslatable(Player player, String message) {
        player.displayClientMessage(standardTranslatable(message), true);
    }

    // CUSTOM METHOD - Message appears on SCREEN without BOLD format
    public static void playDisplayLiteral(Player player, Component message) {
        player.displayClientMessage(message, true);
    }

    // CUSTOM METHOD - Message appears on CHAT without BOLD format
    public static void playDisplayLiteralFalse(Player player, Component message) {
        player.displayClientMessage(message, false);
    }

    // CUSTOM METHOD - Using RGB colors and BOLD format
    public static MutableComponent description(String tooltip, ChatFormatting color,
                                               List<Boolean> curse) {
        boolean curseFalse = curse.get(0), curseTrue = curse.get(1);
        return Component.translatable(tooltip)
                        .withStyle(Style.EMPTY.withColor(color).withBold(curseFalse).withItalic(curseTrue));
    }

    // CUSTOM METHOD - Tooltip Line Literal with RGB colors -> WITH Item name
    public static void tooltipLineLiteralRGB(List<Component> tooltip,
                                             int[] COLORS, ItemStack stack, String message) {
        int shift = (int) (System.currentTimeMillis() / 200L % COLORS.length); // Calculates color shift based on time
        MutableComponent minerText = Component.literal(""); // Animated text for "Miner Bow" with RGB wave effect
        String text = itemLine(stack.getItem().getDescriptionId(), "item.mccoursemod.", "", "_", " "),
                 on = itemLines(text) + message;
        for (int i = 0; i < on.length(); i++) {
            int colorIndex = (i - shift + COLORS.length) % COLORS.length; // Adjust to move colors from left to right
            minerText.append(Component.literal(String.valueOf(on.charAt(i)))
                     .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLORS[colorIndex]))));
        }
        tooltip.add(minerText);
    }

    // CUSTOM METHOD - Tooltip Line Literal with RGB colors -> WITHOUT Item name
    public static void tooltipLineLiteralRGBColors(List<Component> tooltip,
                                                   int[] COLORS, String message) {
        int shift = (int) (System.currentTimeMillis() / 200L % COLORS.length); // Calculates color shift based on time
        MutableComponent minerText = Component.literal(""); // Animated text for "Miner Bow" with RGB wave effect
        for (int i = 0; i < message.length(); i++) {
            int colorIndex = (i - shift + COLORS.length) % COLORS.length; // Adjust to move colors from left to right
            minerText.append(Component.literal(String.valueOf(message.charAt(i)))
                     .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLORS[colorIndex]))));
        }
        tooltip.add(minerText);
    }

    // CUSTOM METHOD - Renamed string on TOOLTIP -> Ex: Fortune etc. (Only one word) -> Capitalize First Letters
    public static String itemLine(String var, String old, String value,
                                  String old2, String value2) {
        String name = var.replace(old, value).replace(old2, value2), firstIndex = name.substring(0, 1).toUpperCase();
        return firstIndex + name.substring(1);
    }

    // CUSTOM METHOD - Renamed string on TOOLTIP -> Ex: Mccourse Generator etc. (Two+ words) -> Capitalize First Letters
    public static String itemLines(String input) {
        String[] words = input.split(" "); // Separated words
        StringJoiner capitalized = new StringJoiner(" "); // Spaced words
        for (String word : words) { // For each word
            if (!word.isEmpty()) {
                capitalized.add(word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase());
            }
        }
        return capitalized.toString(); // Joined words with capitalize words
    }

    // CUSTOM METHOD - SPLIT STRING
    public static String splitWord(String word) {
        return word.replace("_", " ");
    }

    // CUSTOM METHOD - Enchantment color
    public static ChatFormatting getEnchantmentColor(Holder<Enchantment> holder) {
        Map<TagKey<Enchantment>, ChatFormatting> enchantmentColors = new HashMap<>();
        enchantmentColors.put(EnchantmentTags.ARMOR_EXCLUSIVE, gold); // FULL ARMOR
        enchantmentColors.put(ModTags.Enchantments.HELMET_ENCHANTMENTS, darkAqua); // HELMET ARMOR
        enchantmentColors.put(ModTags.Enchantments.CHESTPLATE_ENCHANTMENTS, purple); // CHESTPLATE ARMOR
        enchantmentColors.put(ModTags.Enchantments.LEGGINGS_ENCHANTMENTS, darkGray); // LEGGINGS ARMOR
        enchantmentColors.put(ModTags.Enchantments.BOOTS_ENCHANTMENTS, blue); // BOOTS ARMOR
        enchantmentColors.put(ModTags.Enchantments.FISHING_ENCHANTMENTS, yellow); // FISHING ROD
        enchantmentColors.put(ModTags.Enchantments.MINING_ENCHANTMENTS, darkPurple); // PICKAXE, AXE, SHOVEL ETC.
        enchantmentColors.put(ModTags.Enchantments.DURABILITY_ENCHANTMENTS, darkBlue); // UNBREAKING, MENDING
        enchantmentColors.put(ModTags.Enchantments.TRIDENT_ENCHANTMENTS, aqua); // TRIDENT
        enchantmentColors.put(ModTags.Enchantments.SWORD_ENCHANTMENTS, darkRed); // SWORD
        enchantmentColors.put(ModTags.Enchantments.BOW_ENCHANTMENTS, green); // BOW
        enchantmentColors.put(ModTags.Enchantments.CROSSBOW_ENCHANTMENTS, darkGreen); // CROSSBOW
        enchantmentColors.put(ModTags.Enchantments.MACE_ENCHANTMENTS, white); // MACE
        for (Map.Entry<TagKey<Enchantment>, ChatFormatting> value : enchantmentColors.entrySet()) {
            if (holder.is(value.getKey())) { return value.getValue(); }
        }
        return gray;
    }

    // CUSTOM METHOD - Enchantment Icon compatibility
    public static String icon(Holder<Enchantment> holder) {
        String armor = "§6⭐", pick = "§5⛏", hammer = "§3🔨", shield = "§1🛡",
                 bow = "§a\uD83C\uDFF9", sword = "§4\uD83D\uDDE1", trident = "§b\uD83D\uDD31",
                fish = "§e\uD83C\uDFA3", axe = "§5\uD83E\uDE93";
        Map<List<TagKey<Enchantment>>, String> enchantmentIcons = new HashMap<>();
        enchantmentIcons.put(List.of(EnchantmentTags.CURSE), "§c🔥"); // CURSE
        enchantmentIcons.put(List.of(EnchantmentTags.ARMOR_EXCLUSIVE), armor); // ARMOR
        // HELMET, CHESTPLATE, LEGGINGS, BOOTS ARMORS AND MACE
        enchantmentIcons.put(List.of(ModTags.Enchantments.HELMET_ENCHANTMENTS, ModTags.Enchantments.CHESTPLATE_ENCHANTMENTS,
                                     ModTags.Enchantments.LEGGINGS_ENCHANTMENTS, ModTags.Enchantments.BOOTS_ENCHANTMENTS,
                                     ModTags.Enchantments.MACE_ENCHANTMENTS), "");
        enchantmentIcons.put(List.of(ModTags.Enchantments.FISHING_ENCHANTMENTS), fish); // FISHING ROD
        enchantmentIcons.put(List.of(ModTags.Enchantments.MINING_ENCHANTMENTS), pick + " " + axe); // PICKAXE + AXE + SHOVEL
        enchantmentIcons.put(List.of(ModTags.Enchantments.DURABILITY_ENCHANTMENTS),
                                     axe + " " + fish + " " + pick + " " + armor + " " + sword + " " +
                                     bow + " " + trident + " " + hammer + " " + shield); // DURABILITY
        enchantmentIcons.put(List.of(ModTags.Enchantments.TRIDENT_ENCHANTMENTS), trident); // TRIDENT
        enchantmentIcons.put(List.of(ModTags.Enchantments.SWORD_ENCHANTMENTS), sword); // SWORD
        enchantmentIcons.put(List.of(ModTags.Enchantments.BOW_ENCHANTMENTS,
                                     ModTags.Enchantments.CROSSBOW_ENCHANTMENTS), bow); // BOW or CROSSBOW
        for (Map.Entry<List<TagKey<Enchantment>>, String> value : enchantmentIcons.entrySet()) {
            for (TagKey<Enchantment> tagKey : value.getKey()) {
                if (holder.is(tagKey)) { return value.getValue(); }
            }
        }
        return "";
    }

    // CUSTOM METHOD - Display Icon message TOOLTIP -> IMAGE TOOLTIP COMPONENT
    public static void image(List<Either<FormattedText, TooltipComponent>> list,
                             Item item, int width, int height, String text, Boolean bool) {
        if (bool) { list.add(Either.right(new ImageTooltipComponent(new ItemStack(item), width, height, text))); }
    }

    // CUSTOM METHOD - Display Icon messages TOOLTIP -> MULTI IMAGE TOOLTIP COMPONENT
    public static void text(List<Either<FormattedText, TooltipComponent>> list,
                            List<Item> item, int size, String text, Boolean bool) {
        if (bool) {
            list.add(Either.right(new MultiImageTooltipComponent(List.of(new ItemStack(item.getFirst()),
                                                                         new ItemStack(item.get(1))),
                                                                 size, text)));
        }
    }

    // CUSTOM METHOD - GLOWING MOBS message
    public static void glow(Player player, boolean test,
                            String message, String message1) {
        player.displayClientMessage(componentLiteral("Glowing " + (test ? message : message1),
                                                     (test ? green : red)), true);
    }

    // CUSTOM METHOD - Player death message display
    public static Component chatMessage(ResourceKey<Level> level, Player player,
                                        BlockPos pos, ChatFormatting color) {
        String playerName = player.getGameProfile().name();
        int x = pos.getX(), y = pos.getY(), z = pos.getZ();
        String deathTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String dimensionPath = level.location().getPath();
        return Component.literal(playerName + " died at [X: " + x + ", Y: " + y + ", Z: " + z + "] " +
                                 deathTime + " " + dimensionName(dimensionPath) + " dimension.")
                        .withStyle(Style.EMPTY.withColor(color).withItalic(false));
    }

    // CUSTOM METHOD - Player death message
    public static String dimensionName(String dimensionPath) {
        return switch (dimensionPath) {
               case "overworld" -> "Overworld";
               case "the_nether" -> "Nether";
               case "the_end" -> "End";
               default -> "Kaupendim";
        };
    }

    // CUSTOM METHOD - Player dimension color name
    public static int dimensionColorName(String dimensionPath) {
        return switch (dimensionPath) {
            case "overworld" -> ARGB.color(125, 218, 88);
            case "the_nether" -> ARGB.color(133, 34, 36);
            case "the_end" -> ARGB.color(217, 91, 255);
            default -> ARGB.color(129, 214, 235);
        };
    }

    // CUSTOM METHOD - Player dimension
    public static Component dimension(String dimensionPath) {
        return Component.literal("Dimension: ")
                        .append(componentLiteralIntColor(dimensionName(dimensionPath), dimensionColorName(dimensionPath)));
    }

    // CUSTOM METHOD - Player biome name
    public static String biomeName(String biome) {
        String upperCase = biome.substring(0, 1).toUpperCase();
        String lowerCase = biome.substring(1).toLowerCase();
        String biomeFormatted = upperCase + lowerCase;
        return itemLines(splitWord(biomeFormatted));
    }

    // CUSTOM METHOD - Player biome color name
    public static Component biomeColor(String biome, String dimension) {
        return Component.literal("Biome: ").append(componentLiteralIntColor(biomeName(biome), dimensionColorName(dimension)));
    }

    // CUSTOM METHOD - Player day number
    // Credits by giok3r (Where Am I?) MIT License - https://github.com/giok3r/Where-Am-I/blob/1.21.x/TEMPLATE_LICENSE.txt
    // https://github.com/giok3r/Where-Am-I/blob/1.21.x/src/main/java/net/giok3r/whereami/ClientEvents.java
    public static Component dayNumber(long dayTime) {
        int time = (int) dayTime % 24000;
        int hour;
        String amOrPm;
        if (time < 7000) { // 6AM - 12PM
            hour = time / 1000 + 6;
            if (hour == 12) { amOrPm = "PM"; }
            else { amOrPm = "AM"; }
        }
        else if (time < 19000) { // 1PM - 12AM
            hour = time / 1000 - 6;
            if (hour == 12) { amOrPm = "AM"; }
            else { amOrPm = "PM"; }
        }
        else { // 1AM - 5AM
            hour = time / 1000 - 18;
            amOrPm = "AM";
        }
        int minute = (int) ((time % 1000) * 0.06);
        int day = (int) dayTime / 24000;
        return Component.literal("Time: " + String.format("%d:%02d %s", hour, minute, amOrPm))
                        .append(componentLiteralIntColor(String.format(" [Day %d]", day), ARGB.color(113, 255, 241)));
    }

    // CUSTOM METHOD - Total light, Skylight and Block light + [X, Y, Z] Coordinates
    public static Component coordinate(double x, double y, double z) {
        return Component.literal("X: ").append(componentLiteral(String.format("%.3f", x), aqua))
                        .append(standardLiteral("  Y: ")).append(componentLiteral(String.format("%.5f", y), purple))
                        .append(standardLiteral("  Z: ")).append(componentLiteral(String.format("%.3f", z), gold));
    }

    // CUSTOM METHOD - Total light, Skylight and Block light numbers
    public static Component light(int totalLight, int skyLight, int blockLight) {
        return Component.literal("Light:").append(customStyle(" " + totalLight, totalLight))
                        .append(standardLiteral("  Sky:")).append(customStyle(" " + skyLight, skyLight))
                        .append(standardLiteral("  Block:")).append(customStyle(" " + blockLight, blockLight));
    }

    // CUSTOM METHOD - Total light, Skylight and Block light colors numbers
    public static Component customStyle(String number, int light) {
        return Component.literal(String.valueOf(number))
                        .setStyle(Style.EMPTY.withColor(light > 6 ? ARGB.color(50, 252, 118)
                                                                  : ARGB.color(255, 24, 24)));
    }

    // CUSTOM METHOD - ITEM NAME
    public static Component rgbItemName(ItemStack stack) {
        int shift = (int) (System.currentTimeMillis() / 200L % COLORS.length); // Calculates color shift based on time
        MutableComponent minerText = Component.literal(""); // Animated text for "Miner Bow" with RGB wave effect
        String text = itemLine(stack.getItem().getDescriptionId(), "item.mccoursemod.", "", "_", " ");
        String on = itemLines(text);
        for (int i = 0; i < on.length(); i++) {
            int colorIndex = (i - shift + COLORS.length) % COLORS.length; // Adjust to move colors from left to right
            minerText.append(Component.translatable(String.valueOf(on.charAt(i)))
                     .setStyle(Style.EMPTY.withColor(TextColor.fromRgb(COLORS[colorIndex]))));
        }
        return minerText;
    }
}