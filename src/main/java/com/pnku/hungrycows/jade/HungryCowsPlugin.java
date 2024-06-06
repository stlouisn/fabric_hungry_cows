package com.pnku.hungrycows.jade;

import net.minecraft.entity.passive.CowEntity;
import snownee.jade.api.*;

@WailaPlugin
public class HungryCowsPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(MilkabilityEntityComponentProvider.INSTANCE, CowEntity.class);
    }
}