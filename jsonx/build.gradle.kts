import java.util.*
import com.novoda.gradle.release.PublishExtension

plugins {
    id("java-library")
    id("jacoco")
}

group = "com.github.panpf"
version = property("VERSION").toString()

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    api("org.jetbrains:annotations:${property("JETBRAINS_ANNOTATIONS_VERSION")}")
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
        artifactId = "jsonx"
        publishVersion = property("VERSION").toString()
        desc = "Java, JSON, Extensions"
        website = "https://github.com/panpf/jsonx"
        userOrg = moduleLocalProperties.getProperty("bintray.userOrg")
        bintrayUser = moduleLocalProperties.getProperty("bintray.user")
        bintrayKey = moduleLocalProperties.getProperty("bintray.apikey")
    }
}
