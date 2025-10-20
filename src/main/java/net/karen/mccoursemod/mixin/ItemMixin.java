package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.util.ModClientTooltipComponent;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    private void imageTooltip(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        if (stack.is(Items.ENCHANTED_BOOK) && stack.has(DataComponents.STORED_ENCHANTMENTS) &&
            stack.has(DataComponents.ENCHANTMENT_GLINT_OVERRIDE)) {
            ModClientTooltipComponent modClientTooltipComponent =
               new ModClientTooltipComponent(ResourceLocation.withDefaultNamespace("textures/item/diamond_helmet.png"),
                                             16, 16, "Helmet");
            cir.setReturnValue(Optional.of(modClientTooltipComponent));
        }
    }
}