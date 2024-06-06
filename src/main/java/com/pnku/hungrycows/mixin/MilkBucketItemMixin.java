package com.pnku.hungrycows.mixin;

import net.fabricmc.fabric.mixin.transfer.BucketItemMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin extends Item {
    public MilkBucketItemMixin(Item.Settings settings) {super(settings);}
    @Inject(method = "finishUsing", at = @At("HEAD"))
    protected void injectedFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        super.finishUsing(stack, world, user);
    }
}
