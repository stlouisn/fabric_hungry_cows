package de.pnku.hungrycows.config;

public class HungryCowsConfig {

    private float grassEatProbability;
    private int milkNutritionValue;
    private float milkSaturationModifier;
    private static HungryCowsConfig INSTANCE;

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

    public int getGrassEatPriority() {
        int grassEatPriority = (int) Math.pow(2, 4 - getInstance().getGrassEatProbability());
        return grassEatPriority;
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