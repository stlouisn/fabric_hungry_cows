package dev.hungrycows.mixin;

import dev.hungrycows.renderer.HungryCowRenderState;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.QuadrupedModel;
import org.spongepowered.asm.mixin.Mixin;

@SuppressWarnings("unused")
@Mixin(CowModel.class)
public abstract class CowModelMixin<Cow extends net.minecraft.world.entity.animal.Cow> extends QuadrupedModel<HungryCowRenderState> {

  public CowModelMixin(ModelPart root) {
    super(root);
  }

  @Override
  public void setupAnim(HungryCowRenderState hungryCowRenderState) {
    super.setupAnim(hungryCowRenderState);
    this.head.y = this.head.y + hungryCowRenderState.neckAngle * 9.0F * hungryCowRenderState.ageScale;
    this.head.xRot = hungryCowRenderState.headAngle;
  }
}
