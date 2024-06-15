package com.pnku.hungrycows.mixin;

import com.pnku.hungrycows.util.ICowEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CowEntityModel.class)
public abstract class CowEntityModelMixin<CowEntity extends net.minecraft.entity.passive.CowEntity> extends QuadrupedEntityModel<CowEntity> {
    public CowEntityModelMixin(ModelPart root, boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, int childBodyYOffset) {
        super(root, headScaled, childHeadYOffset, childHeadZOffset, invertedChildHeadScale, invertedChildBodyScale, childBodyYOffset);
    }

    @Unique
    private float headAngle;

    @Override
    public void animateModel(CowEntity cowEntity, float limbAngle, float limbDistance, float tickDelta) {
        super.animateModel(cowEntity, limbAngle, limbDistance, tickDelta);
        this.head.pivotY = 6.0F + ((ICowEntity) cowEntity).hungrycows$getNeckAngle(tickDelta) * 9.0F;
        this.headAngle = ((ICowEntity) cowEntity).hungrycows$getHeadAngle(tickDelta);
    }

    @Override
    public void setAngles(CowEntity cowEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setAngles(cowEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.head.pitch = this.headAngle;
    }
}
