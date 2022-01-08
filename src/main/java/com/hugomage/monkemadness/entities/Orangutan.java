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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class Orangutan extends Animal {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(Orangutan.class, EntityDataSerializers.INT);
    public static final Ingredient BREEDITEM = Ingredient.of(Items.SUGAR_CANE, Items.MELON_SLICE, Items.HONEYCOMB, Items.BAMBOO);
    public Orangutan(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }
    public int attackAnimationRemainingTicks;
    public int getAttackAnimationRemainingTicks() {
        return this.attackAnimationRemainingTicks;
    }
    public int getVariant() { return this.entityData.get(DATA_VARIANT_ID); }
    private void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, variant); }
    public boolean isFood(ItemStack stack) {
        return BREEDITEM.test(stack);
    }
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.ATTACK_DAMAGE, 6D)
                .add(Attributes.ATTACK_SPEED, 4D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D);
    }
    public boolean doHurtTarget(@NotNull Entity p_70652_1_) {
        if (!(p_70652_1_ instanceof LivingEntity)) {
            return false;
        } else {
            this.attackAnimationRemainingTicks = 10;
            this.level.broadcastEntityEvent(this, (byte)4);
            this.playSound(MMSoundsRegistry.ORANGUTAN_HURT.get(), 1.0F, this.getVoicePitch());
            return HoglinBase.hurtAndThrowTarget(this, (LivingEntity)p_70652_1_);
        }
    }

    public void aiStep() {
        if (this.attackAnimationRemainingTicks > 0) {
            --this.attackAnimationRemainingTicks;
        }
        super.aiStep();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte p_70103_1_) {
        if (p_70103_1_ == 4) {
            this.attackAnimationRemainingTicks = 10;
            this.playSound(MMSoundsRegistry.ORANGUTAN_HURT.get(), 0.5F, this.getVoicePitch());
        } else {
            super.handleEntityEvent(p_70103_1_);
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(0, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new TemptGoal(this, 1.1D, BREEDITEM, false));
        this.goalSelector.addGoal(7, new FollowParentGoal(this, 1.1D));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverWorld, @NotNull AgeableMob ageableEntity) {
        Orangutan orangutan = MMEntitysRegistry.ORANGUTAN.get().create(serverWorld);
        assert orangutan != null;
        orangutan.setVariant(this.getVariant());
        return MMEntitysRegistry.ORANGUTAN.get().create(serverWorld);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());

    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));

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
        return MMSoundsRegistry.ORANGUTAN_AMBIENT.get();
    }


    @Override
    protected void playStepSound(@NotNull BlockPos pos, BlockState blockIn )
    {
        if ( !blockIn.getMaterial().isLiquid() )
        {
            this.playSound( SoundEvents.COW_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume() );
        }
    }

    @Nullable
    @Override
    protected SoundEvent  getHurtSound (@NotNull DamageSource damageSource ) {
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
    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(MMItemsRegistry.ORANGUTAN_SPAWN_EGG.get());
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if(this.random.nextInt(2) == 0){
            this.setVariant(1);
        }else if(random.nextInt(50) == 0){
            this.setVariant(2);
        }else if(random.nextInt(50) == 0){
            this.setVariant(3);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

}
