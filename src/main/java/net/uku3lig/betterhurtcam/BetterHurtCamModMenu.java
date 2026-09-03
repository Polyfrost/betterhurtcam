package net.uku3lig.betterhurtcam;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;
import org.polyfrost.oneconfig.api.config.v1.Config;

public final class BetterHurtCamModMenu implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return this::createScreen;
	}

	/**
	 * The platform helper is Kotlin and carries OneConfig's own mapping namespace
	 * in its method descriptor. Reflection prevents that platform-only descriptor
	 * from leaking into this independently remapped mod while still opening the
	 * native OneConfig page.
	 */
	private Screen createScreen(Screen parent) {
		try {
			Class<?> helper = Class.forName("org.polyfrost.oneconfig.utils.v1.dsl.ScreensKt");
			Object screen = helper.getMethod("createScreen", Config.class).invoke(null, BetterHurtCamConfig.INSTANCE);
			return (Screen) screen;
		} catch (ReflectiveOperationException | ClassCastException ignored) {
			return parent;
		}
	}
}
