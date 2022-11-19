import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    jacoco
}

group = "com.zijian"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.0")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.3")
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.2")
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    finalizedBy(tasks.jacocoTestCoverageVerification)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}