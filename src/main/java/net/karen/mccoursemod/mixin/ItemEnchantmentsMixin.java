package net.karen.mccoursemod.mixin;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.karen.mccoursemod.util.ChatUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;
import java.util.function.Consumer;
import static net.karen.mccoursemod.util.ChatUtils.*;

@Mixin(ItemEnchantments.class)
public abstract class ItemEnchantmentsMixin {
    @Shadow @Final Object2IntOpenHashMap<Holder<Enchantment>> enchantments;
    @Shadow private static <T> HolderSet<T>
                           getTagOrEmpty(@Nullable HolderLookup.Provider registries,
                                         ResourceKey<Registry<T>> registryKey,
                                         TagKey<T> key) { return null; }

    @Inject(method = "addToTooltip", at = @At("HEAD"), cancellable = true)
    private void enchantmentsAddToTooltip(Item.TooltipContext context, Consumer<Component> consumer,
                                          TooltipFlag flag, DataComponentGetter getter, CallbackInfo ci) {
        ci.cancel(); // CANCELS original enchantment tooltip
        // RETURNS new enchantment tooltip
        HolderLookup.Provider holderLookupProvider = context.registries();
        HolderSet<Enchantment> holderset =
              getTagOrEmpty(holderLookupProvider, Registries.ENCHANTMENT, EnchantmentTags.TOOLTIP_ORDER);
        if (holderset != null) {
            Object2IntOpenHashMap<Holder<Enchantment>> enchantments = this.enchantments;
            Level mcLevel = context.level();
            if (mcLevel != null) {
                Registry<Enchantment> enchantmentRegistry =
                        mcLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                for (Holder<Enchantment> holder : holderset) {
                    Enchantment enchName = holder.value();
                    ResourceLocation enchantKey = enchantmentRegistry.getKey(enchName);
                    if (enchantKey != null) {
                        String descriptionKey = "enchantment." + enchantKey.getNamespace() + "." + enchantKey.getPath() + ".desc";
                        ChatFormatting color = getEnchantmentColor(holder); // Enchantment COLOR by category
                        boolean isCurse = holder.is(EnchantmentTags.CURSE);
                        ChatFormatting colors = isCurse ? red : color;
                        if (I18n.exists(descriptionKey)) {
                            int i = enchantments.getInt(holder);
                            if (i > 0) {
                                consumer.accept(Enchantment.getFullname(holder, i));
                                consumer.accept(description(I18n.get(descriptionKey), colors, List.of(false, isCurse)));
                                consumer.accept(ChatUtils.atlas().append(" Helmet"));
                            }
                        }
                    }
                }
            }
        }
    }
}