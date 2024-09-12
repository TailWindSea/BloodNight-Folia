import de.chojo.PublishData

plugins {
    java
    alias(libs.plugins.publishdata)
}

group = "de.eldoria"
version = "0.12.0"

subprojects {
    apply {
        plugin<PublishData>()
        plugin<MavenPublishPlugin>()
        plugin<JavaLibraryPlugin>()
    }

    repositories {
        mavenCentral()
        maven("https://repo.spongepowered.org/maven")
        maven("https://repo1.maven.org/maven2/")
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://eldonexus.de/repository/maven-public/")
        maven("https://eldonexus.de/repository/maven-proxies/")
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://mvn.lumine.io/repository/maven-public/")
        maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }

    dependencies {
        compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
        compileOnly("org.projectlombok:lombok:1.18.34")
        compileOnly("org.jetbrains:annotations:24.1.0")
        annotationProcessor("org.projectlombok:lombok:1.18.34")
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
    }
}
