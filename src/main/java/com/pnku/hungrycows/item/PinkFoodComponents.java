package com.pnku.hungrycows.item;

import com.pnku.hungrycows.config.HungryCowsConfig;
import net.minecraft.component.type.FoodComponent;

public class PinkFoodComponents {
    public static final FoodComponent MILK_BUCKET = new FoodComponent.Builder().nutrition(HungryCowsConfig.getInstance().getMilkNutritionValue()).saturationModifier(HungryCowsConfig.getInstance().getMilkSaturationModifier()).alwaysEdible().build();
}
