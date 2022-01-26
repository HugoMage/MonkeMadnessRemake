package com.hugomage.monkemadness.items.tools;

import com.hugomage.monkemadness.registry.MMItemsRegistry;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class Motivator {
    public static Tier MOTIVATING = new Tier() {
        @Override
        public int getUses() {
            return 1000;
        }

        @Override
        public float getSpeed() {
            return 8;
        }

        @Override
        public float getAttackDamageBonus() {
            return 2;
        }

        @Override
        public int getLevel() {
            return 5;
        }

        @Override
        public int getEnchantmentValue() {
            return 20;
        }

        @Override
        public Ingredient getRepairIngredient() {
            Ingredient repairMaterial = Ingredient.of(MMItemsRegistry.GOlDEN_BANANA.get());
            return repairMaterial;
        }


    };


}
