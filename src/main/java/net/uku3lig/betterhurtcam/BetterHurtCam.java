package net.uku3lig.betterhurtcam;

import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.options.KeyBinding;
import net.ornithemc.osl.entrypoints.api.client.ClientModInitializer;
import net.ornithemc.osl.keybinds.api.KeybindEvents;
import net.ornithemc.osl.keybinds.api.KeybindRegistry;
import net.ornithemc.osl.lifecycle.api.client.MinecraftClientEvents;
import org.lwjgl.input.Keyboard;

public final class BetterHurtCam implements ClientModInitializer {
	public static final String MOD_ID = "betterhurtcam";
	private static final BetterHurtCamConfig CONFIG = new BetterHurtCamConfig();
	private static final KeyBinding TOGGLE = new KeyBinding(
		"key.betterhurtcam.toggle", Keyboard.KEY_F8, "category.betterhurtcam");
	private static final KeyBinding INCREASE = new KeyBinding(
		"key.betterhurtcam.plus", Keyboard.KEY_F7, "category.betterhurtcam");
	private static final KeyBinding DECREASE = new KeyBinding(
		"key.betterhurtcam.minus", Keyboard.KEY_F6, "category.betterhurtcam");

	@Override
	public void initClient() {
		CONFIG.load();
		KeybindEvents.REGISTER_KEYBINDS.register(() -> {
			KeybindRegistry.register(TOGGLE);
			KeybindRegistry.register(INCREASE);
			KeybindRegistry.register(DECREASE);
		});
		MinecraftClientEvents.TICK_END.register(BetterHurtCam::handleKeybinds);
	}

	private static void handleKeybinds(Minecraft minecraft) {
		while (TOGGLE.consumeClick()) {
			CONFIG.setEnabled(!CONFIG.isEnabled());
			showStatus(minecraft, "HurtCam " + (CONFIG.isEnabled() ? "enabled" : "disabled"));
		}
		while (INCREASE.consumeClick()) {
			changeMultiplier(minecraft, 0.1D);
		}
		while (DECREASE.consumeClick()) {
			changeMultiplier(minecraft, -0.1D);
		}
	}

	private static void changeMultiplier(Minecraft minecraft, double delta) {
		CONFIG.setMultiplier(CONFIG.getMultiplier() + delta);
		showStatus(minecraft, "HurtCam multiplier: " + String.format(Locale.ROOT, "%.2f", CONFIG.getMultiplier()));
	}

	private static void showStatus(Minecraft minecraft, String message) {
		if (minecraft.player != null) {
			minecraft.gui.setOverlayMessage(message, false);
		}
	}

	public static boolean isEnabled() {
		return CONFIG.isEnabled();
	}

	public static void setEnabled(boolean enabled) {
		CONFIG.setEnabled(enabled);
	}

	public static double getMultiplier() {
		return CONFIG.getMultiplier();
	}

	public static void setMultiplier(double multiplier) {
		CONFIG.setMultiplier(multiplier);
	}

	public static boolean isHeartBlinkEnabled() {
		return CONFIG.isHeartBlink();
	}

	public static void setHeartBlinkEnabled(boolean heartBlink) {
		CONFIG.setHeartBlink(heartBlink);
	}

	public static HurtCamType getType() {
		return CONFIG.getType();
	}

	public static void setType(HurtCamType type) {
		CONFIG.setType(type);
	}
}
