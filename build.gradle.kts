@file:Suppress("TrailingComma", "ConvertLambdaToReference")

plugins {
  java
  id("org.jetbrains.intellij") version "1.3.0"
  kotlin("jvm") version "1.6.0"
}

group = "com.github.lppedd"
version = "0.8.1"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
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
  patchPluginXml {
    version.set(project.version.toString())
    sinceBuild.set("201.6668")
    untilBuild.set(null as String?)

    val projectPath = projectDir.path
    pluginDescription.set((File("$projectPath/plugin-description.html").readText(Charsets.UTF_8)))
    changeNotes.set((File("$projectPath/change-notes/${version.get().replace('.', '_')}.html").readText(Charsets.UTF_8)))
  }
}