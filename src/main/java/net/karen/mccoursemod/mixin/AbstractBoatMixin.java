package net.karen.mccoursemod.mixin;

import net.karen.mccoursemod.entity.custom.ModBoatEntity;
import net.karen.mccoursemod.entity.custom.ModChestBoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Leashable;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import javax.annotation.Nullable;
import java.util.function.Supplier;

@Mixin(AbstractBoat.class)
public class AbstractBoatMixin extends VehicleEntity implements Leashable {
    @Shadow @Final private Supplier<Item> dropItem;
    @Shadow @Nullable private Leashable.LeashData leashData;

    public AbstractBoatMixin(EntityType<?> entityType, Level level) { super(entityType, level); }

    @Override
    protected void readAdditionalSaveData(@NotNull ValueInput valueInput) { this.readLeashData(valueInput); }

    @Override
    protected void addAdditionalSaveData(@NotNull ValueOutput valueOutput) { this.writeLeashData(valueOutput, this.leashData); }

    @Override
    protected @NotNull Item getDropItem() { return this.dropItem.get(); }

    @Override
    public @Nullable LeashData getLeashData() { return this.leashData; }

    @Override
    public void setLeashData(@Nullable Leashable.LeashData leashData) { this.leashData = leashData; }

    @Inject(method = "controlBoat", at = @At("HEAD"))
    public void controlBoatSpeed(CallbackInfo ci) {
        AbstractBoat abstractBoat = (AbstractBoat) (Object) this;
        Vec3 motion = abstractBoat.getDeltaMovement();
        System.out.print(motion);
        boolean boat = abstractBoat instanceof ModBoatEntity;
        boolean chestBoat = abstractBoat instanceof ModChestBoatEntity;
        if (boat || chestBoat) {
            abstractBoat.setDeltaMovement(motion.multiply(1, 1, 1));
        }
    }
}