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
}
