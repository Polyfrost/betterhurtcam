package net.uku3lig.betterhurtcam;

import net.fabricmc.loader.api.FabricLoader;
import org.polyfrost.oneconfig.api.config.v1.Config;
import org.polyfrost.oneconfig.api.config.v1.Property;
import org.polyfrost.oneconfig.api.config.v1.annotations.Dropdown;
import org.polyfrost.oneconfig.api.config.v1.annotations.Include;
import org.polyfrost.oneconfig.api.config.v1.annotations.Number;
import org.polyfrost.oneconfig.api.config.v1.annotations.Switch;
import org.polyfrost.overflowanimations.config.OverflowAnimationsConfig;

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
	public double multiplier = 1.0D;

	@Switch(
		title = "Health bar blinking",
		description = "Has no effect while OverflowAnimations' Heart Flashing is set to 1.7, as that option takes over.",
		category = "BetterHurtCam"
	)
	public boolean heartBlink = true;

	@Dropdown(
		title = "HurtCam type",
		description = "OLD removes horizontal damage-direction rotation.",
		category = "BetterHurtCam",
		options = {"OLD", "YAW_BASED"}
	)
	public String type = "YAW_BASED";

	@Switch(
		title = "Use OLD HurtCam type",
		description = "Forces the OLD type on 1.8 regardless of the HurtCam type above. Only exists on 1.8.",
		category = "BetterHurtCam"
	)
	public boolean oldTypeIn18 = true;

	// Files without this key predate the vanilla defaults and get reset once.
	@Include
	public int configVersion = 0;
	private static final int CONFIG_VERSION = 1;

	private static final boolean OVERFLOW_ANIMATIONS = FabricLoader.getInstance().isModLoaded("overflowanimations");
	private boolean heartBlinkOverridden;

	private BetterHurtCamConfig() {
		super("betterhurtcam.toml", "assets/betterhurtcam/icon.png", "BetterHurtCam", Category.VISUALS);
		addDependency("heartBlink", "OverflowAnimations' Heart Flashing being 1.8+",
			() -> heartBlinkOverridden ? Property.Display.DISABLED : Property.Display.SHOWN);
	}

	@Override
	protected void initialize(boolean byConfigManager) {
		super.initialize(byConfigManager);
		if (configVersion < CONFIG_VERSION) {
			enabled = true;
			multiplier = 1.0D;
			heartBlink = true;
			type = HurtCamType.YAW_BASED.name();
			oldTypeIn18 = true;
			configVersion = CONFIG_VERSION;
			save();
		}
	}

	void refreshHeartBlinkOverride() {
		boolean overridden = OVERFLOW_ANIMATIONS && OverflowAnimationsConfig.instance().screen.healthBarFlashing.isOld();
		if (overridden != heartBlinkOverridden) {
			heartBlinkOverridden = overridden;
			getProperty("heartBlink").revaluateDisplay();
		}
	}

	public HurtCamType getType() {
		return oldTypeIn18 ? HurtCamType.OLD : HurtCamType.parse(type);
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
