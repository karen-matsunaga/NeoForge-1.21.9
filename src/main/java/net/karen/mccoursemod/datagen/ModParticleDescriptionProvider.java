package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.particle.ModParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;
import org.jetbrains.annotations.NotNull;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    protected ModParticleDescriptionProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        // Bismuth particles
        spriteSet(ModParticles.BISMUTH_PARTICLES.get(),
                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "bismuth"));

        // Alexandrite particles
        spriteSet(ModParticles.ALEXANDRITE_PARTICLES.get(),
                  ResourceLocation.fromNamespaceAndPath(MccourseMod.MOD_ID, "alexandrite"));
    }

    @Override
    public @NotNull String getName() { return "Mccourse Mod Particle Descriptions"; }
}