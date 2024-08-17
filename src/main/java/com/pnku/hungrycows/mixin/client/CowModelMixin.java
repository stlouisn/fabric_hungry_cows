package com.pnku.hungrycows.mixin.client;

import com.pnku.hungrycows.util.ICowEntity;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.QuadrupedModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CowModel.class)
public abstract class CowModelMixin<Cow extends net.minecraft.world.entity.animal.Cow> extends QuadrupedModel<Cow> {
    public CowModelMixin(ModelPart root, boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, int childBodyYOffset) {
        super(root, headScaled, childHeadYOffset, childHeadZOffset, invertedChildHeadScale, invertedChildBodyScale, childBodyYOffset);
    }

    @Unique
    private float headAngle;

    @Override
    public void prepareMobModel(Cow cowEntity, float limbAngle, float limbDistance, float tickDelta) {
        super.prepareMobModel(cowEntity, limbAngle, limbDistance, tickDelta);
        this.head.y = 6.0F + ((ICowEntity) cowEntity).hungrycows$getNeckAngle(tickDelta) * 9.0F;
        this.headAngle = ((ICowEntity) cowEntity).hungrycows$getHeadAngle(tickDelta);
    }

    @Override
    public void setupAnim(Cow cowEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        super.setupAnim(cowEntity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
        this.head.xRot = this.headAngle;
    }
}
