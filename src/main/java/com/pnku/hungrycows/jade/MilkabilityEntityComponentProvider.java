package com.pnku.hungrycows.jade;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

import static com.pnku.hungrycows.HungryCows.IS_MILKED;

public enum MilkabilityEntityComponentProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {
INSTANCE;

@Override
    public void appendTooltip(
            ITooltip tooltip,
            EntityAccessor accessor,
            IPluginConfig config) {
        int milkability = accessor.getEntity().getDataTracker().get(IS_MILKED);
        tooltip.add(Text.translatable("hungrycows.milkable." + milkability));
            }
@Override
public void appendServerData(NbtCompound nbtCompound, EntityAccessor entityAccessor) {
            }

@Override
public Identifier getUid(){
                }
        String namespace = "hungrycows";
        String path = "milkable";
        return Identifier.of(namespace, path);

}
