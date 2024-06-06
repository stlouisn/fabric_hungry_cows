package com.pnku.hungrycows.jade;


import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import snownee.jade.api.EntityAccessor;
import snownee.jade.api.IEntityComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;


public enum MilkabilityEntityComponentProvider implements IEntityComponentProvider {
INSTANCE;
@Override
    public void appendTooltip(
            ITooltip tooltip,
            EntityAccessor accessor,
            IPluginConfig config
) {
            tooltip.append(Text.translatable("Milkable?"));
}
@Override
    public Identifier getUid(){
return new Identifier("hungrycows","com.pnku.hungrycows");
}
}
