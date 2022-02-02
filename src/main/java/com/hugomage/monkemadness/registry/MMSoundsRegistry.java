package com.hugomage.monkemadness.registry;

import com.hugomage.monkemadness.MonkeMadness;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMSoundsRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MonkeMadness.MOD_ID);

    public static final RegistryObject<SoundEvent> ORANGUTAN_AMBIENT = SOUND_EVENTS.register("entity.orangutan.ambient", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.orangutan.ambient"))
    );
    public static final RegistryObject<SoundEvent> ORANGUTAN_HURT = SOUND_EVENTS.register("entity.orangutan.hurt", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.orangutan.hurt"))
    );
    public static final RegistryObject<SoundEvent> ORANGUTAN_DEATH = SOUND_EVENTS.register("entity.orangutan.death", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.orangutan.death"))
    );
    public static final RegistryObject<SoundEvent> UAKARI_AMBIENT = SOUND_EVENTS.register("entity.uakari.ambient", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.uakari.ambient"))
    );
    public static final RegistryObject<SoundEvent> UAKARI_HURT = SOUND_EVENTS.register("entity.uakari.hurt", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.uakari.hurt"))
    );
    public static final RegistryObject<SoundEvent> UAKARI_DEATH = SOUND_EVENTS.register("entity.uakari.death", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.uakari.death"))
    );
    public static final RegistryObject<SoundEvent> DISC_BAMBOO = SOUND_EVENTS.register("music.disc.bamboo", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "music.disc.bamboo"))
    );
    public static final RegistryObject<SoundEvent> PAN_FLUTE_WHISTLE = SOUND_EVENTS.register("misc.pan_flute.whistle", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "misc.pan_flute.whistle"))
    );
    public static final RegistryObject<SoundEvent> ZOMBIEAPE_AMBIENT = SOUND_EVENTS.register("entity.zombie_ape.ambient", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.zombie_ape.ambient"))
    );
    public static final RegistryObject<SoundEvent> ZOMBIEAPE_HURT = SOUND_EVENTS.register("entity.zombie_ape.hurt", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.zombie_ape.hurt"))
    );
    public static final RegistryObject<SoundEvent> ZOMBIEAPE_DEATH = SOUND_EVENTS.register("entity.zombie_ape.death", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.zombie_ape.death"))
    );
    public static final RegistryObject<SoundEvent> PROBOSCIS_AMBIENT = SOUND_EVENTS.register("entity.proboscis_monkey.ambient", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.proboscis_monkey.ambient"))
    );
    public static final RegistryObject<SoundEvent> PROBOSCIS_HURT = SOUND_EVENTS.register("entity.proboscis_monkey.hurt", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.proboscis_monkey.hurt"))
    );
    public static final RegistryObject<SoundEvent> PROBOSCIS_DEATH = SOUND_EVENTS.register("entity.proboscis_monkey.death", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.proboscis_monkey.death"))
    );
    public static final RegistryObject<SoundEvent> MANDRILL_AMBIENT = SOUND_EVENTS.register("entity.mandrill.ambient", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.mandrill.ambient"))
    );
    public static final RegistryObject<SoundEvent> RHESUS_AMBIENT = SOUND_EVENTS.register("entity.rhesus.ambient", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.rhesus.ambient"))
    );
    public static final RegistryObject<SoundEvent> RHESUS_HURT = SOUND_EVENTS.register("entity.rhesus.hurt", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.rhesus.hurt"))
    );
    public static final RegistryObject<SoundEvent> RHESUS_DEATH = SOUND_EVENTS.register("entity.rhesus.death", () ->
            new SoundEvent(new ResourceLocation(MonkeMadness.MOD_ID, "entity.rhesus.death"))
    );
}
