package de.pnku.hungrycows.jade;


import de.pnku.hungrycows.HungryCows;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;


public enum MilkabilityEntityComponentProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
INSTANCE;

@Override
    public void appendTooltip(
            ITooltip tooltip,
            EntityAccessor accessor,
            IPluginConfig config) {
        int milkability = accessor.getEntity().getEntityData().get(HungryCows.IS_MILKED);
        tooltip.add(Component.translatable("hungrycows.milkable." + milkability));
            }
@Override
public void appendServerData(CompoundTag nbtCompound, EntityAccessor entityAccessor) {
            }

@Override
public ResourceLocation getUid(){
    return new ResourceLocation("hungrycows","milkable");
                }

}
