package com.hugomage.monkemadness.entities;

import com.hugomage.monkemadness.registry.MMItemsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class Poacher extends AbstractIllager {
    public Poacher(EntityType<? extends Poacher> p_i48556_1_, Level p_i48556_2_) {
        super(p_i48556_1_, p_i48556_2_);

    }
    @Nullable
    private BlockPos homeTarget;
    private int cooldown;
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 3D)
                .add(Attributes.ATTACK_SPEED, 4D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D);
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevel worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        SpawnGroupData ientitylivingdata = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setEquipmentBasedOnDifficulty(difficultyIn);
        this.setEquipmentBasedOnDifficulty(difficultyIn);
        return ientitylivingdata;
    }


    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new OcelotAttackGoal(this));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(2, new RaiderOpenDoorGoal(this));
        this.targetSelector.addGoal( 0, new NearestAttackableTargetGoal<>( this, Ocelot.class, true ) );
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Villager.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Animal.class, true));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @OnlyIn(Dist.CLIENT)
    public IllagerArmPose getArmPose() {
        if (this.isAggressive()) {
            return IllagerArmPose.ATTACKING;
        } else {
            return this.isCelebrating() ? IllagerArmPose.CELEBRATING : IllagerArmPose.CROSSED;
        } }



    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        ItemStack prettyHeavyStack = new ItemStack(MMItemsRegistry.CARVER.get());
        this.setItemSlot(EquipmentSlot.MAINHAND, prettyHeavyStack);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.VINDICATOR_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.VINDICATOR_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
        return SoundEvents.VINDICATOR_HURT;
    }

    @Override
    public void applyRaidBuffs(int wave, boolean p_213660_2_) {

    }
    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(MMItemsRegistry.POACHER_SPAWN_EGG.get());
    }
    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }
}
