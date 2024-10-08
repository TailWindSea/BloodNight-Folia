plugins {
    id("de.eldoria.library-conventions")
    id("com.gradleup.shadow") version "8.3.0"
}

dependencies {
    implementation(project(":BloodNight-api"))
    implementation("de.eldoria", "eldo-util", "1.10.2-SNAPSHOT")
    implementation("net.kyori", "adventure-platform-bukkit", "4.3.1")
    testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.10.1")
    testImplementation("junit", "junit", "4.13.2")
    testImplementation("org.mockito", "mockito-core", "5.7.0")
    compileOnly("io.lumine", "Mythic-Dist", "5.4.1")
    compileOnly("me.clip", "placeholderapi", "2.11.5")
    compileOnly("com.onarandombox.multiversecore", "Multiverse-Core", "4.3.1")
    compileOnly("se.hyperver.hyperverse", "Core", "0.10.0")
}

configurations {
    all {
        resolutionStrategy{
            cacheChangingModulesFor(0, "SECONDS")
        }
    }
}

description = "BloodNight-core"
val shadebase = project.group as String + ".bloodnight."

java {
    withJavadocJar()
}

publishData {
    addBuildData()
    useEldoNexusRepos()
    publishComponent("java")
}

tasks {
    shadowJar {
        relocate("de.eldoria.eldoutilities", shadebase + "eldoutilities")
        relocate("net.kyori", shadebase + "kyori")
        mergeServiceFiles()
        archiveBaseName.set(project.parent?.name)
    }

    processResources {
        from(sourceSets.main.get().resources.srcDirs) {
            filesMatching("plugin.yml") {
                expand(
                    "version" to publishData.getVersion(true)
                )
            }
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }

    build{
        dependsOn(shadowJar)
    }

        register<Copy>("copyToServer") {
        val path = project.property("targetDir") ?: "";
        if (path.toString().isEmpty()) {
            println("targetDir is not set in gradle properties")
            return@register
        }
        from(shadowJar)
        destinationDir = File(path.toString())
    }
}
