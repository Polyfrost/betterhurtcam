package net.uku3lig.betterhurtcam;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import net.fabricmc.loader.api.FabricLoader;

/**
 * Small TOML reader/writer for the stable BetterHurtCam configuration schema.
 *
 * <p>The upstream library writes only four scalar values, so keeping this local
 * avoids adding a config-library dependency to an otherwise small legacy mod.</p>
 */
final class BetterHurtCamConfig {
	private final Path path = FabricLoader.getInstance().getConfigDir().resolve("betterhurtcam.toml");
	private boolean enabled = true;
	private double multiplier = 0.3D;
	private boolean heartBlink = true;
	private HurtCamType type = HurtCamType.YAW_BASED;

	void load() {
		if (!Files.isRegularFile(path)) {
			save();
			return;
		}

		try {
			for (String line : Files.readAllLines(path, StandardCharsets.UTF_8)) {
				readLine(line);
			}
		} catch (IOException ignored) {
			// Keep upstream defaults when a local file cannot be read.
		}
	}

	boolean isEnabled() {
		return enabled;
	}

	void setEnabled(boolean enabled) {
		this.enabled = enabled;
		save();
	}

	double getMultiplier() {
		return multiplier;
	}

	void setMultiplier(double multiplier) {
		if (Double.isFinite(multiplier)) {
			this.multiplier = multiplier;
			save();
		}
	}

	boolean isHeartBlink() {
		return heartBlink;
	}

	void setHeartBlink(boolean heartBlink) {
		this.heartBlink = heartBlink;
		save();
	}

	HurtCamType getType() {
		return type;
	}

	void setType(HurtCamType type) {
		this.type = type == null ? HurtCamType.YAW_BASED : type;
		save();
	}

	private void readLine(String line) {
		int equals = line.indexOf('=');
		if (equals < 1) {
			return;
		}

		String key = line.substring(0, equals).trim();
		String value = withoutComment(line.substring(equals + 1)).trim();
		if ("enabled".equals(key)) {
			enabled = parseBoolean(value, enabled);
		} else if ("multiplier".equals(key)) {
			try {
				double parsed = Double.parseDouble(value);
				if (Double.isFinite(parsed)) {
					multiplier = parsed;
				}
			} catch (NumberFormatException ignored) {
				// Keep the last valid value.
			}
		} else if ("heartBlink".equals(key)) {
			heartBlink = parseBoolean(value, heartBlink);
		} else if ("type".equals(key)) {
			type = HurtCamType.parse(value);
		}
	}

	private static String withoutComment(String value) {
		boolean quoted = false;
		for (int index = 0; index < value.length(); index++) {
			char character = value.charAt(index);
			if (character == '\"') {
				quoted = !quoted;
			} else if (character == '#' && !quoted) {
				return value.substring(0, index);
			}
		}
		return value;
	}

	private static boolean parseBoolean(String value, boolean fallback) {
		if ("true".equalsIgnoreCase(value)) {
			return true;
		}
		if ("false".equalsIgnoreCase(value)) {
			return false;
		}
		return fallback;
	}

	private void save() {
		List<String> lines = List.of(
			"enabled = " + enabled,
			"multiplier = " + Double.toString(multiplier),
			"heartBlink = " + heartBlink,
			"type = \"" + type.name() + "\""
		);
		try {
			Files.createDirectories(path.getParent());
			Files.write(path, lines, StandardCharsets.UTF_8);
		} catch (IOException ignored) {
			// The in-memory change remains effective for this session.
		}
	}
}
