package com.pnku.hungrycows;

import com.pnku.hungrycows.item.PinkFoodComponents;
import com.pnku.hungrycows.item.PinkItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Item;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.item.Items.BUCKET;

public class HungryCows implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("hungrycows");
	public static final Item MILK_BUCKET = Registry.register(Registries.ITEM, "milk_bucket", (Item)new MilkBucketItem(new Item.Settings().recipeRemainder(BUCKET).maxCount(1).food(PinkFoodComponents.MILK_BUCKET)));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Cows are hungry!");
	}
}