package net.uku3lig.betterhurtcam.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.util.OptionEnum;

@Getter
@AllArgsConstructor
public enum HurtCamType implements OptionEnum {
    OLD(0, "betterhurtcam.type.old"),
    YAW_BASED(1, "betterhurtcam.type.yawBased"),
    ;

    private final int id;
    private final String key;
}
