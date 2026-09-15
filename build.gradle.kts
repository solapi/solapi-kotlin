import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.jetbrains.dokka.gradle.tasks.DokkaGeneratePublicationTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.3.0"
    kotlin("plugin.serialization") version "2.3.0"
    id("org.jetbrains.dokka") version "2.1.0"
    id("com.gradleup.shadow") version "9.3.1"
    java
    `java-library`
    `maven-publish`
    signing
    id("com.vanniktech.maven.publish") version "0.36.0"
}

group = "com.solapi"
version = "1.2.0"

repositories {
    mavenCentral()
}

dokka {
    dokkaPublications.html {
        suppressInheritedMembers.set(true)
    }
}

mavenPublishing {
    // Central Portal 사용 (OSSRH가 아닌)
    publishToMavenCentral(automaticRelease = false)

    // GPG 서명 활성화 (선택사항이지만 권장)
    signAllPublications()

    // 아티팩트 좌표 및 POM 메타데이터 설정
    coordinates(group.toString(), "sdk", version.toString())

    pom {
        name.set("SOLAPI SDK")
        description.set("SOLAPI 및 SOLAPI 계열(쿨에스엠에스 등) 서비스에서 사용되는 문자 발송용 SDK")
        url.set("https://github.com/solapi/solapi-kotlin")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/license/MIT")
            }
        }

        developers {
            developer {
                id.set("contact")
                name.set("SOLAPI Contact")
                email.set("contact@solapi.com")
                organization.set("SOLAPI Inc")
            }
            developer {
                id.set("lucy")
                name.set("Lucy Lee")
                email.set("lucy@solapi.com")
                organization.set("SOLAPI Inc")
            }
            developer {
                id.set("hosy")
                name.set("Hosy Lee")
                email.set("hosy@solapi.com")
                organization.set("SOLAPI Inc")
            }
        }

        scm {
            connection.set("scm:git:git://github.com/solapi/solapi-kotlin.git")
            developerConnection.set("scm:git:ssh://github.com/solapi/solapi-kotlin.git")
            url.set("https://github.com/solapi/solapi-kotlin")
        }
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("commons-codec:commons-codec:1.20.0")
    implementation("com.squareup.okhttp3:okhttp:5.3.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.3.0")
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
    implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0")

    testImplementation(kotlin("test"))
    testImplementation("com.squareup.okhttp3:mockwebserver3:5.3.0")

    dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:2.1.0")
}

val generatedSrcDir = layout.buildDirectory.dir("generated/source/kotlin")

sourceSets.main.get().java.srcDir(generatedSrcDir)

val generateVersionFile by tasks.register("generateVersionFile") {
    val file = generatedSrcDir.get().file("com/solapi/sdk/Version.kt")
    outputs.file(file)
    doLast {
        file.asFile.parentFile.mkdirs()
        file.asFile.writeText("""|package com.solapi.sdk
                               |
                               |internal object Version {
                               |    const val SDK_VERSION = "$version"
                               |}
                               |""".trimMargin())
    }
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn(generateVersionFile)
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

java {
    // Dokka가 javadocJar를 제공하므로 withJavadocJar() 제거
    withSourcesJar()
}

tasks.named("sourcesJar") {
    dependsOn(generateVersionFile)
}

tasks.shadowJar {
    mergeServiceFiles()

    relocate("com.fasterxml", "com.solapi.shadow.com.fasterxml")
    relocate("okhttp3", "com.solapi.shadow.okhttp3")
    relocate("okio", "com.solapi.shadow.okio")
    relocate("retrofit2", "com.solapi.shadow.retrofit2")
    relocate("org.apache", "com.solapi.shadow.org.apache")
    relocate("kotlinx.serialization", "com.solapi.shadow.kotlinx.serialization")

    archiveClassifier.set("")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    javaCompiler.set(javaToolchains.compilerFor {
        languageVersion.set(JavaLanguageVersion.of(8))
    })
}

tasks.withType<DokkaGeneratePublicationTask>().configureEach {
    dependsOn(generateVersionFile)
    outputDirectory.set(project.rootDir.resolve("docs"))
}

// Publishing 관련 태스크들이 dokkaJavadocJar에 의존하도록 설정
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn("dokkaJavadocJar")
}

// 메타데이터 생성 태스크가 dokkaJavadocJar에 의존하도록 설정
afterEvaluate {
    tasks.findByName("generateMetadataFileForMavenPublication")?.dependsOn("dokkaJavadocJar")
}
