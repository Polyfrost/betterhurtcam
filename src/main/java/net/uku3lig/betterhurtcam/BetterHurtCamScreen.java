package net.uku3lig.betterhurtcam;

import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

final class BetterHurtCamScreen extends Screen {
	private static final int ENABLED = 0;
	private static final int DECREASE = 1;
	private static final int INCREASE = 2;
	private static final int HEART_BLINK = 3;
	private static final int TYPE = 4;
	private static final int DONE = 5;
	private final Screen parent;

	BetterHurtCamScreen(Screen parent) {
		this.parent = parent;
	}

	@Override
	public void init() {
		int x = width / 2 - 100;
		int y = height / 2 - 76;
		buttons.add(new ButtonWidget(ENABLED, x, y, 200, 20, enabledMessage()));
		buttons.add(new ButtonWidget(DECREASE, x, y + 26, 98, 20, "Decrease strength"));
		buttons.add(new ButtonWidget(INCREASE, x + 102, y + 26, 98, 20, "Increase strength"));
		buttons.add(new ButtonWidget(HEART_BLINK, x, y + 52, 200, 20, heartBlinkMessage()));
		buttons.add(new ButtonWidget(TYPE, x, y + 78, 200, 20, typeMessage()));
		buttons.add(new ButtonWidget(DONE, x, y + 112, 200, 20, "Done"));
	}

	@Override
	public void render(int mouseX, int mouseY, float delta) {
		renderBackground();
		drawCenteredString(textRenderer, "BetterHurtCam", width / 2, height / 2 - 110, 0xFFFFFF);
		drawCenteredString(textRenderer, "Multiplier: " + String.format(Locale.ROOT, "%.2f", BetterHurtCam.getMultiplier()),
			width / 2, height / 2 - 95, 0xA0A0A0);
		super.render(mouseX, mouseY, delta);
	}

	@Override
	protected void buttonClicked(ButtonWidget button) {
		if (button.id == ENABLED) {
			BetterHurtCam.setEnabled(!BetterHurtCam.isEnabled());
		} else if (button.id == DECREASE) {
			BetterHurtCam.setMultiplier(BetterHurtCam.getMultiplier() - 0.1D);
		} else if (button.id == INCREASE) {
			BetterHurtCam.setMultiplier(BetterHurtCam.getMultiplier() + 0.1D);
		} else if (button.id == HEART_BLINK) {
			BetterHurtCam.setHeartBlinkEnabled(!BetterHurtCam.isHeartBlinkEnabled());
		} else if (button.id == TYPE) {
			BetterHurtCam.setType(BetterHurtCam.getType().next());
		} else if (button.id == DONE) {
			minecraft.openScreen(parent);
			return;
		}
		minecraft.openScreen(new BetterHurtCamScreen(parent));
	}

	private static String enabledMessage() {
		return "HurtCam: " + (BetterHurtCam.isEnabled() ? "ON" : "OFF");
	}

	private static String heartBlinkMessage() {
		return "Health bar blinking: " + (BetterHurtCam.isHeartBlinkEnabled() ? "ON" : "OFF");
	}

	private static String typeMessage() {
		return "HurtCam type: " + (BetterHurtCam.getType() == HurtCamType.OLD ? "Old" : "Yaw-based");
	}
}
