import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
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

tasks.test {
    useJUnitPlatform()
}

kover {
    isDisabled.set(false)
    engine.set(kotlinx.kover.api.DefaultIntellijEngine)

    htmlReport {
        onCheck.set(false)
        reportDir.set(layout.buildDirectory.dir("reports/html-result"))
    }

    verify {
        onCheck.set(true)

        rule {
            isEnabled = true
            name = null
            target = kotlinx.kover.api.VerificationTarget.ALL

            bound {
                minValue = 99
                counter = kotlinx.kover.api.CounterType.LINE
                valueType = kotlinx.kover.api.VerificationValueType.COVERED_PERCENTAGE
            }
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}