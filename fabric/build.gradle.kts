plugins {
    id("multiloader-platform")
    id("fabric-loom")
}

dependencies {
    minecraft("com.mojang:minecraft:${BuildConfig.MINECRAFT_VERSION}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${BuildConfig.FABRIC_LOADER_VERSION}")

    modImplementation("net.uku3lig:ukulib-fabric:${BuildConfig.UKULIB_VERSION}")
}

tasks.remapJar {
    destinationDirectory.set(file(rootProject.layout.buildDirectory).resolve("libs"))
}