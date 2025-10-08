package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.block.ModBlocks;
import net.karen.mccoursemod.component.ModDataComponentTypes;
import net.karen.mccoursemod.component.custom.FoundBlock;
import net.karen.mccoursemod.enchantment.ModEnchantments;
import net.karen.mccoursemod.item.ModItems;
import net.karen.mccoursemod.item.custom.*;
import net.karen.mccoursemod.util.ModTags;
import net.karen.mccoursemod.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow public abstract String toString();

    @Shadow public abstract <T extends TooltipProvider>
                            void addToTooltip(DataComponentType<T> component, Item.TooltipContext context,
                                              TooltipDisplay display, Consumer<Component> consumer,
                                              TooltipFlag flag);

    @Inject(method = "getTooltipLines", at = @At("RETURN"), cancellable = true)
    private void getTooltipLines(Item.TooltipContext context, Player player,
                                 TooltipFlag flag, CallbackInfoReturnable<List<Component>> cir) {
        ItemStack stack = (ItemStack) (Object) this; // Get all blocks, items, etc.
        List<Component> tooltip = new ArrayList<>(cir.getReturnValue()); // Old tooltip
        Level level = Minecraft.getInstance().level;
        Item item = stack.getItem();
        // ** CUSTOM BLOCKS **
        // Item checked is MAGIC block + Added more information about MAGIC block
        if (stack.is(ModBlocks.MAGIC.get().asItem())) {
            Component original = tooltip.getFirst(), // Original tooltip line 0
                       colored = original.copy().withStyle(style -> style.withColor(0x00ff00));
            tooltip.set(0, colored); // Change only the name (first line of the tooltip) -> Color not appears on screen
            tooltip.add(standardTranslatable("tooltip.mccoursemod.magic_block"));
        }
        // Item checked is SOUND block + Added more information about SOUND block
        if (stack.is(ModBlocks.SOUND.get().asItem())) {
            tooltip.add(standardTranslatable("tooltip.mccoursemod.sound"));
        }
        // ** CUSTOM ITEMS **
        // Added more information about GROWTH item
        if (stack.is(ModItems.GROWTH.get().asItem())) {
            tooltip.add(componentTranslatable("tooltip.mccoursemod.growth", aqua));
        }
        // TORCH BALL item
        if (stack.is(ModItems.TORCH_BALL.get().asItem())) {
            if (item instanceof TorchBallItem torchBall) {
                tooltip.add(componentLiteral("§6 [" + torchBall.getItemName() + "]§r" +
                                             " when hit added torch!", yellow));
            }
        }
        // TOMAHAWK item
        if (stack.is(ModItems.TOMAHAWK.get().asItem())) {
            tooltip.add(componentTranslatable("tooltip.mccoursemod.tomahawk", yellow));
        }
        // DATA TABLET item
        if (stack.is(ModItems.DATA_TABLET.get().asItem())) {
            FoundBlock foundBlockData = stack.get(ModDataComponentTypes.FOUND_BLOCK.get());
            if (foundBlockData != null) {
                String biome = biomeName(foundBlockData.biome().location().getPath()); // Biome name
                String dimensionPath = foundBlockData.dimension().location().getPath(); // Dimension name
                tooltip.add(foundBlockData.blockPosition()); // Block position + X, Y, Z coordinates
                tooltip.add(biomeColor(biome, dimensionPath).copy().withColor(ARGB.color(254, 153, 0)));
                tooltip.add(dimension(dimensionPath).copy().withColor(ARGB.color(233, 210, 114)));
            }
        }
        // ELYTRA PLUS item
        if (stack.is(ModItems.DIAMOND_ELYTRA.get().asItem())) {
            if (item instanceof ElytraPlusItem elytraPlus) {
                tooltipLineLiteralRGB(tooltip, COLORS, stack, elytraPlus.elytraItemName());
            }
        }
        // COMPACTOR item
        if (stack.is(ModTags.Items.COMPACTOR_ITEMS)) {
            if (item instanceof CompactorItem compactor) {
                compactor.compactorItemName()
                         .forEach((message, color) ->
                                  tooltip.add(componentLiteral(message, color)));
            }
        }
        // CHISEL item
        if (stack.is(ModItems.CHISEL.get().asItem())) {
            if (item instanceof ChiselItem chisel) {
                boolean isShift = Minecraft.getInstance().hasShiftDown();
                tooltip.add(componentTranslatable(chisel.chiselShiftDescription(), isShift ? blue : red));
                tooltip.add(componentLiteral(chisel.chiselItemDescription(stack), gray));
            }
        }
        // LEVEL CHARGER GENERIC items
        if (stack.is(ModTags.Items.LEVEL_CHARGER_ITEMS)) {
            if (item instanceof LevelChargerItem generic) {
                tooltip.add(componentLiteral(generic.levelChargerItemDescription(),
                                             generic.getAmount() == 1 ? green : red));
            }
        }
        // MCCOURSE MOD BOTTLE item
        if (stack.is(ModItems.MCCOURSE_MOD_BOTTLE)) {
            if (item instanceof MccourseModBottleItem mccoursemodBottle) {
                mccoursemodBottle.mccourseBottleItemDescription(stack)
                                 .forEach((message, color) ->
                                          tooltip.add(componentLiteral(message, color)));
            }
        }
        // MCCOURSE MOD FISHING ROD item
        if (stack.is(ModItems.MCCOURSE_MOD_FISHING_ROD)) {
            tooltip.add(componentLiteral("More faster than vanilla Fishing Rod.", darkGray));
        }
        // MINER bow item
        if (stack.is(ModItems.MINER_BOW)) {
            if (item instanceof MinerBowItem minerBow) {
                int radius = minerBow.getRadius();
                int value = radius * 2 + 1;
                int depth = minerBow.getDepth();
                tooltipLineLiteralRGB(tooltip, COLORS, stack, " Blocks: " + value + " x " + value + " x " + depth);
            }
        }
        // LUCK items
        if (stack.is(ModTags.Items.LUCK_ITEMS)) {
            if (item instanceof LuckItem luck) {
                luck.luckDescription().forEach((key, value) ->
                                               tooltip.add(componentTranslatableIntColor(key, value)));
            }
        }
        // RESTORE item
        if (stack.is(ModItems.RESTORE)) {
            if (item instanceof RestoreItem restore) {
                List<String> lines = restore.restoreItemDescription();
                for (int i = 0; i < lines.size(); i++) {
                     tooltip.add(i + 1, standardLiteral(lines.get(i)));
                }
            }
        }
        // ** CUSTOM ENCHANTMENTS **
        if (level != null) {
            HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
            // Item checked is UNLOCK enchantment
            if (Utils.toolEnchant(ench, ModEnchantments.UNLOCK, stack) > 0) {
                if (stack.has(ModDataComponentTypes.UNLOCK)) {
                    Boolean values = stack.get(ModDataComponentTypes.UNLOCK);
                    if (values != null) {
                        boolean locked = values; // Locked Data Component change stage
                        tooltip.add(standardLiteral(locked ? "§c\uD83D\uDD12 Item locked! " +
                                                             "§7- Press §eV§7 §ato UNLOCK! " +
                                                             String.valueOf(locked).toUpperCase()
                                                           : "§a\uD83D\uDD13 Item unlocked! " +
                                                             "§7- Press §eV§7 §cto LOCK! " +
                                                             String.valueOf(locked).toUpperCase()));
                    }
                }
                else { stack.set(ModDataComponentTypes.UNLOCK, false); }
            }
        }
        cir.setReturnValue(tooltip); // New tooltip
    }

    // DEFAULT METHOD - Added custom tooltip
    @Inject(method="addDetailsToTooltip", at = @At("HEAD"))
    private void addDetailsToTooltip$mccoursemod(Item.TooltipContext context, TooltipDisplay display,
                                                 Player player, TooltipFlag flag,
                                                 Consumer<Component> consumer, CallbackInfo ci) {
       this.addToTooltip(ModDataComponentTypes.SHIFT_TOOLTIP.get(), context, display, consumer, flag);
       this.addToTooltip(ModDataComponentTypes.ITEM_TOOLTIP.get(), context, display, consumer, flag);
       this.addToTooltip(ModDataComponentTypes.HAMMER_TOOLTIP.get(), context, display, consumer, flag);
    }

    // DEFAULT METHOD - LAPIS LAZULI consumption is blocked
    @Inject(method = "shrink", at = @At("HEAD"), cancellable = true)
    private void preventLapisShrink(int decrement, CallbackInfo ci) {
        ItemStack self = (ItemStack) (Object) this;
        if (self.is(Items.LAPIS_LAZULI) && Utils.IGNORE_LAPIS) { ci.cancel(); } // Ignore original method
    }
}