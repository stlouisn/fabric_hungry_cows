package com.pnku.hungrycows.item;

import net.minecraft.component.type.FoodComponent;

public class PinkFoodComponents {
    public static final FoodComponent MILK_BUCKET = new FoodComponent.Builder().nutrition(5).saturationModifier(0.8f).alwaysEdible().build();
}
