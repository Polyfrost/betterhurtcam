package net.uku3lig.betterhurtcam.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.uku3lig.betterhurtcam.BetterHurtCam;
import net.uku3lig.betterhurtcam.config.BHCConfigScreen;

@Mod(value = "betterhurtcam", dist = Dist.CLIENT)
public class BetterHurtCamNeoForge {
    public BetterHurtCamNeoForge(ModContainer container) {
        BetterHurtCam.onInitialize();

        container.registerExtensionPoint(IConfigScreenFactory.class, (c, screen) -> new BHCConfigScreen(screen));
    }
}
