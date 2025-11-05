package net.karen.top.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;

public class BismuthParticles extends SingleQuadParticle {
    protected BismuthParticles(ClientLevel level, double x, double y, double z,
                               double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet.first());
        this.friction = 0.8F;
        this.lifetime = 80;
        this.setSpriteFromAge(spriteSet);
        this.rCol = 1F;
        this.gCol = 1F;
        this.bCol = 1F;
    }

    @Override
    protected @NotNull Layer getLayer() { return Layer.TRANSLUCENT; }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) { this.spriteSet = spriteSet; }

        @Override
        public @Nullable Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level,
                                                 double x, double y, double z, double xSpeed, double ySpeed, double zSpeed,
                                                 @NotNull RandomSource source) {
            return new BismuthParticles(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}