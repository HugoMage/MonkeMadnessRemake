package com.hugomage.monkemadness.entities;

import com.hugomage.monkemadness.registry.MMEntitysRegistry;
import com.hugomage.monkemadness.registry.MMItemsRegistry;
import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;


public class CrestedMacaque extends Animal {

    public static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.SUGAR_CANE);
    public int timeUntilNextPoop = this.random.nextInt(4000) + 4000;
    public CrestedMacaque(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(CrestedMacaque.class, EntityDataSerializers.INT);
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.33D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.ATTACK_SPEED, 6D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D);
    }



    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));

    }


    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 6.5F);
        }

        return super.getExperienceReward(p_70693_1_);
    }
    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(MMItemsRegistry.CRESTED_SPAWN_EGG.get());
    }
    public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    private void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    } @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevel worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if(this.random.nextInt(100) == 0){
            this.setVariant(2);
        }else if(random.nextInt(100) == 0){
            this.setVariant(1);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        CrestedMacaque baboonEntity = MMEntitysRegistry.CRESTEDMACAQUE.get().create(p_146743_);
        baboonEntity.setVariant(this.getVariant());
        return baboonEntity;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("variant", this.getVariant());

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("variant"));

    }
    public void aiStep() {
        if (!this.level.isClientSide && this.isAlive() && --this.timeUntilNextPoop <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(MMItemsRegistry.POOP.get());
            this.timeUntilNextPoop = this.random.nextInt(6000) + 6000;
        }
        super.aiStep();
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
            this.playSound( SoundEvents.WOLF_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume() );
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
    public boolean isFood(@NotNull ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }
    public void baseTick() {
        super.baseTick();
        this.level.getProfiler().push("mobBaseTick");
        if (this.isAlive() && this.random.nextInt(1000) < this.ambientSoundTime++) {
            this.playAmbientSound();
        }

        this.level.getProfiler().pop();
    }

}


