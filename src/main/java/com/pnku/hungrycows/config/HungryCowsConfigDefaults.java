package com.pnku.hungrycows.config;

import com.pnku.hungrycows.HungryCows;

public class HungryCowsConfigDefaults {

    public static float grassEatProbability;
    public static int milkNutritionValue;
    public static float milkSaturationModifier;


    public HungryCowsConfigDefaults() {
        grassEatProbability = 1;
        milkNutritionValue = 6;
        milkSaturationModifier = 1.2f;
    }
}
