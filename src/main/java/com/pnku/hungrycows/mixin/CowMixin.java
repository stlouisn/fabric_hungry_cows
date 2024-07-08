package com.pnku.hungrycows.mixin;

import com.pnku.hungrycows.config.HungryCowsConfig;
import com.pnku.hungrycows.item.PinkFoodComponents;
import com.pnku.hungrycows.util.ICowEntity;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.pnku.hungrycows.HungryCows.IS_MILKED;

@Mixin(Cow.class)
public abstract class CowMixin extends Animal implements Shearable, ICowEntity {
    @Unique
    private EatBlockGoal cowEatGrassGoal;
    @Unique
    private int eatGrassTimer;

    public CowMixin(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }
    @Inject(method = "registerGoals", at = @At("HEAD"))
    protected void injectedRegisterGoals(CallbackInfo info) {
        this.cowEatGrassGoal = new EatBlockGoal(this);
        this.goalSelector.addGoal(HungryCowsConfig.getInstance().getGrassEatPriority(), this.cowEatGrassGoal);
    }

    protected void customServerAiStep() {
        this.eatGrassTimer = this.cowEatGrassGoal.getEatAnimationTick();
        super.customServerAiStep();
    }
    public void aiStep() {
        if (this.level().isClientSide) {
            this.eatGrassTimer = Math.max(0, this.eatGrassTimer - 1);
        }

        super.aiStep();
    }
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_MILKED, (byte)0);
    }

    public void handleEntityEvent(byte status) {
        if (status == 10) {
            this.eatGrassTimer = 40;
        } else {
            super.handleEntityEvent(status);
        }
    }

    @Unique
    public float hungrycows$getNeckAngle(float delta) {
        if (this.eatGrassTimer <= 0) {
            return 0.0F;
        } else if (this.eatGrassTimer >= 4 && this.eatGrassTimer <= 36) {
            return 1.0F;
        } else {
            return this.eatGrassTimer < 4 ? ((float)this.eatGrassTimer - delta) / 4.0F : -((float)(this.eatGrassTimer - 40) - delta) / 4.0F;
        }
    }

    @Unique
    public float hungrycows$getHeadAngle(float delta) {
        if (this.eatGrassTimer > 4 && this.eatGrassTimer <= 36) {
            float f = ((float)(this.eatGrassTimer - 4) - delta) / 32.0F;
            return 0.62831855F + 0.21991149F * Mth.sin(f * 28.7F);
        } else {
            return this.eatGrassTimer > 0 ? 0.62831855F : this.getXRot() * 0.017453292F;
        }
    }

    @Unique
    public boolean isMilkable() { return this.isAlive() && !this.isMilked() && !this.isBaby();
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Milked", this.isMilked());
    }
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setMilked(nbt.getBoolean("Milked"));
    }
    @Unique
    public boolean isMilked() {
        return ((Byte)this.entityData.get(IS_MILKED)) != 0;
    }
    @Unique
    public void setMilked(boolean isMilked) {
        byte isMilkedByte = isMilked ? (byte) 1 : (byte) 0;

        this.entityData.set(IS_MILKED, isMilkedByte);
    }

    public void ate() {
        super.ate();
        this.setMilked(false);
        if (this.isBaby()) {
            this.ageUp(60);
        }
    }

    static {
        IS_MILKED = SynchedEntityData.defineId(CowMixin.class, EntityDataSerializers.BYTE);
    }

    @Unique
    public ItemStack getEdibleMilk(){
        ItemStack edibleMilk = new ItemStack(Items.MILK_BUCKET);
        edibleMilk.set(DataComponents.FOOD, PinkFoodComponents.MILK_BUCKET);

        return edibleMilk;
    }

    @Inject(method = "mobInteract", at = @At("HEAD"), cancellable = true)
    private void injectedMobInteract(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.BUCKET)){
            if (!this.isBaby() && this.isMilkable()) {
                this.setMilked(true);
                player.playSound(SoundEvents.AMETHYST_BLOCK_RESONATE, 0.317F, 3.17F);
                player.playSound(SoundEvents.COW_MILK, 1.317F, 1.237F);
                ItemStack itemStackMilk = ItemUtils.createFilledResult(itemStack, player, getEdibleMilk());
                player.setItemInHand(hand, itemStackMilk);

                cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide));
            }
            else cir.setReturnValue(InteractionResult.PASS);
        }
    }
}