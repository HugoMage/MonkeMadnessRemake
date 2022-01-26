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
    public static final RegistryObject<EntityType<ProboscisMonkey>> PROBOSCIS = ENTITY_TYPES.register("proboscis_monkey", ()->
            EntityType.Builder.of(ProboscisMonkey::new, MobCategory.CREATURE)
                    .sized(1.0f,1.0f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "proboscis_monkey").toString()));
    public static final RegistryObject<EntityType<SlowLoris>> SLOW_LORIS = ENTITY_TYPES.register("slow_loris", ()->
            EntityType.Builder.of(SlowLoris::new, MobCategory.CREATURE)
                    .sized(0.8f,0.6f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "slow_loris").toString()));
    public static final RegistryObject<EntityType<Mandrill>> MANDRILL = ENTITY_TYPES.register("mandrill", ()->
            EntityType.Builder.of(Mandrill::new, MobCategory.CREATURE)
                    .sized(1.0f,1.3f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "mandrill").toString()));
    public static final RegistryObject<EntityType<Gigantopithecus>> GIGANTOPITHECUS = ENTITY_TYPES.register("gigantopithecus", ()->
            EntityType.Builder.of(Gigantopithecus::new, MobCategory.CREATURE)
                    .sized(2.9f,3.5f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "gigantopithecus").toString()));
    public static final RegistryObject<EntityType<JapaneseMacaque>> JAPANESEMACAQUE = ENTITY_TYPES.register("japanese_macaque", ()->
            EntityType.Builder.of(JapaneseMacaque::new, MobCategory.CREATURE)
                    .sized(1.0f,1.3f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "japanese_macaque").toString()));
    public static final RegistryObject<EntityType<Poacher>> POACHER = ENTITY_TYPES.register("poacher", ()->
            EntityType.Builder.of(Poacher::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.95F)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "poacher").toString()));
    public static final RegistryObject<EntityType<CrestedMacaque>> CRESTEDMACAQUE = ENTITY_TYPES.register("crested_macaque", ()->
            EntityType.Builder.of(CrestedMacaque::new, MobCategory.CREATURE)
                    .sized(1.0f,1.3f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "crested_macaque").toString()));
    public static final RegistryObject<EntityType<Bonobo>> BONOBO = ENTITY_TYPES.register("bonobo", ()->
            EntityType.Builder.of(Bonobo::new, MobCategory.CREATURE)
                    .sized(1.0f,1.1f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "bonobo").toString()));
    public static final RegistryObject<EntityType<Gelada>> GELADA = ENTITY_TYPES.register("gelada", ()->
            EntityType.Builder.of(Gelada::new, MobCategory.CREATURE)
                    .sized(1.2f,1.1f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "gelada").toString()));
    public static final RegistryObject<EntityType<Gibbon>> GIBBON = ENTITY_TYPES.register("gibbon", ()->
            EntityType.Builder.of(Gibbon::new, MobCategory.CREATURE)
                    .sized(0.5f,1.1f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "gibbon").toString()));

}
