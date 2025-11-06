package net.karen.top.mixin;

import net.karen.top.entity.custom.ModBoatEntity;
import net.karen.top.entity.custom.ModChestBoatEntity;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method="maxUpStep", at = @At("HEAD"), cancellable = true)
    private void maxUpStepBoat(CallbackInfoReturnable<Float> cir) {
        Entity entity = (Entity)(Object) this;
        boolean boat = entity instanceof ModBoatEntity modBoatEntity &&
                       modBoatEntity.horizontalCollision;
        boolean chestBoat = entity instanceof ModChestBoatEntity modChestBoatEntity &&
                            modChestBoatEntity.horizontalCollision;
        if (boat || chestBoat) { cir.setReturnValue(10.0F); } // Increase Mod Boat max up step
    }
}