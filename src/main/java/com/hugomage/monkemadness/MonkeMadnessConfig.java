package com.hugomage.monkemadness;

import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = MonkeMadness.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MonkeMadnessConfig {
    public static int OrangutanSpawnWeight;

    public static class Common {
        public static final Common INSTANCE;
        public static final ForgeConfigSpec SPEC;

        static {
            Pair<Common, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Common::new);
            INSTANCE = pair.getLeft();
            SPEC = pair.getRight();
        }

        public final ForgeConfigSpec.IntValue OrangutanSpawnWeight;

        Common(ForgeConfigSpec.Builder builder) {

            builder.push("Jungle Primates Spawn Weight");
            OrangutanSpawnWeight = builder.comment("Spawn weight of Orangutans").defineInRange("orangutan_spawn_weight", 40, 0, 1000);
            builder.pop();

            builder.push("Savannah Primates Spawn Weight");
            builder.pop();

            builder.push("Taiga Primates Spawn Weight");
            builder.pop();

            builder.push("Misc Primates and Others Spawn Weight");
            builder.pop();

        }

        public static void reload() {
            MonkeMadnessConfig.OrangutanSpawnWeight = INSTANCE.OrangutanSpawnWeight.get();


        }
    }
}
