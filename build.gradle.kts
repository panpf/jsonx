buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven { setUrl("https://mirrors.huaweicloud.com/repository/maven/") }   // Huawei maven mirror
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("KOTLIN_VERSION")}")
        classpath("com.github.panpf.bintray-publish:bintray-publish:${property("BINTRAY_PUBLISH")}")
    }
}

allprojects {
    repositories {
        jcenter()
        mavenCentral()
    }
}