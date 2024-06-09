package com.pnku.hungrycows;

import com.pnku.hungrycows.config.HungryCowsConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.data.TrackedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.pnku.hungrycows.config.HungryCowsConfigDefaults.grassEatProbability;

public class HungryCows implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("hungrycows");
	public static TrackedData<Byte> IS_MILKED;
	public static int grassEatPriority = (int) Math.pow(2, 4 - grassEatProbability);

	@Override
	public void onInitialize() {
		LOGGER.info("Cows are hungry!");
		HungryCowsConfig.init();
	}
}