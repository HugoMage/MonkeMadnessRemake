package com.hugomage.monkemadness.entities;

import com.hugomage.monkemadness.registry.MMEntitysRegistry;
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
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class SnubNosedMonkey extends Animal {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(SnubNosedMonkey.class, EntityDataSerializers.INT);
    public static final Ingredient BREEDITEM = Ingredient.of(Items.SWEET_BERRIES, Items.MELON_SLICE, Items.HONEYCOMB, Items.BAMBOO);
    private int ticksSinceEaten;
    private boolean invulnerable;
    private static final Predicate<ItemEntity> TRUSTED_TARGET_SELECTOR = (p_213489_0_) -> { return !p_213489_0_.hasPickUpDelay() && p_213489_0_.isAlive(); };
    public SnubNosedMonkey(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.DANGER_OTHER, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_OTHER, 0.0F);
        this.setCanPickUpLoot(true);
    }
     public int getVariant() { return this.entityData.get(DATA_VARIANT_ID); }
    public boolean isFood(@NotNull ItemStack stack) {
        return BREEDITEM.test(stack);
    }
    private boolean canEat(ItemStack p_213464_1_) {
        return p_213464_1_.getItem().isEdible() && this.getTarget() == null && this.onGround;
    }
    private void setVariant(int variant) {
        this.entityData.set(DATA_VARIANT_ID, variant); }
    public static AttributeSupplier.Builder setCustomAttributes(){
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.40D);
    }
    @Override
    public boolean isInvulnerableTo(@NotNull DamageSource p_20122_) {
        return this.invulnerable && p_20122_ != DamageSource.SWEET_BERRY_BUSH && !p_20122_.isCreativePlayer();
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
        this.goalSelector.addGoal(7, new SnubNosedMonkey.FindItemsGoal());
        this.goalSelector.addGoal(10, new SnubNosedMonkey.EatFoodGoal(1.2F, 12, 2));
    }
    public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.isInvulnerableTo(p_70097_1_)) {
            return true;
        }


        return super.hurt(p_70097_1_, p_70097_2_);

    }
    @Override
    public InteractionResult mobInteract(Player p_230254_1_, InteractionHand p_230254_2_) {
        ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
        Item item = itemstack.getItem();
        if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
            if (!p_230254_1_.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.heal((float) item.getFoodProperties().getNutrition());
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(p_230254_1_, p_230254_2_); }

    public class EatFoodGoal extends MoveToBlockGoal {
        protected int ticksWaited;

        public EatFoodGoal(double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
            super(SnubNosedMonkey.this, p_i50737_2_, p_i50737_4_, p_i50737_5_);
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
            } else if (!this.isReachedTarget() && SnubNosedMonkey.this.random.nextFloat() < 0.05F) {
                SnubNosedMonkey.this.playSound(SoundEvents.FOX_SNIFF, 1.0F, 1.0F);
            }

            super.tick();
        }

        protected void onReachedTarget() {
            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(SnubNosedMonkey.this.level, SnubNosedMonkey.this)) {
                BlockState blockstate = SnubNosedMonkey.this.level.getBlockState(this.blockPos);
                if (blockstate.is(Blocks.SWEET_BERRY_BUSH)) {
                    int i = blockstate.getValue(SweetBerryBushBlock.AGE);
                    blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1));
                    int j = 1 + SnubNosedMonkey.this.level.random.nextInt(2) + (i == 3 ? 1 : 0);
                    ItemStack itemstack = SnubNosedMonkey.this.getItemBySlot(EquipmentSlot.MAINHAND);
                    if (itemstack.isEmpty()) {
                        SnubNosedMonkey.this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.SWEET_BERRIES));
                        --j;
                    }

                    if (j > 0) {
                        Block.popResource(SnubNosedMonkey.this.level, this.blockPos, new ItemStack(Items.SWEET_BERRIES, j));
                    }

                    SnubNosedMonkey.this.playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 1.0F, 1.0F);
                    SnubNosedMonkey.this.level.setBlock(this.blockPos, blockstate.setValue(SweetBerryBushBlock.AGE, Integer.valueOf(1)), 2);
                }
            }
        }

        public boolean canUse() {
            return !SnubNosedMonkey.this.isSleeping() && super.canUse();
        }

        public void start() {
            this.ticksWaited = 0;
            super.start();
        }
    }
    class FindItemsGoal extends Goal {
        public FindItemsGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }
        public boolean canUse() {
            if (!SnubNosedMonkey.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else if (SnubNosedMonkey.this.getTarget() == null && SnubNosedMonkey.this.getLastHurtByMob() == null) {
                if (SnubNosedMonkey.this.getRandom().nextInt(10) != 0) {
                    return false;
                } else {
                    List<ItemEntity> list = SnubNosedMonkey.this.level.getEntitiesOfClass(ItemEntity.class, SnubNosedMonkey.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), SnubNosedMonkey.TRUSTED_TARGET_SELECTOR);
                    return !list.isEmpty() && SnubNosedMonkey.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
                }
            } else {
                return false;
            }
        }

        public void tick() {
            List<ItemEntity> list = SnubNosedMonkey.this.level.getEntitiesOfClass(ItemEntity.class, SnubNosedMonkey.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), SnubNosedMonkey.TRUSTED_TARGET_SELECTOR);
            ItemStack itemstack = SnubNosedMonkey.this.getItemBySlot(EquipmentSlot.MAINHAND);
            if (itemstack.isEmpty() && !list.isEmpty()) {
                SnubNosedMonkey.this.getNavigation().moveTo(list.get(0), (double)1.2F);
            }

        }

        public void start() {
            List<ItemEntity> list = SnubNosedMonkey.this.level.getEntitiesOfClass(ItemEntity.class, SnubNosedMonkey.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), SnubNosedMonkey.TRUSTED_TARGET_SELECTOR);
            if (!list.isEmpty()) {
                SnubNosedMonkey.this.getNavigation().moveTo(list.get(0), 1.2F);
            }

        }
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
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverWorld, @NotNull AgeableMob ageableEntity) {
        SnubNosedMonkey orangutan = MMEntitysRegistry.SNUBNOSEDMONKEY.get().create(serverWorld);
        assert orangutan != null;
        orangutan.setVariant(this.getVariant());
        return MMEntitysRegistry.SNUBNOSEDMONKEY.get().create(serverWorld);
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
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));

    }
    protected int getExperienceReward(@NotNull Player p_70693_1_) {
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
        return new ItemStack(MMItemsRegistry.SNUBNOSED_SPAWN_EGG.get());
    }
    @Nullable
    public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor worldIn, @NotNull DifficultyInstance difficultyIn, @NotNull MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
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
