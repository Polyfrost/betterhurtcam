# Compatibility and test profile

The release JAR is installed in the isolated PineconeMC instance:

`~/.local/share/ElyPrismLauncher/instances/PineconeMC BetterHurtCam 1.8.9/minecraft/mods/`

It is a copy of `CountsPack GEN II`; the original profile has not been changed.

## Reviewed active mods

The test profile contains Argentum, Argentum Extras, Cera, Count's Glue, Lenis, Mod Menu, NetFix, Ornithe Standard Libraries, SoundFix, and Zirconium. OneConfig and its required libraries are added only to this copied profile for BetterHurtCam validation.

BetterHurtCam only injects into `GameRenderer.applyHurtCam` and the temporary health-blink local in `GameGui.renderStatusBars`. Count's Glue injects different `GameRenderer` and `GameGui` methods, and Zirconium's optional GameGui font-batching mixin is not active. OneConfig is kept as a separate JAR and BetterHurtCam bundles none of its classes or dependencies.

The copied profile completed a headless Xvfb startup audit with all reviewed mods discovered by Fabric Loader:

- Argentum, Argentum Extras, Cera, NetFix, SoundFix, and Zirconium have no direct BetterHurtCam target-method overlap.
- Count's Glue shares the affected renderer and HUD classes but injects different methods.
- Lenis and its legacy-LWJGL3 shim are required by OneConfig and loaded successfully.
- Mod Menu registers BetterHurtCam's own OneConfig-backed configuration factory.
- The upgraded OSL aggregate satisfies both the original mods and OneConfig's newer component requirements.

The verified OneConfig CI artifact currently declares a `oneconfigv1` Mod Menu entrypoint class that is absent from that artifact. Mod Menu logs and skips that external OneConfig entrypoint but continues startup; BetterHurtCam does not use it. This is an upstream OneConfig packaging issue, not a bundled or modified dependency in this project.

Axolotl Client and Axolotl OldAnimations are intentionally excluded from this validation, as requested. Their JARs are preserved in the copied profile at:

`minecraft/mods-backups/betterhurtcam-excluded-mods-20260903/`

The profile's stale Ornithe V2 remapping cache was also preserved under `minecraft/mods-backups/` so the next launch remaps the active mod list cleanly.

## First launch checks

Launch `PineconeMC BetterHurtCam 1.8.9` from PineconeMC. The first launch should create `minecraft/config/betterhurtcam.toml` with the upstream-compatible keys.

1. Open Mod Menu and confirm BetterHurtCam has a working Configure button backed by OneConfig.
2. Confirm F8 toggles hurtcam, F7 increases strength, and F6 decreases strength.
3. Set multiplier to `0.0` and take damage; the hurt rotation should disappear while the mod remains enabled.
4. Toggle health-bar blinking and verify damage does or does not blink hearts accordingly.
5. Set `type = "OLD"` in `config/betterhurtcam.toml`, restart, and verify the horizontal damage-direction rotation is removed.

The automated audit confirmed loader discovery, OneConfig initialization, and creation of:

```toml
heartBlink = true
multiplier = 0.3
type = "YAW_BASED"
enabled = true
```

It also exercised OneConfig's TOML serializer directly for reads of the legacy `type = "old"` form and writes of the four upstream fields. The remaining checks require an interactive game session.
