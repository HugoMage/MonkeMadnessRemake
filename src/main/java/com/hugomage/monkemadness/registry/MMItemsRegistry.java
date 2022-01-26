package com.hugomage.monkemadness.registry;

import com.hugomage.monkemadness.MonkeMadness;
import com.hugomage.monkemadness.items.BaleMaterial;
import com.hugomage.monkemadness.items.Carver;
import com.hugomage.monkemadness.items.MonkeyMotivator;
import com.hugomage.monkemadness.items.PoopItem;
import com.hugomage.monkemadness.items.tools.Motivator;
import com.hugomage.monkemadness.items.tools.PanFlute;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
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
    public static final RegistryObject<Item> GOlDEN_BANANA = ITEMS.register("golden_banana", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 1), 1.0F).effect(new MobEffectInstance(MobEffects.JUMP, 100, 1), 1.0F).build())));
    public static final RegistryObject<Item> BALE_MUSTACHE = ITEMS.register("bale_monkey_mustache", () -> new ArmorItem(new BaleMaterial(), EquipmentSlot.HEAD, (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT).stacksTo(1)));
    public static final RegistryObject<Item> POOP = ITEMS.register("poop", () -> new PoopItem((new Item.Properties()).stacksTo(16).tab(CreativeModeTab.TAB_MISC)));

    //Primate Brain
    public static final RegistryObject<Item> PRIMATE_BRAIN = ITEMS.register("primate_brain", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_BREWING).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.POISON, 100, 3), 1.0F).build())));
    public static final RegistryObject<Item> PREPARED_PRIMATE_BRAIN = ITEMS.register("prepared_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.8F).effect(new MobEffectInstance(MobEffects.POISON, 100, 3), 1.0F).build())));
    public static final RegistryObject<Item> STEAMING_PRIMATE_BRAIN = ITEMS.register("steamed_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.9F).effect(new MobEffectInstance(MobEffects.POISON, 100, 2), 1.0F).build())));
    public static final RegistryObject<Item> BOILED_PRIMATE_BRAIN = ITEMS.register("boiled_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(1.0F).effect(new MobEffectInstance(MobEffects.POISON, 100, 1), 1.0F).build())));
    public static final RegistryObject<Item> COOKED_PRIMATE_BRAIN = ITEMS.register("cooked_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(5).saturationMod(1.2F).effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 3), 1.0F).build())));
    public static final RegistryObject<Item> SPOILED_PRIMATE_BRAIN = ITEMS.register("spoiled_primate_brain", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(0.1F).effect(new MobEffectInstance(MobEffects.POISON, 100, 5), 1.0F).build())));
    //Spawn Eggs
    public static final RegistryObject<ForgeSpawnEggItem> ORANGUTAN_SPAWN_EGG = ITEMS.register("orangutan_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.ORANGUTAN, 0xa06146, 0x776350, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> SNUBNOSED_SPAWN_EGG = ITEMS.register("snub_nosed_monkey_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.SNUBNOSEDMONKEY, 0x594a4e, 0xbad4f2, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> TARSIER_SPAWN_EGG = ITEMS.register("tarsier_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.TARSIER, 0x916436, 0xa57646, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> ZOMBIEAPE_SPAWN_EGG = ITEMS.register("zombie_ape_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.ZOMBIEAPE, 0x152419, 0x3c784c, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> BALE_SPAWN_EGG = ITEMS.register("bale_monkey_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.BALE, 0x675e57, 0xc2d2d1, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> PROBOSCIS_SPAWN_EGG = ITEMS.register("proboscis_monkey_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.PROBOSCIS, 0xc49d61, 0xe6e7ee, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> SLOW_LORIS_SPAWN_EGG = ITEMS.register("slow_loris_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.SLOW_LORIS, 0xc6bebb, 0x643f24, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> MANDRILL_SPAWN_EGG = ITEMS.register("mandrill_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.MANDRILL, 0x2c292a, 0x5a70ad, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> GIGANTO_SPAWN_EGG = ITEMS.register("gigantopithecus_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.GIGANTOPITHECUS, 0x9c4317, 0x86849b, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> JAPAN_SPAWN_EGG = ITEMS.register("japanese_macaque_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.JAPANESEMACAQUE, 0xa3958c, 0xae3b4f, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> POACHER_SPAWN_EGG = ITEMS.register("poacher_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.POACHER, 0x242827, 0x959b9b, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> CRESTED_SPAWN_EGG = ITEMS.register("crested_macaque_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.CRESTEDMACAQUE, 0x363838, 0xdb8794, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> BONOBO_SPAWN_EGG = ITEMS.register("bonobo_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.BONOBO, 0x1a1b1e, 0x4d4e55, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> GELADA_SPAWN_EGG = ITEMS.register("gelada_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.GELADA, 0xbb7341, 0x993035, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));
    public static final RegistryObject<ForgeSpawnEggItem> GIBBON_SPAWN_EGG = ITEMS.register("gibbon_spawn_egg",() -> new ForgeSpawnEggItem(MMEntitysRegistry.GIBBON, 0x322d34, 0xd5cdcb, new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS)));

    //Holdables
    public static final RegistryObject<Item> PAN_FLUTE = ITEMS.register("pan_flute", PanFlute::new);
    public static final RegistryObject<SwordItem> CARVER = ITEMS.register("carvers_knife", () -> new SwordItem(Carver.BAT, 4, -1.8f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)) {});
    public static final RegistryObject<MonkeyMotivator> MOTIVATOR = ITEMS.register("monkey_motivator", () -> new MonkeyMotivator(Motivator.MOTIVATING, 3, -2.8f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)) {});

}
