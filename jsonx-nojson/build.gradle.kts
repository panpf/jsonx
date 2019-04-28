import java.util.*
import com.novoda.gradle.release.PublishExtension

plugins {
    id("java-library")
    id("jacoco")
}

group = "me.panpf"
version = property("VERSION").toString()

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation("org.jetbrains:annotations:${property("JETBRAINS_ANNOTATIONS_VERSION")}")
    compileOnly("org.json:org.json:${property("JSON")}")

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
    testImplementation("me.panpf:javax:${property("JAVAX")}")
    testImplementation("org.json:org.json:${property("JSON")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))

/*
 * publish
 */
project.file("local.properties").takeIf { it.exists() }?.let { file -> file.inputStream().use { input -> Properties().apply { load(input) } } }?.takeIf { !it.isEmpty }?.let { moduleLocalProperties ->
    apply(plugin = "com.novoda.bintray-release")

    configure<PublishExtension> {
        groupId = "me.panpf"
        artifactId = "jsonx-nojson"
        publishVersion = property("VERSION").toString()
        desc = "Java, JSON, Extensions"
        website = "https://github.com/panpf/jsonx"
        userOrg = moduleLocalProperties.getProperty("bintray.userOrg")
        bintrayUser = moduleLocalProperties.getProperty("bintray.user")
        bintrayKey = moduleLocalProperties.getProperty("bintray.apikey")
    }
}
