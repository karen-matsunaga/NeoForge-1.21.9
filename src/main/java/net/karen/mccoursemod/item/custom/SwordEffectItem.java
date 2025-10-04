package net.karen.mccoursemod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import org.jetbrains.annotations.NotNull;
import static net.karen.mccoursemod.util.ChatUtils.componentTranslatableIntColor;

public class SwordEffectItem extends Item {
    private final int color;
    public SwordEffectItem(Item.Properties properties, ToolMaterial material,
                           float attackDamage, float attackSpeed, TagKey<Item> repair, int color) {
        super(properties.sword(material, attackDamage, attackSpeed).fireResistant().repairable(repair));
        this.color = color;
    }

    @Override
    public boolean onLeftClickEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull Entity entity) {
        if (entity instanceof LivingEntity livingEntity) { // Every time the player hit on entity to create slowing effect
            livingEntity.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 400), player);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public @NotNull Component getName(@NotNull ItemStack stack) {
        return componentTranslatableIntColor(this.getDescriptionId(), color);
    }
}