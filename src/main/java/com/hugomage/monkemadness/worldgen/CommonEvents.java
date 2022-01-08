package com.hugomage.monkemadness.worldgen;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.MonkeMadnessConfig;
import com.hugomage.monkemadness.registry.MMEntitysRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MonkeMadness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)

public class CommonEvents {
    @SubscribeEvent(priority = EventPriority.NORMAL)

    public static void registerBiomes(BiomeLoadingEvent event) {
        if (event.getCategory() == Biome.BiomeCategory.SAVANNA) {

        }
        if (event.getCategory() == Biome.BiomeCategory.ICY) {

        }
        if (event.getCategory() == Biome.BiomeCategory.JUNGLE) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(MMEntitysRegistry.ORANGUTAN.get(),  MonkeMadnessConfig.Common.INSTANCE.OrangutanSpawnWeight.get(), 1, 3));

        }

        if (event.getName().equals(new ResourceLocation("minecraft:lush_caves"))) {

        }
        if (event.getName().equals(new ResourceLocation("atmospheric:rainforest"))) {

        }


    }
}
