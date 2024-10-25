package dev.hungrycows;

import dev.hungrycows.utils.Constants;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.syncher.EntityDataAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HungryCows implements ModInitializer {

  @SuppressWarnings("unused")
  public static final Logger LOGGER = LoggerFactory.getLogger(Constants.MOD_ID);

  public static EntityDataAccessor<Byte> IS_MILKED;

  @Override
  public void onInitialize() {
  }
}