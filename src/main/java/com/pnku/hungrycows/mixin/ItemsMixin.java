package com.pnku.hungrycows.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import org.spongepowered.asm.mixin.Mixin;

import com.pnku.hungrycows.item.PinkFoodComponents;
import org.spongepowered.asm.mixin.Unique;

import static net.minecraft.item.Items.BUCKET;

@Mixin(Items.class)
public abstract class ItemsMixin {
    public ItemsMixin(){}
    @Unique
    private static final Item MILK_BUCKET = Items.register("milk_bucket", (Item)new MilkBucketItem(new Item.Settings().recipeRemainder(BUCKET).maxCount(1).food(PinkFoodComponents.MILK_BUCKET)));
}
