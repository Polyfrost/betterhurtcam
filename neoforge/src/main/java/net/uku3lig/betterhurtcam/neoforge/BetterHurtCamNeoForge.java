package net.uku3lig.betterhurtcam.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;
import net.uku3lig.betterhurtcam.BetterHurtCam;

@Mod(value = "betterhurtcam", dist = Dist.CLIENT)
public class BetterHurtCamNeoForge {
    public BetterHurtCamNeoForge() {
        BetterHurtCam.onInitialize();
    }
}
