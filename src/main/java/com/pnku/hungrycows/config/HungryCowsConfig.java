package com.pnku.hungrycows.config;

public class HungryCowsConfig {

    private float grassEatProbability;
    private int milkNutritionValue;
    private float milkSaturationModifier;
    public static HungryCowsConfig INSTANCE;
    public static int grassEatPriority = (int) Math.pow(2, 4 - getInstance().grassEatProbability);

    public HungryCowsConfig() {
        grassEatProbability = 1;
        milkNutritionValue = 6;
        milkSaturationModifier = 1.2f;
    }

    public static HungryCowsConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new HungryCowsConfig();
        }

        return INSTANCE;
    }

    public void setGrassEatProbability(float grassEatProbability) {
        this.grassEatProbability = grassEatProbability;
    }

    public void setMilkNutritionValue(int milkNutritionValue) {
        this.milkNutritionValue = milkNutritionValue;
    }

    public void setMilkSaturationModifier(float milkSaturationModifier) {
        this.milkSaturationModifier = milkSaturationModifier;
    }

    public float getGrassEatProbability() {
        return grassEatProbability;
    }

    public int getMilkNutritionValue() {
        return milkNutritionValue;
    }

    public float getMilkSaturationModifier() {
        return milkSaturationModifier;
    }

    public void updateConfigs(HungryCowsConfig config) {
        grassEatProbability = config.getGrassEatProbability();
        milkNutritionValue = config.getMilkNutritionValue();
        milkSaturationModifier = config.getMilkSaturationModifier();
    }
}