package dev.hungrycows.mixin;

import dev.hungrycows.implementations.ICowEntity;
import dev.hungrycows.renderer.HungryCowRenderState;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.CowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.animal.Cow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@SuppressWarnings("deprecation")
@Mixin(CowRenderer.class)
public abstract class CowRendererMixin extends AgeableMobRenderer<Cow, HungryCowRenderState, CowModel> implements ICowEntity {
  public CowRendererMixin(EntityRendererProvider.Context context) {
    super(context, new CowModel(context.bakeLayer(ModelLayers.COW)), new CowModel(context.bakeLayer(ModelLayers.COW_BABY)), 0.7F);
  }

  @Inject(method = "createRenderState()Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;", at = @At("HEAD"), cancellable = true)
  public void injectedCreateRenderState(CallbackInfoReturnable<HungryCowRenderState> cir) {
    cir.setReturnValue(new HungryCowRenderState());
  }

  @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/animal/Cow;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("HEAD"))
  public void injectedExtractRenderState(Cow cow, LivingEntityRenderState livingEntityRenderState, float f, CallbackInfo ci) {
    HungryCowRenderState hungryCowRenderState = (HungryCowRenderState) livingEntityRenderState;
    super.extractRenderState(cow, hungryCowRenderState, f);
    hungryCowRenderState.headAngle = ((ICowEntity) cow).hungrycows$getHeadAngle(f);
    hungryCowRenderState.neckAngle = ((ICowEntity) cow).hungrycows$getNeckAngle(f);
  }
}
