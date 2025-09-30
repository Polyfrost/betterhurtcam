package net.uku3lig.betterhurtcam;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.config.ConfigManager;
import net.uku3lig.ukulib.utils.Ukutils;
import org.lwjgl.glfw.GLFW;

public class BetterHurtCam implements ModInitializer {
    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(Identifier.of("betterhurtcam", "key"));

    private static final KeyBinding toggle = new KeyBinding("key.betterhurtcam.toggle", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, CATEGORY);
    private static final KeyBinding plus = new KeyBinding("key.betterhurtcam.plus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F7, CATEGORY);
    private static final KeyBinding minus = new KeyBinding("key.betterhurtcam.minus", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F6, CATEGORY);

    @Getter
    private static final ConfigManager<BHCConfig> manager = ConfigManager.createDefault(BHCConfig.class, "betterhurtcam");


    @Override
    public void onInitialize() {
        Ukutils.registerToggleBind(toggle, () -> manager.getConfig().isEnabled(), b -> manager.getConfig().setEnabled(b), Text.literal("Hurtcam"));

        Ukutils.registerKeybinding(plus, client -> {
            manager.getConfig().modifyMultiplier(0.1);
            manager.saveConfig();

            if (client.player != null) {
                client.player.sendMessage(getMultiplierText(), true);
            }
        });

        Ukutils.registerKeybinding(minus, client -> {
            manager.getConfig().modifyMultiplier(-0.1);
            manager.saveConfig();

            if (client.player != null) {
                client.player.sendMessage(getMultiplierText(), true);
            }
        });
    }

    private Text getMultiplierText() {
        String multValue = "%.2f".formatted(manager.getConfig().getMultiplier());
        Text multText = Text.literal(multValue).formatted(Formatting.BOLD, Formatting.DARK_AQUA);
        return Text.literal("Hurtcam multiplier modified to ").append(multText);
    }
}
