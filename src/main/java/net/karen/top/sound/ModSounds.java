package net.karen.top.sound;

import net.karen.top.Top;
import net.karen.top.util.Utils;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
           DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Top.MOD_ID);

    // ** CUSTOM Advanced item sounds **
    // CHISEL item sound
    public static final Supplier<SoundEvent> CHISEL_USE = createSE("chisel_use");

    // METAL DETECTOR item sound
    public static final Supplier<SoundEvent> METAL_DETECTOR_FOUND_ORE = createSE("metal_detector_found_ore");

    // ** CUSTOM Advanced block sounds **
    /* MAGIC block sounds * Position 1: BREAK sound (when block is broken) * Position 2: STEP sound (when walking on block)
                          * Position 3: PLACE sound (when block is placed) * Position 4: HIT sound (when block is punched)
                          * Position 5: FALL sound (when falling onto block)                                                */
    public static final Supplier<SoundEvent> MAGIC_BLOCK_BREAK = createSE("magic_block_break");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_STEP = createSE("magic_block_step");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_PLACE = createSE("magic_block_place");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_HIT = createSE("magic_block_hit");
    public static final Supplier<SoundEvent> MAGIC_BLOCK_FALL = createSE("magic_block_fall");
    public static final DeferredSoundType MAGIC_BLOCK_SOUNDS =
           createST(ModSounds.MAGIC_BLOCK_BREAK, ModSounds.MAGIC_BLOCK_STEP, ModSounds.MAGIC_BLOCK_PLACE,
                    ModSounds.MAGIC_BLOCK_HIT, ModSounds.MAGIC_BLOCK_FALL);

    // ALEXANDRITE LAMP block sounds to registry in NeoForge events
    public static final Supplier<SoundEvent> ALEXANDRITE_LAMP_BREAK = createSE("alexandrite_lamp_break");
    public static final Supplier<SoundEvent> ALEXANDRITE_LAMP_STEP = createSE("alexandrite_lamp_step");
    public static final Supplier<SoundEvent> ALEXANDRITE_LAMP_PLACE = createSE("alexandrite_lamp_place");
    public static final Supplier<SoundEvent> ALEXANDRITE_LAMP_HIT = createSE("alexandrite_lamp_hit");
    public static final Supplier<SoundEvent> ALEXANDRITE_LAMP_FALL = createSE("alexandrite_lamp_fall");
    public static final DeferredSoundType ALEXANDRITE_LAMP_SOUNDS =
           createST(ModSounds.ALEXANDRITE_LAMP_BREAK, ModSounds.ALEXANDRITE_LAMP_STEP, ModSounds.ALEXANDRITE_LAMP_PLACE,
                    ModSounds.ALEXANDRITE_LAMP_HIT, ModSounds.ALEXANDRITE_LAMP_FALL);

    // ** CUSTOM Jukebox songs **
    // BAR BRAWL music disc item sounds
    public static final ResourceKey<JukeboxSong> BAR_BRAWL_KEY = createSong("bar_brawl");
    public static final DeferredHolder<SoundEvent, SoundEvent> MUSIC_DISC_BAR_BRAWL = createSE("bar_brawl");

    // CUSTOM METHOD - Registry all songs on JUKEBOX block
    private static ResourceKey<JukeboxSong> createSong(String name) {
        return Utils.rKey(Registries.JUKEBOX_SONG, Utils.topPath(name));
    }

    // CUSTOM METHOD - Registry all Supplier or Deferred Holder SOUND EVENT TYPE -> ITEM, BLOCK, MUSIC DISC, BIOME etc.
    private static DeferredHolder<SoundEvent, SoundEvent> createSE(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(Utils.topPath(name)));
    }

    // CUSTOM METHOD - Registry all SOUND TYPE (BREAK sound, STEP sound, PLACE sound, HIT sound and FALL sound)
    private static DeferredSoundType createST(Supplier<SoundEvent> breakSound,
                                              Supplier<SoundEvent> step, Supplier<SoundEvent> place,
                                              Supplier<SoundEvent> hit, Supplier<SoundEvent> fall) {
        return new DeferredSoundType(1F, 1F, breakSound, step, place, hit, fall);
    }

    // CUSTOM METHOD - Registry all custom sounds on event bus
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }

    // CUSTOM METHOD - Register all parameters of Data Generation JSON file
    private static void registerGen(BootstrapContext<JukeboxSong> context,
                                    ResourceKey<JukeboxSong> key, DeferredHolder<SoundEvent, SoundEvent> soundEvent,
                                    int lengthInSeconds, int comparatorOutput) {
        context.register(key, new JukeboxSong(soundEvent,
                                              Component.translatable(Util.makeDescriptionId("item",
                                                                     key.location().withSuffix("_music_disc.desc"))),
                                              (float) lengthInSeconds, comparatorOutput));
    }

    // CUSTOM METHOD - Data generation of all custom sound events on JSON file
    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        registerGen(context, ModSounds.BAR_BRAWL_KEY, ModSounds.MUSIC_DISC_BAR_BRAWL, 162, 15);
    }
}