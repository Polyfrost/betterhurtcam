package net.uku3lig.betterhurtcam.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @ModifyReturnValue(method = "getDamageTiltYaw", at = @At("RETURN"))
    public float changeHurtCamType(float original) {
        return switch (BetterHurtCam.getManager().getConfig().getType()) {
            case OLD -> 0;
            case YAW_BASED -> original;
        };
    }
}
