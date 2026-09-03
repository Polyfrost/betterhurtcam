package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.HurtCamType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Inject(method = "applyHurtCam", at = @At("HEAD"), cancellable = true)
	private void betterhurtcam$disableHurtCam(float tickDelta, CallbackInfo ci) {
		if (!BetterHurtCam.isEnabled()) {
			ci.cancel();
		}
	}

	@Redirect(method = "applyHurtCam", at = @At(
		value = "INVOKE",
		target = "Lnet/minecraft/client/render/platform/GlStateManager;rotatef(FFFF)V"
	))
	private void betterhurtcam$modifyHurtCam(float angle, float x, float y, float z) {
		if (y != 0.0F && BetterHurtCam.getType() == HurtCamType.OLD) {
			angle = 0.0F;
		}
		GlStateManager.rotatef((float)(angle * BetterHurtCam.getMultiplier()), x, y, z);
	}
}
