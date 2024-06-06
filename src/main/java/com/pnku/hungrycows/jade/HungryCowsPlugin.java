package com.pnku.hungrycows.jade;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.CowEntity;
import snownee.jade.api.*;

@WailaPlugin
public class HungryCowsPlugin implements IWailaPlugin {

    @Override
    public void register(IWailaCommonRegistration registration) {
        //TODO register data providers
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        //TODO register component providers, icon providers, callbacks, and config options here
        registration.registerEntityComponent(MilkabilityEntityComponentProvider.INSTANCE, CowEntity.class);
        registration.addRayTraceCallback((hitResult, accessor, originalAccessor) -> {
            if (accessor instanceof EntityAccessor entityAccessor) {
                Entity entity = entityAccessor.getEntity();
                if (entity instanceof CowEntity) {

                    return registration.entityAccessor().from(entityAccessor).entity(entity).build(); // .build();
                }
            }
            return accessor;
        });
    }
}