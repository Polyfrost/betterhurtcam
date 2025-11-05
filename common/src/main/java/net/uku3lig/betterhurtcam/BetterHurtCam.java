package net.uku3lig.betterhurtcam;

import com.mojang.blaze3d.platform.InputConstants;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.uku3lig.betterhurtcam.config.BHCConfig;
import net.uku3lig.ukulib.Ukulib;
import net.uku3lig.ukulib.config.ConfigManager;
import org.lwjgl.glfw.GLFW;

public class BetterHurtCam {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath("betterhurtcam", "key"));

    private static final KeyMapping toggle = new KeyMapping("key.betterhurtcam.toggle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F8, CATEGORY);
    private static final KeyMapping plus = new KeyMapping("key.betterhurtcam.plus", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F7, CATEGORY);
    private static final KeyMapping minus = new KeyMapping("key.betterhurtcam.minus", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F6, CATEGORY);

    @Getter
    private static final ConfigManager<BHCConfig> manager = ConfigManager.createDefault(BHCConfig.class, "betterhurtcam");

    public static void onInitialize() {
        Ukulib.getUtils().registerToggleBind(toggle, () -> manager.getConfig().isEnabled(), b -> manager.getConfig().setEnabled(b), Component.literal("Hurtcam"));

        Ukulib.getUtils().registerKeybinding(plus, client -> {
            manager.getConfig().modifyMultiplier(0.1);
            manager.saveConfig();

            if (client.player != null) {
                client.player.displayClientMessage(getMultiplierText(), true);
            }
        });

        Ukulib.getUtils().registerKeybinding(minus, client -> {
            manager.getConfig().modifyMultiplier(-0.1);
            manager.saveConfig();

            if (client.player != null) {
                client.player.displayClientMessage(getMultiplierText(), true);
            }
        });
    }

    private static Component getMultiplierText() {
        String multValue = "%.2f".formatted(manager.getConfig().getMultiplier());
        Component multText = Component.literal(multValue).withStyle(ChatFormatting.BOLD, ChatFormatting.DARK_AQUA);
        return Component.literal("Hurtcam multiplier modified to ").append(multText);
    }
}
