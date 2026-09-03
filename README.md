# BetterHurtCam for Ornithe 1.8.9

Native Ornithe backport of [BetterHurtCam](https://modrinth.com/mod/betterhurtcam), based on upstream 1.14.0.

BetterHurtCam can disable the damage camera effect or adjust its multiplier. It retains the upstream toggle, increase, and decrease keybindings and health-bar blinking control. Settings are presented through the native Ornithe 1.8.9 OneConfig port and remain stored in BetterHurtCam's upstream-compatible TOML file.

## Requirements

- Minecraft 1.8.9
- Fabric Loader 0.19.3 or newer
- OneConfig `1.1.12` for Ornithe 1.8.9, built from [`Polyfrost/OneConfig` `legacy` commit `077ff616`](https://github.com/Polyfrost/OneConfig/tree/077ff616455e3b67d1a117feb5d9d25e7d57a2ef)
- The matching Compose Multiplatform bundle and Fabric Language Kotlin version required by that OneConfig build
- OSL `0.21.0-alpha.34` components required by that OneConfig build
- Mod Menu 0.5.0 or newer is optional, but provides a direct Configure button for the OneConfig screen

The OneConfig dependencies are external libraries; BetterHurtCam does not bundle, modify, or redistribute them.

## Configuration compatibility

The mod reads and writes `config/betterhurtcam.toml`, the same file and field names used by BetterHurtCam 1.14.0:

```toml
enabled = true
multiplier = 0.3
heartBlink = true
type = "YAW_BASED"
```

`type` accepts both the current enum form (`YAW_BASED` / `OLD`) and the previous lowercase identifiers. On 1.8.9, `OLD` removes the horizontal damage-direction rotations and `YAW_BASED` preserves them.

## Build

Use a JDK 17 or newer and an authenticated [GitHub CLI](https://cli.github.com/). The build downloads the exact OneConfig CI artifact above, verifies its published SHA-256, and uses its embedded API only for compilation. On this machine, the tested command is:

```sh
JAVA_HOME="$HOME/.local/share/ElyPrismLauncher/java/java-runtime-epsilon" ./gradlew build
```

The release JAR is written to `build/libs/`.

See [COMPATIBILITY.md](COMPATIBILITY.md) for the reviewed PineconeMC test profile and first-launch checks.

## License

This backport retains the upstream [MIT license](LICENSE).
