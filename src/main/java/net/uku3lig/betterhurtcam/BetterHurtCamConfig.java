package net.uku3lig.betterhurtcam;

import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.annotations.Dropdown;
import org.polyfrost.oneconfig.api.config.v1.annotations.Number;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;

/**
 * OneConfig presentation and persistence for the unchanged BetterHurtCam TOML schema.
 *
 * <p>The file ID intentionally includes {@code .toml}; OneConfig then reads and writes
 * the same {@code config/betterhurtcam.toml} file used by upstream BetterHurtCam.</p>
 */
public final class BetterHurtCamConfig extends Config {
	public static final BetterHurtCamConfig INSTANCE = new BetterHurtCamConfig();

	@Switch(title = "Enable HurtCam", category = "BetterHurtCam")
	public boolean enabled = true;

	@Number(
		title = "HurtCam multiplier",
		description = "Multiplies the damage camera rotation. Set to 0 to remove it.",
		category = "BetterHurtCam",
		min = -3.4028235E38f,
		max = 3.4028235E38f
	)
	public double multiplier = 0.3D;

	@Switch(title = "Health bar blinking", category = "BetterHurtCam")
	public boolean heartBlink = true;

	@Dropdown(
		title = "HurtCam type",
		description = "OLD removes horizontal damage-direction rotation.",
		category = "BetterHurtCam",
		options = {"OLD", "YAW_BASED"}
	)
	public String type = "YAW_BASED";

	private BetterHurtCamConfig() {
		super("betterhurtcam.toml", "assets/betterhurtcam/icon.png", "BetterHurtCam", Category.VISUALS);
	}

	public HurtCamType getType() {
		return HurtCamType.parse(type);
	}

	public void setType(HurtCamType type) {
		this.type = (type == null ? HurtCamType.YAW_BASED : type).name();
		save();
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		save();
	}

	public void setMultiplier(double multiplier) {
		if (Double.isFinite(multiplier)) {
			this.multiplier = multiplier;
			save();
		}
	}

	public void setHeartBlink(boolean heartBlink) {
		this.heartBlink = heartBlink;
		save();
	}
}
