package net.uku3lig.betterhurtcam.neoforge.mixin;

import net.minecraft.client.gui.Hud;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Hud.class)
public class MixinHud {
    @ModifyArg(method = "extractHealthLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Hud;extractHearts(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/world/entity/player/Player;IIIIFIIIZ)V"))
    public boolean renderHearts(boolean blinking) {
        return blinking && BetterHurtCam.getManager().getConfig().isHeartBlink();
    }
}