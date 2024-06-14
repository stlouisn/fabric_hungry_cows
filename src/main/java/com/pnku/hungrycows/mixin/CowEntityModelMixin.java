package com.pnku.hungrycows.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CowEntityModel.class)
public abstract class CowEntityModelMixin<T extends CowEntityMixin> extends QuadrupedEntityModel<T> {
    public CowEntityModelMixin(ModelPart root, boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, int childBodyYOffset) {
        super(root, headScaled, childHeadYOffset, childHeadZOffset, invertedChildHeadScale, invertedChildBodyScale, childBodyYOffset);
    }

    @Unique
    private float headAngle;

    public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
        super.animateModel(entity, limbAngle, limbDistance, tickDelta);
        this.head.pivotY = 6.0F + ((T) entity).getNeckAngle(tickDelta) * 9.0F;
        this.headAngle = ((T) entity).getHeadAngle(tickDelta);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.head.pitch = this.headAngle;
    }
}
