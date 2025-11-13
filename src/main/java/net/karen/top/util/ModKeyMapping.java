package net.karen.top.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.karen.top.Top;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.util.Lazy;
import org.lwjgl.glfw.GLFW;
import static net.karen.top.util.ChatUtils.top;

public class ModKeyMapping {
    // Top custom category
    public static final KeyMapping.Category KEY_CATEGORY_TOP =
           new KeyMapping.Category(ResourceLocation.fromNamespaceAndPath(Top.MOD_ID, "main"));

    // Glowing Blocks custom key input
    public static final String KEY_GLOWING_BLOCKS = "key." + top + "glowing_blocks";
    // Glowing Mobs custom key input
    public static final String KEY_GLOWING_MOBS = "key." + top + "glowing_mobs";
    // Special Bottle custom key inputs
    public static final String KEY_SPECIAL_BOTTLE_STORED = "key." + top + "special_bottle_stored";
    public static final String KEY_SPECIAL_BOTTLE_RESTORED = "key." + top + "special_bottle_restored";
    // Unlock custom key input
    public static final String KEY_UNLOCK = "key." + top + "unlock";
    // Dash custom key input
    public static final String KEY_DASH = "key." + top + "dash";

    // Register all custom key binding
    // GLOWING BLOCKS
    public static final Lazy<KeyMapping> GLOWING_BLOCKS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_GLOWING_BLOCKS, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_TOP));
    // GLOWING MOBS
    public static final Lazy<KeyMapping> GLOWING_MOBS_KEY =
           Lazy.of(() -> new KeyMapping(KEY_GLOWING_MOBS, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_M, KEY_CATEGORY_TOP));
    // SPECIAL BOTTLE
    public static final Lazy<KeyMapping> SPECIAL_BOTTLE_STORED_KEY =
           Lazy.of(() -> new KeyMapping(KEY_SPECIAL_BOTTLE_STORED, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_TOP));

    public static final Lazy<KeyMapping> SPECIAL_BOTTLE_RESTORED_KEY =
           Lazy.of(() -> new KeyMapping(KEY_SPECIAL_BOTTLE_RESTORED, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, KEY_CATEGORY_TOP));
    // UNLOCK
    public static final Lazy<KeyMapping> UNLOCK_KEY =
           Lazy.of(() -> new KeyMapping(KEY_UNLOCK, KeyConflictContext.UNIVERSAL,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_TOP));

    // DASH
    public static final Lazy<KeyMapping> DASH_KEY =
           Lazy.of(() -> new KeyMapping(KEY_DASH, KeyConflictContext.IN_GAME,
                                        InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Y, KEY_CATEGORY_TOP));
}