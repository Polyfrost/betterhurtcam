# BetterHurtCam for Ornithe 1.8.9

Native Ornithe backport of [BetterHurtCam](https://modrinth.com/mod/betterhurtcam), based on upstream 1.14.0.

BetterHurtCam can disable the damage camera effect or adjust its multiplier. It also supplies the upstream toggle, increase, and decrease keybindings, health-bar blinking control, and a Mod Menu configuration screen.

## Requirements

- Minecraft 1.8.9
- Fabric Loader 0.19.3 or newer
- Ornithe Standard Libraries 0.20.3 or newer
- Mod Menu 0.5.0 or newer is optional, but provides the configuration screen

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

Use a JDK 17 or newer. On this machine, the tested command is:

```sh
JAVA_HOME="$HOME/.local/share/ElyPrismLauncher/java/java-runtime-epsilon" ./gradlew build
```

The release JAR is written to `build/libs/`.

## License

This backport retains the upstream [MIT license](LICENSE).
