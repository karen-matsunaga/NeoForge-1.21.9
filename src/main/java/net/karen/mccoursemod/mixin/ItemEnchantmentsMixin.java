package net.karen.mccoursemod.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.function.Consumer;

@Mixin(ItemEnchantments.class)
public abstract class ItemEnchantmentsMixin {
    @Shadow @Final Object2IntOpenHashMap<Holder<Enchantment>> enchantments;
    @Shadow private static <T> HolderSet<T> getTagOrEmpty(@Nullable HolderLookup.Provider registries,
                                                          ResourceKey<Registry<T>>
                                                          registryKey, TagKey<T> key) { return null; }

    @Inject(method = "addToTooltip", at = @At("HEAD"), cancellable = true)
    private void enchantmentsAddToTooltip(Item.TooltipContext context, Consumer<Component> consumer,
                                          TooltipFlag flag, DataComponentGetter getter, CallbackInfo ci) {
        // Cancels original enchantment tooltip
        ci.cancel();
        // Returns new enchantment tooltip
        HolderLookup.Provider holderLookupProvider = context.registries();
        HolderSet<Enchantment> holderset =
              getTagOrEmpty(holderLookupProvider, Registries.ENCHANTMENT, EnchantmentTags.TOOLTIP_ORDER);
        if (holderset != null) {
            Object2IntOpenHashMap<Holder<Enchantment>> enchantments = this.enchantments;
            for (Holder<Enchantment> holder : holderset) {
                int i = enchantments.getInt(holder);
                if (i > 0) {
                    consumer.accept(Enchantment.getFullname(holder, i));
                    consumer.accept(Component.literal("Teste1"));
                }
            }
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : enchantments.object2IntEntrySet()) {
                Holder<Enchantment> holder1 = entry.getKey();
                if (!holderset.contains(holder1)) {
                    consumer.accept(Enchantment.getFullname(entry.getKey(), entry.getIntValue()));
                    consumer.accept(Component.literal("Teste1"));
                }
            }
        }
    }
}