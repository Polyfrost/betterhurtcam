package net.uku3lig.betterhurtcam.mixin;

import net.minecraft.client.gui.GameGui;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameGui.class)
public abstract class GameGuiMixin {
	@ModifyVariable(method = "renderStatusBars", at = @At(value = "STORE"), ordinal = 0)
	private boolean betterhurtcam$disableHealthBlink(boolean blinking) {
		return blinking && BetterHurtCam.isHeartBlinkEnabled();
	}
}
