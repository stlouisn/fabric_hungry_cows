package com.pnku.hungrycows.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.text.Text;

import static com.pnku.hungrycows.config.HungryCowsConfigDefaults.*;

public class HungryCowsConfigScreen implements ModMenuApi {

    public static ConfigBuilder builder() {
        ConfigBuilder configBuilder = ConfigBuilder.create()
                .setTitle(Text.translatable("title.hungrycows.config"))
                .setEditable(true)
                .setSavingRunnable(() -> HungryCowsConfig.writeToConfig());

        ConfigCategory cow = configBuilder.getOrCreateCategory(Text.translatable("config.category.hungrycows.cow"));
        ConfigCategory milk = configBuilder.getOrCreateCategory(Text.translatable("config.category.hungrycows.milk"));

        cow.addEntry(configBuilder.entryBuilder()
                .startFloatField(Text.translatable("config.cow_option.hungrycows.grasseat_probability"), grassEatProbability)
                .setDefaultValue(1)
                        .setMin(0)
                        .setMax(3)
                .setSaveConsumer(val -> {HungryCowsConfig.INSTANCE.grassEatProbability = val;})
                .build());
        milk.addEntry(configBuilder.entryBuilder()
                .startIntField(Text.translatable("config.milk_option.hungrycows.milk_nutrition"), milkNutritionValue)
                .setDefaultValue(6)
                .setMin(0)
                .setMax(20)
                .setSaveConsumer(val -> {HungryCowsConfig.INSTANCE.milkNutritionValue = val;})
                .setTooltip(Text.translatable("config.milk_option.hungrycows.milk_nutrition.tooltip"))
                .build());
        milk.addEntry(configBuilder.entryBuilder()
                .startFloatField(Text.translatable("config.milk_option.hungrycows.milk_saturation"), milkSaturationModifier)
                .setDefaultValue(1.2f)
                .setMin(0.0f)
                .setMax(2.0f)
                .setSaveConsumer(val -> {HungryCowsConfig.INSTANCE.milkSaturationModifier = val;})
                .setTooltip(Text.translatable("config.milk_option.hungrycows.milk_saturation.tooltip"))
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