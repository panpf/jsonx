plugins {
    id("java-library")
    id("jacoco")
    id("kotlin")
}

group = "com.github.panpf"
version = property("VERSION").toString()

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.6"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.6"
    }
}

dependencies {
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${property("KOTLIN_VERSION")}")
    api(project(":jsonx"))
    compileOnly("org.json:org.json:${property("JSON")}")

    testImplementation("junit:junit:${property("JUNIT_VERSION")}")
    testImplementation("org.json:org.json:${property("JSON")}")
    testImplementation("com.github.panpf.tools4j:tools4j-base64-ktx:${property("TOOLS4J")}")
}

tasks.getByName("check").dependsOn(tasks.getByName("jacocoTestReport"))

/*
 * publish
 */
`java.util`.Properties().apply {
    rootProject.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
    project.file("local.properties").takeIf { it.exists() }?.inputStream()?.use { load(it) }
}.takeIf {
    it.getProperty("bintray.user") != null && it.getProperty("bintray.userOrg") != null && it.getProperty("bintray.apiKey") != null
}?.let { localProperties ->
    apply(plugin = "com.github.panpf.bintray-publish")
    configure<com.github.panpf.bintray.publish.PublishExtension> {
        groupId = "com.github.panpf"
        artifactId = "jsonx-ktx"
        publishVersion = property("VERSION").toString()
        desc = "Java, JSON, Kotlin, Extensions"
        website = "https://github.com/panpf/jsonx"
        userOrg = localProperties.getProperty("bintray.userOrg")
        bintrayUser = localProperties.getProperty("bintray.user")
        bintrayKey = localProperties.getProperty("bintray.apiKey")
    }
}
