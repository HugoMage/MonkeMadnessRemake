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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import javax.annotation.Nullable;

public class HowlerMonkey extends Animal {

    public static final Ingredient BREEDITEM = Ingredient.of(Items.SUGAR_CANE, Items.MELON_SLICE, Items.HONEYCOMB);
    public HowlerMonkey(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }
    public int timeUntilNextPoop = this.random.nextInt(4000) + 4000;

    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(HowlerMonkey.class, EntityDataSerializers.INT);
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.40D)
                .add(Attributes.ATTACK_DAMAGE, 1D)
                .add(Attributes.ATTACK_SPEED, 6D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D);
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, BREEDITEM, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.0D));
    }
    public int GetOpenMouthTexture() {
        return this.random.nextInt(2000) + 2000;
    }
    public boolean IsOpenMouthTexture() {
        return this.GetOpenMouthTexture() <= 0;
    }
    public void aiStep() {
        if (!this.level.isClientSide && this.isAlive() && this.GetOpenMouthTexture() <= 0) {
            this.playSound(MMSoundsRegistry.PAN_FLUTE_WHISTLE.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.GetOpenMouthTexture();
        }
        if (!this.level.isClientSide && this.isAlive() && --this.timeUntilNextPoop <= 0) {
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(MMItemsRegistry.POOP.get());
            this.timeUntilNextPoop = this.random.nextInt(3000) + 3000;
        }
        super.aiStep();
    }
    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn )
    {
        if ( !blockIn.getMaterial().isLiquid() )
        {
            this.playSound( SoundEvents.PARROT_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume() );
        }
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSoundsRegistry.UAKARI_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return MMSoundsRegistry.UAKARI_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSoundsRegistry.UAKARI_DEATH.get();
    }


    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 0.5F);
        }

        return super.getExperienceReward(p_70693_1_);
    }

    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) {
            if (!p_230254_1_.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.heal((float) item.getFoodProperties().getNutrition());
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(p_230254_1_, p_230254_2_); }

    public boolean isBreedingItem(ItemStack stack) {
        return BREEDITEM.test(stack);
    }


    public int getVariant() {
        return this.entityData.get(DATA_VARIANT_ID);
    }

    private void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, variant);
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevel worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        if(this.random.nextInt(4) == 0){
            this.setVariant(2);
        }else if(random.nextInt(4) == 0){
            this.setVariant(1);
        }
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_241840_1_, AgeableMob p_241840_2_) {
        return null;
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

}