
plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.9.0"
}

group = "com.easy.entity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testCompile group: 'junit', name: 'junit', version: '4.12'
    implementation("commons-httpclient:commons-httpclient:3.1"){
        exclude(group = "commons-logging", module = "commons-logging")
        exclude(group = "commons-codec", module = "commons-codec")
    }
    implementation("mysql:mysql-connector-java:5.1.49")
    configurations.all {
        resolutionStrategy.force("commons-httpclient:commons-httpclient:3.1")
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    // version.set("2022.2.1")
    type.set("IC") // Target IDE Platform
    localPath.set("E:\\developSoftwareInstall\\ideaIC\\ideaIC2022.2.1\\f72ebe165697d9008ac64a185ce8b0aad1b63aec\\ideaIC-2022.2.1")
    //localPath.set("E:\\developSoftwareInstall\\ideaIC\\ideaIC2021.2.1\\fb8e0c8f941235e03802029fb64f458df3d2823c\\ideaIC-2021.2.1")
    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "UTF-8"
    }

    patchPluginXml {
        sinceBuild.set("211")
        untilBuild.set("222.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
