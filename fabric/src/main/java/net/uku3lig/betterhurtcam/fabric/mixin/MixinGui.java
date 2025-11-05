package net.uku3lig.betterhurtcam.fabric.mixin;

import net.minecraft.client.gui.Gui;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Gui.class)
public class MixinGui {
    @ModifyArg(method = "renderPlayerHealth", index = 10, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHearts(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/entity/player/Player;IIIIFIIIZ)V"))
    public boolean renderHearts(boolean blinking) {
        return blinking && BetterHurtCam.getManager().getConfig().isHeartBlink();
    }
}
