plugins {
    id("com.github.johnrengelman.shadow") version ("8.1.1") apply(false)
    id("io.micronaut.application") version ("4.0.3") apply (false)
    id("io.micronaut.aot") version ("4.0.3") apply (false)
}

subprojects {
    apply {
        plugin("java")
    }

    version = "0.0.1"
    group = "ch.swaechter.reposly.web"

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_17.toString()
        targetCompatibility = JavaVersion.VERSION_17.toString()
        options.encoding = "UTF-8"
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.whenTaskAdded {
        if (listOf("distZip", "distTar", "shadowDistZip", "shadowDistTar", "startScripts", "startShadowScripts").contains(this.name)) {
            enabled = false
        }
    }
}
