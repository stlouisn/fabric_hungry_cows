package de.pnku.hungrycows.jade;

import net.minecraft.world.entity.animal.Cow;
import snownee.jade.api.*;

@WailaPlugin
public class HungryCowsPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(MilkabilityEntityComponentProvider.INSTANCE, Cow.class);
    }
}