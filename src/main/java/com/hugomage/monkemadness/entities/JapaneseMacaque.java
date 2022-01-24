package com.hugomage.monkemadness.entities;
import com.hugomage.monkemadness.registry.MMEntitysRegistry;
import com.hugomage.monkemadness.registry.MMItemsRegistry;
import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;

import javax.annotation.Nullable;

public class JapaneseMacaque extends Animal {

    public static final Ingredient BREEDITEM = Ingredient.of(Items.SUGAR_CANE, Items.MELON_SLICE, Items.HONEYCOMB);

    public JapaneseMacaque(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }


    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.ATTACK_SPEED, 6D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D);
    }



    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, BREEDITEM, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new JapaneseMacaque.JapaneseMacaqueGoToWaterGoal(this, 1.0D));
    }
    public boolean isFood(ItemStack stack) {
        return BREEDITEM.test(stack);
    }

    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 0.5F);
        }
        return super.getExperienceReward(p_70693_1_);
    }
    static class JapaneseMacaqueGoToWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final JapaneseMacaque turtle;

        JapaneseMacaqueGoToWaterGoal(JapaneseMacaque p_30262_, double p_30263_) {
            super(p_30262_, p_30262_.isBaby() ? 2.0D : p_30263_, 24);
            this.turtle = p_30262_;
            this.verticalSearchStart = -1;
        }

        public boolean canContinueToUse() {
            return !this.turtle.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.turtle.level, this.blockPos);
        }

        public boolean canUse() {
            if (this.turtle.isBaby() && !this.turtle.isInWater()) {
                return super.canUse();
            } else {
                return !this.turtle.isInWater() && super.canUse();
            }
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader p_30270_, BlockPos p_30271_) {
            return p_30270_.getBlockState(p_30271_).is(Blocks.WATER);
        }
    }
    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(MMItemsRegistry.JAPAN_SPAWN_EGG.get());
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        JapaneseMacaque orangutan = MMEntitysRegistry.JAPANESEMACAQUE.get().create(p_241840_1_);
        assert orangutan != null;
        return MMEntitysRegistry.JAPANESEMACAQUE.get().create(p_241840_1_);
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound ()
    {
        return MMSoundsRegistry.MANDRILL_AMBIENT.get();
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn )
    {
        if ( !blockIn.getMaterial().isLiquid() )
        {
            this.playSound( SoundEvents.ZOMBIE_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume() );
        }
    }
    @Nullable
    @Override
    protected SoundEvent  getHurtSound (DamageSource damageSource ) {
        return MMSoundsRegistry.ORANGUTAN_HURT.get();
    }
    @Nullable
    @Override
    protected SoundEvent getDeathSound () { return MMSoundsRegistry.ORANGUTAN_DEATH.get(); }
    public void baseTick() {
        super.baseTick();
        this.level.getProfiler().push("mobBaseTick");
        if (this.isAlive() && this.random.nextInt(1000) < this.ambientSoundTime++) {
            this.playAmbientSound();
        }
        this.level.getProfiler().pop();
    }
    private void resetAmbientSoundTime() {
        this.ambientSoundTime = -this.getAmbientSoundInterval();
    }
    public int livingSoundTime() {
        return 0;
    }
}


