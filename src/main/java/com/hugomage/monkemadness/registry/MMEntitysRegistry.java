package com.hugomage.monkemadness.registry;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMEntitysRegistry {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, MonkeMadness.MOD_ID);

    //Apes
    public static final RegistryObject<EntityType<Orangutan>> ORANGUTAN = ENTITY_TYPES.register("orangutan", ()->
            EntityType.Builder.of(Orangutan::new, MobCategory.CREATURE)
                    .sized(1.5f,2.0f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "orangutan").toString()));
    public static final RegistryObject<EntityType<SnubNosedMonkey>> SNUBNOSEDMONKEY = ENTITY_TYPES.register("snub_nosed_monkey", ()->
            EntityType.Builder.of(SnubNosedMonkey::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.5f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "snub_nosed_monkey").toString()));
    public static final RegistryObject<EntityType<Tarsier>> TARSIER = ENTITY_TYPES.register("tarsier", ()->
            EntityType.Builder.of(Tarsier::new, MobCategory.CREATURE)
                    .sized(0.8f,0.6f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "tarsier").toString()));
    public static final RegistryObject<EntityType<ZombieApe>> ZOMBIEAPE = ENTITY_TYPES.register("zombie_ape", ()->
            EntityType.Builder.of(ZombieApe::new, MobCategory.MONSTER)
                    .sized(1.0f,1.3f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "zombie_ape").toString()));
    public static final RegistryObject<EntityType<BaleMonkey>> BALE = ENTITY_TYPES.register("bale_monkey", ()->
            EntityType.Builder.of(BaleMonkey::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.0f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "bale_monkey").toString()));
    public static final RegistryObject<EntityType<Poop>> POOP = ENTITY_TYPES.register("poop",
            () -> EntityType.Builder.<Poop>of(Poop::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "poop").toString()));

}
