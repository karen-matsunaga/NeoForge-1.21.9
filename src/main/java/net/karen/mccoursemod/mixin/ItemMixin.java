package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.util.ChatUtils;
import net.karen.mccoursemod.util.MultiImageTooltipComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.List;
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "getTooltipImage", at = @At("HEAD"), cancellable = true)
    private void imageTooltip(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        if (stack.is(Items.ENCHANTED_BOOK) && stack.has(DataComponents.STORED_ENCHANTMENTS) &&
            stack.has(DataComponents.ENCHANTMENT_GLINT_OVERRIDE)) {
            Minecraft mc = Minecraft.getInstance();
            Level level = mc.level;
            if (level != null) {
                HolderLookup.RegistryLookup<Enchantment> ench = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                List<Holder.Reference<Enchantment>> enchantments = ench.listElements().toList();
                List<Holder.Reference<Enchantment>> a = enchantments.stream().toList();
                for (Holder.Reference<Enchantment> referenceEnchantment : a) {
                     Holder<Enchantment> enchantmentHolder = referenceEnchantment.getDelegate();
                     MultiImageTooltipComponent icon = ChatUtils.enchantmentIcon(enchantmentHolder);
                     cir.setReturnValue(Optional.of(icon));
                }
            }
        }
    }
}