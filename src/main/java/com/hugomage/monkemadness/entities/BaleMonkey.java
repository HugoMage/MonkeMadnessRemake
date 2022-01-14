package com.hugomage.monkemadness.entities;

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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BaleMonkey extends Animal {
    private static final EntityDataAccessor<Boolean> SHEARED = SynchedEntityData.defineId(BaleMonkey.class, EntityDataSerializers.BOOLEAN);
    public static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.HONEYCOMB);
    public int timeUntilNextPoop = this.random.nextInt(4000) + 4000;
    public boolean isSheared() { return this.entityData.get(SHEARED); }
    public void setSheared(boolean sheared) { this.entityData.set(SHEARED, sheared); }
    public BaleMonkey(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.ATTACK_DAMAGE, 4D)
                .add(Attributes.ATTACK_SPEED, 6D)
                .add(Attributes.ATTACK_KNOCKBACK, 1D);
    }
    public void aiStep() {
        if (!this.level.isClientSide && this.isAlive() && --this.timeUntilNextPoop <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(MMItemsRegistry.POOP.get());
            this.timeUntilNextPoop = this.random.nextInt(6000) + 6000;
        }
        super.aiStep();
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 0.6D));
    }

    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 6.5F);
        }

        return super.getExperienceReward(p_70693_1_);
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound ()
    {
        return MMSoundsRegistry.ORANGUTAN_AMBIENT.get();
    }


    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn )
    {
        if ( !blockIn.getMaterial().isLiquid() )
        {
            this.playSound( SoundEvents.WOLF_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume() );
        }
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHEARED, false);
    }
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Sheared", this.isSheared());
    }
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSheared(compound.getBoolean("Sheared"));
    }
    @Nullable
    @Override
    protected SoundEvent  getHurtSound (DamageSource damageSource ) {
        return MMSoundsRegistry.ORANGUTAN_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound () { return MMSoundsRegistry.ORANGUTAN_DEATH.get(); }
    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (!isSheared() && item == Items.SHEARS && !level.isClientSide && !isBaby()) {
            shear();
            playSound(SoundEvents.SHEEP_SHEAR, getSoundVolume(), 1);
            p_230254_1_.getItemInHand(p_230254_2_).hurtAndBreak(1, p_230254_1_, (p_213613_1_) -> {
                p_213613_1_.broadcastBreakEvent(p_230254_2_);
            });
            spawnAtLocation(new ItemStack(MMItemsRegistry.BALE_MUSTACHE.get(), random.nextInt(1) + 1), 1);
            return InteractionResult.SUCCESS;

        }
        return super.mobInteract(p_230254_1_, p_230254_2_);
    }
    public void shear() {
        if (!level.isClientSide) {
            setSheared(true);
        }
    }
}
