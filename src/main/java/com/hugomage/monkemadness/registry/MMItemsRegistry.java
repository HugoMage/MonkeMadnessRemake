package com.hugomage.monkemadness.registry;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.items.tools.PanFlute;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMItemsRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MonkeMadness.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MonkeMadness.MOD_ID);
    //Items
    public static final RegistryObject<Item> MUSIC_DISC_BAMBOO = ITEMS.register("music_disc_bamboo", () -> new RecordItem(15, MMSoundsRegistry.DISC_BAMBOO, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS).stacksTo(1).rarity(Rarity.RARE)));

    //Primate Brain
    public static final RegistryObject<Item> PRIMATE_BRAIN = ITEMS.register("primate_brain", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.POISON, 100, 3), 1.0F).build())));
    public static final RegistryObject<Item> PREPARED_PRIMATE_BRAIN = ITEMS.register("prepared_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.POISON, 100, 3), 1.0F).build())));
    public static final RegistryObject<Item> STEAMING_PRIMATE_BRAIN = ITEMS.register("steamed_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.9F).effect(new MobEffectInstance(MobEffects.POISON, 100, 2), 1.0F).build())));
    public static final RegistryObject<Item> BOILED_PRIMATE_BRAIN = ITEMS.register("boiled_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1.0F).effect(new MobEffectInstance(MobEffects.POISON, 100, 1), 1.0F).build())));
    public static final RegistryObject<Item> COOKED_PRIMATE_BRAIN = ITEMS.register("cooked_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 3), 1.0F).build())));
    public static final RegistryObject<Item> SPOILED_PRIMATE_BRAIN = ITEMS.register("spoiled_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.POISON, 100, 5), 1.0F).build())));
    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> ORANGUTAN_SPAWN_EGG = ITEMS.register("orangutan_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.ORANGUTAN, 0xa06146, 0x776350, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> BONOBO_SPAWN_EGG = ITEMS.register("bonobo_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.BONOBO, 0x1a1b1e, 0x4d4e55, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    //Holdables
    public static final RegistryObject<Item> MONKEY_MOTIVATOR = ITEMS.register("pan_flute", PanFlute::new);


}
