package net.uku3lig.betterhurtcam;

import net.minecraft.client.gui.screens.Screen;
import net.uku3lig.betterhurtcam.config.BHCConfigScreen;
import net.uku3lig.ukulib.api.UkulibAPI;

import java.util.function.UnaryOperator;

public class UkulibIntegration implements UkulibAPI {
    @Override
    public UnaryOperator<Screen> supplyConfigScreen() {
        return BHCConfigScreen::new;
    }
}
