@file:Suppress("TrailingComma", "ConvertLambdaToReference")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  id("org.jetbrains.intellij") version "1.13.2"
  kotlin("jvm") version "1.8.10"
}

group = "com.github.lppedd"
version = "0.8.2"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation("junit:junit:4.13.2")
}

intellij {
  version.set("IU-201.6668.113")
  downloadSources.set(true)
  pluginName.set("idea-conventional-commit")
  plugins.set(listOf(
      "java",
      "JavaScriptLanguage",
      "Pythonid:201.6668.113",
      "com.jetbrains.php:201.6668.113"
  ))
}

configure<JavaPluginExtension> {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
  wrapper {
    distributionType = Wrapper.DistributionType.ALL
  }

  val kotlinSettings: KotlinCompile.() -> Unit = {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xno-call-assertions",
        "-Xno-receiver-assertions",
        "-Xno-param-assertions",
    )
  }

  compileKotlin(kotlinSettings)
  compileTestKotlin(kotlinSettings)

  patchPluginXml {
    version.set(project.version.toString())
    sinceBuild.set("201.6668")
    untilBuild.set("")

    val projectPath = projectDir.path
    pluginDescription.set((File("$projectPath/plugin-description.html").readText(Charsets.UTF_8)))
    changeNotes.set((File("$projectPath/change-notes/${version.get().replace('.', '_')}.html").readText(Charsets.UTF_8)))
  }
}
