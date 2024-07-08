package com.pnku.hungrycows.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.network.chat.Component;

public class HungryCowsConfigScreen implements ModMenuApi {

    public static ConfigBuilder builder() {
        ConfigBuilder configBuilder = ConfigBuilder.create()
                .setTitle(Component.translatable("title.hungrycows.config"))
                .setEditable(true)
                .setSavingRunnable(() -> HungryCowsConfigJsonHelper.writeToConfig());

        ConfigCategory cow = configBuilder.getOrCreateCategory(Component.translatable("config.category.hungrycows.cow"));
        ConfigCategory milk = configBuilder.getOrCreateCategory(Component.translatable("config.category.hungrycows.milk"));

        cow.addEntry(configBuilder.entryBuilder()
                .startFloatField(Component.translatable("config.cow_option.hungrycows.grasseat_probability"), HungryCowsConfig.getInstance().getGrassEatProbability())
                .setDefaultValue(1)
                        .setMin(0)
                        .setMax(3)
                .setSaveConsumer(grassEatProbabilityFloat -> {
                    HungryCowsConfig.getInstance().setGrassEatProbability(grassEatProbabilityFloat);})
                .setTooltip(Component.translatable("config.cow_option.hungrycows.grasseat_probability"))
                .build());
        milk.addEntry(configBuilder.entryBuilder()
                .startIntField(Component.translatable("config.milk_option.hungrycows.milk_nutrition"), HungryCowsConfig.getInstance().getMilkNutritionValue())
                .setDefaultValue(6)
                .setMin(0)
                .setMax(20)
                .setSaveConsumer(milkNutritionValueInt -> {
                    HungryCowsConfig.getInstance().setMilkNutritionValue(milkNutritionValueInt);})
                .setTooltip(Component.translatable("config.milk_option.hungrycows.milk_nutrition.tooltip"))
                .build());
        milk.addEntry(configBuilder.entryBuilder()
                .startFloatField(Component.translatable("config.milk_option.hungrycows.milk_saturation"), HungryCowsConfig.getInstance().getMilkSaturationModifier())
                .setDefaultValue(1.2f)
                .setMin(0.0f)
                .setMax(2.0f)
                .setSaveConsumer(milkSaturationModifierFloat -> {
                    HungryCowsConfig.getInstance().setMilkSaturationModifier(milkSaturationModifierFloat);})
                .setTooltip(Component.translatable("config.milk_option.hungrycows.milk_saturation.tooltip"))
                .build());
        return configBuilder;

    }

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
           return builder().setParentScreen(parent).build();
        };
    }
}