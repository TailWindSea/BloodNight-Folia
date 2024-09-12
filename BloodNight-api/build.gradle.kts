plugins {
    id("de.eldoria.library-conventions")
}

description = "BloodNight-api"

java {
    withJavadocJar()
}

publishData {
    useEldoNexusRepos()
    publishComponent("java")
}

