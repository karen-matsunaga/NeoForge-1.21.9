package net.karen.top.entity.custom;

import net.karen.top.enchantment.ModEnchantments;
import net.karen.top.item.custom.MinerBowItem;
import net.karen.top.util.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import static net.karen.top.event.ModEvents.*;

public class MinerBowArrowEntity extends AbstractArrow {
    public MinerBowArrowEntity(EntityType<? extends AbstractArrow> type,
                               Level level) {
        super(type, level);
    }

    public MinerBowArrowEntity(LivingEntity owner, Level level) {
        super(EntityType.ARROW, owner, level, new ItemStack(Items.ARROW), null);
    }

    @Override
    public void tick() { super.tick(); }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult result) {
        super.onHitBlock(result);
        BlockPos center = result.getBlockPos();
        Level level = this.level();
        Entity entity = this.getOwner();
        if (entity instanceof Player player && level instanceof ServerLevel serverLevel) {
            ItemStack stack = player.getWeaponItem();
            Item item = stack.getItem();
            if (item instanceof MinerBowItem minerBow) {
                int radius = minerBow.getRadius();
                int depth = minerBow.getDepth();
                for (int dx = -1; dx <= radius; dx++) {
                    for (int dy = -1; dy <= radius; dy++) {
                        for (int dz = -1; dz <= depth; dz++) {
                            BlockPos pos = center.offset(dx, dy, dz);
                            BlockState state = serverLevel.getBlockState(pos);
                            if (state.getDestroySpeed(serverLevel, pos) >= 0 && !state.isAir()) {
                                HolderLookup.RegistryLookup<Enchantment> ench =
                                      serverLevel.registryAccess().lookupOrThrow(Registries.ENCHANTMENT);
                                int rainbow = Utils.toolEnchant(ench, ModEnchantments.RAINBOW, stack);
                                int autoSmelt = Utils.toolEnchant(ench, ModEnchantments.AUTO_SMELT, stack);
                                int moreOres = Utils.toolEnchant(ench, ModEnchantments.MORE_ORES, stack);
                                if (rainbow > 0 || autoSmelt > 0 || moreOres > 0) {
                                    BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(level, pos, state, player);
                                    onAutoSmeltMagnetMoreOresMultiplierRainbowActivated(event);
                                    serverLevel.destroyBlock(pos, true);
                                }
                                else {
                                    BlockEntity blockEntity = serverLevel.getBlockEntity(pos);
                                    // Detected FORTUNE and SILK TOUCH enchantments
                                    LootParams.Builder builder =
                                        new LootParams.Builder(serverLevel)
                                                      .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                                                      .withParameter(LootContextParams.TOOL, stack)
                                                      .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity);
                                    List<ItemStack> drops = state.getDrops(builder);
                                    drops.forEach(drop ->
                                                  Containers.dropItemStack(serverLevel, pos.getX(), pos.getY(), pos.getZ(), drop));
                                    state.onBlockStateChange(serverLevel, pos, state);
                                    serverLevel.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                                }
                            }
                        }
                    }
                }
            }
        }
        this.discard(); // Remove the arrow after impact
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() { return new ItemStack(Items.ARROW); }
}