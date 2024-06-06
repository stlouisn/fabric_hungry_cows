package com.pnku.hungrycows.item;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.minecraft.item.Items.BUCKET;

public class PinkItems {
    public static final Item MILK_BUCKET = Registry.register(Registries.ITEM, "milk_bucket", (Item)new MilkBucketItem(new Item.Settings().recipeRemainder(BUCKET).maxCount(1).food(PinkFoodComponents.MILK_BUCKET)));
}
