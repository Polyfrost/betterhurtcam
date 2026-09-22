plugins {
	java
	id("net.fabricmc.fabric-loom-remap") version "1.17.+"
	id("ploceus") version "1.17.+"
}

group = "net.uku3lig.betterhurtcam"
version = "1.14.0+mc1.8.9-ornithe"
base.archivesName = "BetterHurtCam"

val oneConfigVersion = "1.2.3"

repositories {
	maven("https://moehreag.duckdns.org/maven/releases")
	maven("https://repo.polyfrost.org/releases")
	mavenCentral()
}

ploceus {
	setIntermediaryGeneration(2)
}

loom {
	mods {
		create("betterhurtcam") {
			sourceSet("main")
		}
	}
	runs {
		remove(getByName("server"))
	}
}

dependencies {
	minecraft("com.mojang:minecraft:1.8.9")
	mappings(ploceus.featherMappings("1"))

	modImplementation("net.fabricmc:fabric-loader:0.19.3")
	ploceus.dependOsl("0.21.0")
	modImplementation("com.terraformersmc:modmenu:0.5.0+mc1.8.9")
	compileOnly("org.polyfrost.oneconfig:config-impl:$oneConfigVersion@jar")
	compileOnly("org.polyfrost.oneconfig:config:$oneConfigVersion@jar")
}

tasks.processResources {
	inputs.property("version", version)
	filesMatching("fabric.mod.json") {
		expand("version" to version)
	}
}

tasks.withType<JavaCompile>().configureEach {
	options.encoding = "UTF-8"
	options.release = 21
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
	withSourcesJar()
}
