package net.karen.mccoursemod.datagen;

import net.karen.mccoursemod.MccourseMod;
import net.karen.mccoursemod.sound.ModSounds;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;
import org.jetbrains.annotations.NotNull;

public class ModSoundDefinitionsProvider extends SoundDefinitionsProvider {
    public ModSoundDefinitionsProvider(PackOutput output) {
        super(output, MccourseMod.MOD_ID);
    }

    @Override
    public void registerSounds() {
        // CHISEL USE
        add(ModSounds.CHISEL_USE.get(), SoundDefinition.definition()
                                                       .subtitle("sounds.mccoursemod.chisel_use")
                                                       .with(sound("mccoursemod:chisel_use")));

        // METAL DETECTOR FOUND ORE
        add(ModSounds.METAL_DETECTOR_FOUND_ORE.get(), SoundDefinition.definition()
                                                                     .subtitle("sounds.mccoursemod.metal_detector_found_ore")
                                                                     .with(sound("mccoursemod:metal_detector_found_ore")));

        // MAGIC BLOCK BREAK
        add(ModSounds.MAGIC_BLOCK_BREAK.get(), SoundDefinition.definition()
                                                              .subtitle("sounds.mccoursemod.magic_block_break")
                                                              .with(sound("mccoursemod:magic_block_break")));

        // MAGIC BLOCK STEP
        add(ModSounds.MAGIC_BLOCK_STEP.get(), SoundDefinition.definition()
                                                              .subtitle("sounds.mccoursemod.magic_block_step")
                                                              .with(sound("mccoursemod:magic_block_step")));

        // MAGIC BLOCK PLACE
        add(ModSounds.MAGIC_BLOCK_PLACE.get(), SoundDefinition.definition()
                                                              .subtitle("sounds.mccoursemod.magic_block_place")
                                                              .with(sound("mccoursemod:magic_block_place")));

        // MAGIC BLOCK HIT
        add(ModSounds.MAGIC_BLOCK_HIT.get(), SoundDefinition.definition()
                                                              .subtitle("sounds.mccoursemod.magic_block_hit")
                                                              .with(sound("mccoursemod:magic_block_hit")));

        // MAGIC BLOCK FALL
        add(ModSounds.MAGIC_BLOCK_FALL.get(), SoundDefinition.definition()
                                                              .subtitle("sounds.mccoursemod.magic_block_fall")
                                                              .with(sound("mccoursemod:magic_block_fall")));

        // ALEXANDRITE LAMP BREAK
        add(ModSounds.ALEXANDRITE_LAMP_BREAK.get(), SoundDefinition.definition()
                                                                   .subtitle("sounds.mccoursemod.alexandrite_lamp_break")
                                                                   .with(sound("mccoursemod:alexandrite_lamp_break")));

        // ALEXANDRITE LAMP STEP
        add(ModSounds.ALEXANDRITE_LAMP_STEP.get(), SoundDefinition.definition()
                                                                  .subtitle("sounds.mccoursemod.alexandrite_lamp_step")
                                                                  .with(sound("mccoursemod:alexandrite_lamp_step")));

        // ALEXANDRITE LAMP PLACE
        add(ModSounds.ALEXANDRITE_LAMP_PLACE.get(), SoundDefinition.definition()
                                                                   .subtitle("sounds.mccoursemod.alexandrite_lamp_place")
                                                                   .with(sound("mccoursemod:alexandrite_lamp_place")));

        // ALEXANDRITE LAMP HIT
        add(ModSounds.ALEXANDRITE_LAMP_HIT.get(), SoundDefinition.definition()
                                                                 .subtitle("sounds.mccoursemod.alexandrite_lamp_hit")
                                                                 .with(sound("mccoursemod:alexandrite_lamp_hit")));

        // ALEXANDRITE LAMP FALL
        add(ModSounds.ALEXANDRITE_LAMP_FALL.get(), SoundDefinition.definition()
                                                                  .subtitle("sounds.mccoursemod.alexandrite_lamp_fall")
                                                                  .with(sound("mccoursemod:alexandrite_lamp_fall")));

        // MUSIC DISC BAR BRAWL
        add(ModSounds.MUSIC_DISC_BAR_BRAWL, SoundDefinition.definition()
                                                           .with(sound("mccoursemod:bar_brawl",
                                                                       SoundDefinition.SoundType.SOUND).stream(true)));
    }

    @Override
    public @NotNull String getName() { return "Mccourse Mod Sound Tags"; }
}