package com.hugomage.monkemadness.entities;

import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ZombieApe extends Monster {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.HONEYCOMB);
    public static final Predicate<LivingEntity> TARGET_ENTITIES = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.COW || entitytype == EntityType.FOX;
    };

    public ZombieApe(EntityType<? extends Monster> type, net.minecraft.world.level.Level worldIn) {
        super(type, worldIn);
        this.setCanPickUpLoot(true);
    }
    public int attackAnimationRemainingTicks;
    public boolean doHurtTarget(Entity p_70652_1_) {
        if (!(p_70652_1_ instanceof LivingEntity)) {
            return false;
        } else {
            this.attackAnimationRemainingTicks = 10;
            this.level.broadcastEntityEvent(this, (byte)4);
            this.playSound(MMSoundsRegistry.ZOMBIEAPE_HURT.get(), 1.0F, this.getVoicePitch());
            return HoglinBase.hurtAndThrowTarget(this, (LivingEntity)p_70652_1_);
        }
    }
    public void aiStep() {


        if (this.attackAnimationRemainingTicks > 0) {
            --this.attackAnimationRemainingTicks;
        }

        super.aiStep();
    }
    public int getAttackAnimationRemainingTicks() {
        return this.attackAnimationRemainingTicks;
    }
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 4) {
            this.attackAnimationRemainingTicks = 10;
            this.playSound(MMSoundsRegistry.ZOMBIEAPE_HURT.get(), 0.5F, this.getVoicePitch());
        } else {
            super.handleEntityEvent(p_70103_1_);
        }

    }
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 7D)
                .add(Attributes.ATTACK_SPEED, 6D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D);
    }
    @Override
    protected void registerGoals() {

        super.registerGoals();
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal( 0, new NearestAttackableTargetGoal<>( this, Ocelot.class, true ) );
        this.targetSelector.addGoal( 0, new NearestAttackableTargetGoal<>( this, Orangutan.class, true ) );
        this.targetSelector.addGoal( 6, new NearestAttackableTargetGoal<>( this, Orangutan.class, true ) );
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Orangutan.class, true));
        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
        this.targetSelector.addGoal( 0, new NearestAttackableTargetGoal<>( this, Orangutan.class, true ) );
    }


    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 6.5F);
        }

        return super.getExperienceReward(p_70693_1_);
    }


    @Nullable
    @Override
    protected SoundEvent getAmbientSound ()
    {
        return MMSoundsRegistry.ZOMBIEAPE_AMBIENT.get();
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
        return MMSoundsRegistry.ZOMBIEAPE_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound () { return MMSoundsRegistry.ZOMBIEAPE_DEATH.get(); }
}
