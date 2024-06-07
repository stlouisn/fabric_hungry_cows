package com.pnku.hungrycows;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.data.TrackedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.item.Items.BUCKET;

public class HungryCows implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hungrycows");
	public static TrackedData<Byte> IS_MILKED;

	@Override
	public void onInitialize() {
		LOGGER.info("Cows are hungry!");
	}
}