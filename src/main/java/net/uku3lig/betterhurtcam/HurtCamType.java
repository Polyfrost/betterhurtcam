package net.uku3lig.betterhurtcam;

/** Matches the type values stored by current BetterHurtCam releases. */
public enum HurtCamType {
	OLD,
	YAW_BASED;

	public static HurtCamType parse(String value) {
		if (value == null) {
			return YAW_BASED;
		}

		String normalized = value.trim();
		if (normalized.length() >= 2 && normalized.startsWith("\"") && normalized.endsWith("\"")) {
			normalized = normalized.substring(1, normalized.length() - 1);
		}
		normalized = normalized.replace('-', '_').toUpperCase(java.util.Locale.ROOT);
		return "OLD".equals(normalized) ? OLD : YAW_BASED;
	}

	public HurtCamType next() {
		return this == OLD ? YAW_BASED : OLD;
	}
}
