package com.hugomage.monkemadness.registry;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.entities.Bonobo;
import com.hugomage.monkemadness.entities.Orangutan;
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
    public static final RegistryObject<EntityType<Bonobo>> BONOBO = ENTITY_TYPES.register("bonobo", ()->
            EntityType.Builder.of(Bonobo::new, MobCategory.CREATURE)
                    .sized(1.0f,1.1f)
                    .build(new ResourceLocation(MonkeMadness.MOD_ID, "bonobo").toString()));
}
