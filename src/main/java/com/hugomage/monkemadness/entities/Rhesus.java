package com.hugomage.monkemadness.entities;
import com.hugomage.monkemadness.registry.MMItemsRegistry;
import com.hugomage.monkemadness.registry.MMSoundsRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class Rhesus extends Animal {

    public static final Ingredient BREEDITEM = Ingredient.of(MMItemsRegistry.GOlDEN_BANANA.get());
    private int ticksSinceEaten;
    private static final Predicate<ItemEntity> TRUSTED_TARGET_SELECTOR = (p_213489_0_) -> {
        return !p_213489_0_.hasPickUpDelay() && p_213489_0_.isAlive();
    };
    public Rhesus(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        this.setCanPickUpLoot(true);
    }

    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(Rhesus.class, EntityDataSerializers.INT);
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.40D)
                .add(Attributes.ATTACK_DAMAGE, 1D)
                .add(Attributes.ATTACK_SPEED, 6D)
                .add(Attributes.ATTACK_KNOCKBACK, 0D);
    }
    private static final Predicate<ItemEntity> PICKABLE_DROP_FILTER = item -> item != null && !item.hasPickUpDelay();
    public SoundEvent getEatSound(ItemStack itemStackIn) {
        return SoundEvents.FOX_EAT;
    }
    private boolean canMove() {
        return !this.isSleeping();
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new TemptGoal(this, 1.1D, BREEDITEM, false));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new Rhesus.FindItemsGoal());
        this.goalSelector.addGoal(10, new Rhesus.EatFoodGoal(1.2F, 12, 2));
    }
    public void aiStep() {

        if (!this.level.isClientSide && this.isAlive() && this.isEffectiveAi()) {
            ++this.ticksSinceEaten;
            ItemStack itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (this.canEat(itemstack)) {

                if (this.ticksSinceEaten > 600) {
                    ItemStack itemstack1 = itemstack.finishUsingItem(this.level, this);
                    if (!itemstack1.isEmpty()) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack1);
                    }

                    this.ticksSinceEaten = 0;
                } else if (this.ticksSinceEaten > 560 && this.random.nextFloat() < 0.1F) {
                    this.playSound(this.getEatingSound(itemstack), 1.0F, 1.0F);
                    this.addEatingParticles();
                    this.level.broadcastEntityEvent(this, (byte)45);
                }
            }
        }

        super.aiStep();
    }
    private void addEatingParticles() {

            this.playSound(SoundEvents.PANDA_EAT, 0.5F + 0.5F * (float)this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            for(int i = 0; i < 6; ++i) {
                Vec3 vec3 = new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, ((double)this.random.nextFloat() - 0.5D) * 0.1D);
                vec3 = vec3.xRot(-this.getXRot() * ((float)Math.PI / 180F));
                vec3 = vec3.yRot(-this.getYRot() * ((float)Math.PI / 180F));
                double d0 = (double)(-this.random.nextFloat()) * 0.6D - 0.3D;
                Vec3 vec31 = new Vec3(((double)this.random.nextFloat() - 0.5D) * 0.8D, d0, 1.0D + ((double)this.random.nextFloat() - 0.5D) * 0.4D);
                vec31 = vec31.yRot(-this.yBodyRot * ((float)Math.PI / 180F));
                vec31 = vec31.add(this.getX(), this.getEyeY() + 1.0D, this.getZ());
                this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItemBySlot(EquipmentSlot.MAINHAND)), vec31.x, vec31.y, vec31.z, vec3.x, vec3.y + 0.05D, vec3.z);
            }
        }




    private boolean canEat(ItemStack p_213464_1_) {
        return p_213464_1_.getItem().isEdible() && this.getTarget() == null && this.onGround;
    }
    public class EatFoodGoal extends MoveToBlockGoal {
        protected int ticksWaited;

        public EatFoodGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
            super(Rhesus.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
        }

        public double acceptedDistance() {
            return 2.0D;
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 100 == 0;
        }

        @Override
        protected boolean isValidTarget(LevelReader p_25619_, BlockPos p_25620_) {
            BlockState blockstate = p_25619_.getBlockState(p_25620_);
            return blockstate.is(Blocks.SWEET_BERRY_BUSH) && blockstate.getValue(SweetBerryBushBlock.AGE) >= 2;
        }

        public void tick() {
            if (this.isReachedTarget()) {
                if (this.ticksWaited >= 40) {
                    this.onReachedTarget();
                } else {
                    ++this.ticksWaited;
                }
            } else if (!this.isReachedTarget() && Rhesus.this.random.nextFloat() < 0.05F) {
                Rhesus.this.playSound(SoundEvents.FOX_SNIFF, 1.0F, 1.0F);
            }

            super.tick();
        }

        protected void onReachedTarget() {
            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(Rhesus.this.level, Rhesus.this)) {
                BlockState blockstate = Rhesus.this.level.getBlockState(this.blockPos);
                if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
                    int i = blockstate.getValue(SweetBerryBushBlock.AGE);
                    blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1));
                    int j = 1 + Rhesus.this.level.random.nextInt(2) + (i == 3 ? 1 : 0);
                    ItemStack itemstack = Rhesus.this.getItemBySlot(EquipmentSlot.MAINHAND);
                    if (itemstack.isEmpty()) {
                        Rhesus.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
                        --j;
                    }

                    if (j > 0) {
                        Block.popResource(Rhesus.this.level, this.blockPos, new ItemStack(Items.SWEET_BERRIES, j));
                    }

                    Rhesus.this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
                    Rhesus.this.level.setBlock(this.blockPos, blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
                }
            }
        }

        public boolean canUse() {
            return !Rhesus.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
    class FindItemsGoal extends Goal {
        public FindItemsGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }
        public boolean canUse() {
            if (!Rhesus.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else if (Rhesus.this.getTarget() == null && Rhesus.this.getLastHurtByMob() == null) {
                if (!Rhesus.this.canMove()) {
                    return false;
                } else if (Rhesus.this.getRandom().nextInt(10) != 0) {
                    return false;
                } else {
                    List<ItemEntity> list = Rhesus.this.level.getEntitiesOfClass(ItemEntity.class, Rhesus.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Rhesus.TRUSTED_TARGET_SELECTOR);
                    return !list.isEmpty() && Rhesus.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
                }
            } else {
                return false;
            }
        }

        public void tick() {
            List<ItemEntity> list = Rhesus.this.level.getEntitiesOfClass(ItemEntity.class, Rhesus.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Rhesus.TRUSTED_TARGET_SELECTOR);
            ItemStack itemstack = Rhesus.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                Rhesus.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }

        public void start() {
            List<ItemEntity> list = Rhesus.this.level.getEntitiesOfClass(ItemEntity.class, Rhesus.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Rhesus.TRUSTED_TARGET_SELECTOR);
            if (!list.isEmpty()) {
                Rhesus.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }
    }

    private boolean func_213478_eo() {
        return !this.isSleeping();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        if (!blockIn.getMaterial().isLiquid()) {
            this.playSound(SoundEvents.PARROT_STEP, this.getSoundVolume() * 0.3F, this.getSoundVolume());
        }
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return MMSoundsRegistry.RHESUS_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return MMSoundsRegistry.RHESUS_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return MMSoundsRegistry.RHESUS_DEATH.get();
    }


    protected int getExperienceReward(Player p_70693_1_) {
        if (this.isBaby()) {
            this.xpReward = (int)((float)this.xpReward * 6.5F);
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

    public boolean isFood(ItemStack stack) {
        Item item = stack.getItem();
        return item.isEdible() && item.getFoodProperties().isMeat();
    }
    public boolean isBreedingItem(@NotNull ItemStack stack) {
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
        if(this.random.nextInt(100) == 0){
            this.setVariant(2);
        }else if(random.nextInt(100) == 0){
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