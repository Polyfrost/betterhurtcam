package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.uku3lig.ukulib.config.option.StringTranslatable;

@Getter
@AllArgsConstructor
public enum HurtCamType implements StringTranslatable {
    OLD("old", "betterhurtcam.type.old"),
    YAW_BASED("yaw_based", "betterhurtcam.type.yawBased"),
    ;

    private final String name;
    private final String translationKey;
}
