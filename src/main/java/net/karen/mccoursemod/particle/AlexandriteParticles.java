package net.karen.mccoursemod.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AlexandriteParticles extends SingleQuadParticle {
    protected AlexandriteParticles(ClientLevel level, double x, double y, double z,
                                   double xSpeed, double ySpeed, double zSpeed, SpriteSet spriteSet) {
        super(level, x, y, z, xSpeed, ySpeed, zSpeed, spriteSet.first());

        // Position of particle
        this.friction = 0.8f;
        this.xd = xSpeed;
        this.yd = ySpeed;
        this.zd = zSpeed;

        // Size of particle
        this.quadSize *= 0.75f; // 75% of size
        this.lifetime = 20; // 20 ticks
        this.setSpriteFromAge(spriteSet);

        // RGB colors of particle
        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    // DEFAULT METHOD - Render layer
    @Override
    protected @NotNull Layer getLayer() {
        return Layer.TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        // DEFAULT METHOD - Create constructor to set spriteSet
        public Provider(SpriteSet spriteSet) { this.spriteSet = spriteSet; }

        // DEFAULT METHOD - Create custom particles
        @Override
        public @Nullable Particle createParticle(@NotNull SimpleParticleType type, @NotNull ClientLevel level,
                                                 double x, double y, double z,
                                                 double xSpeed, double ySpeed, double zSpeed,
                                                 @NotNull RandomSource source) {
            return new AlexandriteParticles(level, x, y, z, xSpeed, ySpeed, zSpeed, this.spriteSet);
        }
    }
}