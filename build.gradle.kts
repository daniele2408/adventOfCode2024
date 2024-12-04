import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("jvm") version "1.9.23"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("aocutils:logic:0.1.0")
    implementation("io.arrow-kt:arrow-core:1.2.4")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

val properties = Properties().apply {
    load(FileInputStream(rootProject.file("src/main/resources/local.properties")))
}
val localRepoUri = properties["localRepoUri"] as String

repositories {
    mavenCentral()
    maven {
        url = uri(localRepoUri)
    }
}