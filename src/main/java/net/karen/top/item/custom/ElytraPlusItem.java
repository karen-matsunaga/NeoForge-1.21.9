package net.karen.top.item.custom;

import net.karen.top.util.ModTags;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import static net.karen.top.util.ChatUtils.*;

public class ElytraPlusItem extends Item {
    private final Holder<MobEffect> effectHolder; // EFFECT
    private final int effectAmplifier; // EFFECT LEVEL

    public ElytraPlusItem(Holder<MobEffect> effectHolder,
                          int effectAmplifier, Item.Properties properties) {
        super(properties);
        this.effectHolder = effectHolder;
        this.effectAmplifier = effectAmplifier;
    }

    // DEFAULT METHOD - Enchantment support (COMPATIBILITY)
    @Override
    public boolean supportsEnchantment(ItemStack stack, @NotNull Holder<Enchantment> enchantment) {
        return stack.is(Items.ENCHANTED_BOOK) || enchantment.is(ModTags.Enchantments.DURABILITY_ENCHANTMENTS) ||
               enchantment.is(EnchantmentTags.ARMOR_EXCLUSIVE);
    }

    // DEFAULT METHOD - Piglins neutral
    @Override
    public boolean makesPiglinsNeutral(ItemStack stack, @NotNull LivingEntity wearer) { return stack.is(this); }

    // DEFAULT METHOD - Inventory tick
    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull ServerLevel level,
                              @NotNull Entity entity, @Nullable EquipmentSlot slot) {
        if (entity instanceof Player player && !level.isClientSide()) {
            MobEffectInstance effect = new MobEffectInstance(effectHolder, 200, effectAmplifier,
                                                             true, true, true);
            boolean isElytra = player.getInventory().player.getItemBySlot(EquipmentSlot.CHEST).is(this);
            if (isElytra) { player.addEffect(effect); } // EFFECT Activated
            if (!isElytra) { player.removeEffect(effect.getEffect()); } // EFFECT Disabled
        }
        super.inventoryTick(stack, level, entity, slot);
    }

    // DEFAULT METHOD - Elytra Plus item name
    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) { return rgbItemName(stack); }

    // CUSTOM METHOD - Elytra Plus item description
    public List<String> elytraItemName() {
        String effectHolder = this.effectHolder.getRegisteredName().replace("minecraft:", "");
        String upperLetter = effectHolder.substring(0, 1).toUpperCase();
        String lowerLetters = effectHolder.substring(1);
        String effectName = upperLetter + lowerLetters;
        int effectLevel = effectAmplifier + 1;
        return List.of(" with more durability and receive an effect!", "Effect: " + effectName + " " + effectLevel);
    }
}