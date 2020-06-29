import java.util.*
import com.novoda.gradle.release.PublishExtension

plugins {
    id("java-library")
    id("jacoco")
    id("kotlin")
}

group = "com.github.panpf"
version = property("VERSION").toString()

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${property("KOTLIN_VERSION")}")
    implementation(project(":jsonx"))
    compileOnly("org.json:org.json:${property("JSON")}")

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
    testImplementation("org.json:org.json:${property("JSON")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))

/*
 * publish
 */
project.file("local.properties").takeIf { it.exists() }?.let { file -> file.inputStream().use { input -> Properties().apply { load(input) } } }?.takeIf { !it.isEmpty }?.let { moduleLocalProperties ->
    apply(plugin = "com.novoda.bintray-release")

    configure<PublishExtension> {
        groupId = "com.github.panpf"
        artifactId = "jsonx-ktx"
        publishVersion = property("VERSION").toString()
        desc = "Java, JSON, Kotlin, Extensions"
        website = "https://github.com/panpf/jsonx"
        userOrg = moduleLocalProperties.getProperty("bintray.userOrg")
        bintrayUser = moduleLocalProperties.getProperty("bintray.user")
        bintrayKey = moduleLocalProperties.getProperty("bintray.apikey")
    }
}
