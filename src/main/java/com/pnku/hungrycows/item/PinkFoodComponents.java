package com.pnku.hungrycows.item;

import net.minecraft.component.type.FoodComponent;
import static com.pnku.hungrycows.config.HungryCowsConfigDefaults.*;

public class PinkFoodComponents {
    public static final FoodComponent MILK_BUCKET = new FoodComponent.Builder().nutrition(milkNutritionValue).saturationModifier(milkSaturationModifier).alwaysEdible().build();
}
