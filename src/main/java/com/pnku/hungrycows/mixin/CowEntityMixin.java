package com.pnku.hungrycows.mixin;


import com.pnku.hungrycows.item.PinkFoodComponents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.pnku.hungrycows.HungryCows.IS_MILKED;

@Mixin(CowEntity.class)
public abstract class CowEntityMixin extends AnimalEntity implements Shearable {


    @Unique
    private EatGrassGoal cowEatGrassGoal;
    @Unique
    private int eatGrassTimer;

    public CowEntityMixin(EntityType<? extends CowEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "initGoals", at = @At("HEAD"))
    protected void injectedInitGoals(CallbackInfo info) {
        this.cowEatGrassGoal = new EatGrassGoal(this);
        this.goalSelector.add(8, this.cowEatGrassGoal);
    }

    protected void mobTick() {
        this.eatGrassTimer = this.cowEatGrassGoal.getTimer();
        super.mobTick();
    }
    public void tickMovement() {
        if (this.getWorld().isClient) {
            this.eatGrassTimer = Math.max(0, this.eatGrassTimer - 1);
        }

        super.tickMovement();
    }
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(IS_MILKED, (byte)0);
    }

    public void handleStatus(byte status) {
        if (status == 10) {
            this.eatGrassTimer = 40;
        } else {
            super.handleStatus(status);
        }
    }

    @Unique
    public boolean isMilkable() { return this.isAlive() && !this.isMilked() && !this.isBaby();
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Milked", this.isMilked());
    }
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setMilked(nbt.getBoolean("Milked"));
    }
    @Unique
    public boolean isMilked() {
        return ((Byte)this.dataTracker.get(IS_MILKED)) != 0;
    }
    @Unique
    public void setMilked(boolean isMilked) {
        byte isMilkedByte = isMilked ? (byte) 1 : (byte) 0;

        this.dataTracker.set(IS_MILKED, isMilkedByte);
    }

    public void onEatingGrass() {
        super.onEatingGrass();
        this.setMilked(false);
        if (this.isBaby()) {
            this.growUp(60);
        }
    }

    static {
        IS_MILKED = DataTracker.registerData(CowEntityMixin.class, TrackedDataHandlerRegistry.BYTE);
    }

    @Unique
    public ItemStack getEdibleMilk(){
        ItemStack edibleMilk = new ItemStack(Items.MILK_BUCKET);
        edibleMilk.set(DataComponentTypes.FOOD, PinkFoodComponents.MILK_BUCKET);

        return edibleMilk;
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void injectedInteractMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.BUCKET)){
            if (!this.isBaby() && this.isMilkable()) {
                this.setMilked(true);
                player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, 0.317F, 3.17F);
                player.playSound(SoundEvents.ENTITY_COW_MILK, 1.317F, 1.237F);
                ItemStack itemStackMilk = ItemUsage.exchangeStack(itemStack, player, getEdibleMilk());
                player.setStackInHand(hand, itemStackMilk);

                cir.setReturnValue(ActionResult.success(this.getWorld().isClient));
            }
            else cir.setReturnValue(ActionResult.PASS);
        }
    }
}