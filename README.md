# BetterHurtCam for Ornithe 1.8.9

Native Ornithe backport of [BetterHurtCam](https://modrinth.com/mod/betterhurtcam), based on upstream 1.14.0.

BetterHurtCam can disable the damage camera effect or adjust its multiplier. It retains the upstream toggle, increase, and decrease keybindings and health-bar blinking control. Settings are presented through the native Ornithe 1.8.9 OneConfig port and remain stored in BetterHurtCam's upstream-compatible TOML file.

## Requirements

- Minecraft 1.8.9
- Fabric Loader and the current Ornithe runtime libraries
- OneConfig for Ornithe and its matching runtime dependencies
- Mod Menu is optional, but provides a direct Configure button

Dependency versions are maintained with the active Ornithe and OneConfig work. BetterHurtCam keeps OneConfig external and does not bundle or modify it.

## Configuration compatibility

The mod reads and writes `config/betterhurtcam.toml`, the same file and field names used by BetterHurtCam 1.14.0:

```toml
enabled = true
multiplier = 0.3
heartBlink = true
type = "YAW_BASED"
```

`type` accepts both the current enum form (`YAW_BASED` / `OLD`) and the previous lowercase identifiers. On 1.8.9, `OLD` removes the horizontal damage-direction rotations and `YAW_BASED` preserves them.


## License

This backport retains the upstream [MIT license](LICENSE).
