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
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.hoglin.HoglinBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class Gigantopithecus extends Animal {
    public Gigantopithecus(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(Gigantopithecus.class, EntityDataSerializers.INT);
    public static final Ingredient BREEDITEM = Ingredient.of(Items.BAMBOO);
    private int attackAnimationRemainingTicks;
    public boolean inWall;
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.ATTACK_DAMAGE, 20D)
                .add(Attributes.ATTACK_SPEED, 4D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1D);

    }

    public boolean doHurtTarget(Entity p_70652_1_) {
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

        if (!this.level.isClientSide) {
            this.inWall = this.checkWalls(this.getBoundingBox());
        }
        if (this.attackAnimationRemainingTicks > 0) {
            --this.attackAnimationRemainingTicks;
        }

        super.aiStep();
    }

    private boolean checkWalls(AABB p_31140_) {
        int i = Mth.floor(p_31140_.minX);
        int j = Mth.floor(p_31140_.minY);
        int k = Mth.floor(p_31140_.minZ);
        int l = Mth.floor(p_31140_.maxX);
        int i1 = Mth.floor(p_31140_.maxY);
        int j1 = Mth.floor(p_31140_.maxZ);
        boolean flag = false;
        boolean flag1 = false;

        for(int k1 = i; k1 <= l; ++k1) {
            for(int l1 = j; l1 <= i1; ++l1) {
                for(int i2 = k; i2 <= j1; ++i2) {
                    BlockPos blockpos = new BlockPos(k1, l1, i2);
                    BlockState blockstate = this.level.getBlockState(blockpos);
                    if (!blockstate.isAir() && blockstate.is(Blocks.BAMBOO) && blockstate.getMaterial() != Material.FIRE) {
                        if (net.minecraftforge.common.ForgeHooks.canEntityDestroy(this.level, blockpos, this)) {
                            flag1 = this.level.removeBlock(blockpos, false) || flag1;
                        } else {
                            flag = true;
                        }
                    }
                }
            }
        }

        if (flag1) {
            BlockPos blockpos1 = new BlockPos(i + this.random.nextInt(l - i + 1), j + this.random.nextInt(i1 - j + 1), k + this.random.nextInt(j1 - k + 1));
            this.level.levelEvent(2008, blockpos1, 0);
        }

        return flag;
    }

    public int getAttackAnimationRemainingTicks() {
        return this.attackAnimationRemainingTicks;
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

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
       {

                if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                    if (!p_230254_1_.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }

                    this.heal((float)item.getFoodProperties().getNutrition());
                    return InteractionResult.SUCCESS;
                }

            return super.mobInteract(p_230254_1_, p_230254_2_);
    }}

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDITEM.test(stack);
    }
     @Override
    protected void registerGoals() {
        super.registerGoals();
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(0, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, Ingredient.of(Items.SUGAR_CANE), false));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, ZombieApe.class, true));

    }

    protected float getStandingEyeHeight(Pose p_33799_, EntityDimensions p_33800_) {
        return 3.3F;
    }

    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 10.5F);
        }

        return super.getExperienceReward(p_70693_1_);
    }
    @Override
    public ItemStack getPickedResult(HitResult target) {
        return new ItemStack(MMItemsRegistry.GIGANTO_SPAWN_EGG.get());
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageableEntity) {
        return MMEntitysRegistry.GIGANTOPITHECUS.get().create(serverWorld);
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

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("variant"));

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
            this.playSound( SoundEvents.COW_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume() );
        }
    }

    @Nullable
    @Override
    protected SoundEvent  getHurtSound (DamageSource damageSource ) {
        return (MMSoundsRegistry.ORANGUTAN_HURT.get());
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

    public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    private void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevel worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if(this.random.nextInt(100) == 0){
            this.setVariant(2);
        }else if(random.nextInt(100) == 0){
            this.setVariant(1);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


}


