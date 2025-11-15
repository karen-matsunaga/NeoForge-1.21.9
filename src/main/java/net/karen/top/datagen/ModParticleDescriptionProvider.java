package net.karen.top.datagen;

import net.karen.top.Top;
import net.karen.top.particle.ModParticles;
import net.karen.top.util.Utils;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;
import org.jetbrains.annotations.NotNull;
import static net.karen.top.util.ChatUtils.*;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    protected ModParticleDescriptionProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        // Bismuth particles
        spriteSet(ModParticles.BISMUTH_PARTICLES.get(), Utils.topPath("bismuth"));

        // Alexandrite particles
        spriteSet(ModParticles.ALEXANDRITE_PARTICLES.get(), Utils.topPath("alexandrite"));
    }

    @Override
    public @NotNull String getName() { return splitWord(itemLines(Top.MOD_ID)) + " Particle Descriptions"; }
}