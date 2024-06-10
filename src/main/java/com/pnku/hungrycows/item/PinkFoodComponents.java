package com.pnku.hungrycows.item;

import com.pnku.hungrycows.config.HungryCowsConfigJsonHelper;
import net.minecraft.component.type.FoodComponent;

public class PinkFoodComponents {
    public static final FoodComponent MILK_BUCKET = new FoodComponent.Builder().nutrition(HungryCowsConfigJsonHelper.INSTANCE.getMilkNutritionValue()).saturationModifier(HungryCowsConfigJsonHelper.INSTANCE.getMilkSaturationModifier()).alwaysEdible().build();
}
