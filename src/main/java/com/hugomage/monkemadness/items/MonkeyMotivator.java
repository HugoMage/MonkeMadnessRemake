package com.hugomage.monkemadness.items;

import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class MonkeyMotivator extends DiggerItem {
    public MonkeyMotivator(Tier p_42961_, int p_42962_, float p_42963_, Item.Properties p_42964_) {
        super((float)p_42962_, p_42963_, p_42961_, BlockTags.MINEABLE_WITH_PICKAXE, p_42964_);
    }
    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack stack = p_43406_.getItemInHand(p_43407_);
        addParticleEffect(ParticleTypes.NOTE, p_43405_, p_43406_.getX() - 0.5, p_43406_.getY() + 1, p_43406_.getZ() - 0.5);
        p_43406_.playSound(MMSoundsRegistry.ORANGUTAN_AMBIENT.get(), 0.4f, 1);
        return InteractionResultHolder.pass(stack);
    }
    private void addParticleEffect(SimpleParticleType particleData, Level world, double x, double y, double z) {
        Random random = new Random();
        for(int i = 0; i < 1; ++i) {
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextGaussian() * 0.02D;
            double d4 = random.nextGaussian() * 0.02D;
            double d6 = x + random.nextDouble();
            double d7 = y + random.nextDouble() * 0.5;
            double d8 = z + random.nextDouble();
            world.addParticle(particleData, d6, d7, d8, d2, d3, d4);
        }
    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_PICKAXE_ACTIONS.contains(toolAction);
    }
}
