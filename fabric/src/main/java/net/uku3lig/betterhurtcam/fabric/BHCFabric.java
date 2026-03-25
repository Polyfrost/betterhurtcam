package net.uku3lig.betterhurtcam.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.uku3lig.betterhurtcam.BetterHurtCam;

public class BHCFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BetterHurtCam.onInitialize();
    }
}
