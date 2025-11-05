package net.uku3lig.betterhurtcam.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.UkulibIntegration;
import net.uku3lig.ukulib.neoforge.UkulibNFProvider;

@Mod(value = "betterhurtcam", dist = Dist.CLIENT)
public class BetterHurtCamNeoForge {
    public BetterHurtCamNeoForge(ModContainer container) {
        BetterHurtCam.onInitialize();

        container.registerExtensionPoint(UkulibNFProvider.class, UkulibIntegration::new);
    }
}
