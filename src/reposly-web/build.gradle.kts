plugins {
    id("com.github.johnrengelman.shadow")
    id("io.micronaut.application")
    id("io.micronaut.aot")
}

dependencies {
    // Web core
    annotationProcessor("io.micronaut.security:micronaut-security-annotations")
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.security:micronaut-security")
    implementation("io.micronaut.serde:micronaut-serde-jackson")

    // Web OpenAPI
    annotationProcessor("io.micronaut.openapi:micronaut-openapi")
    implementation("io.swagger.core.v3:swagger-annotations")

    // Logging
    runtimeOnly("ch.qos.logback:logback-classic")

    // GraalVM
    compileOnly("org.graalvm.nativeimage:svm")
}

application {
    mainClass.set("ch.swaechter.reposly.web.Application")
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("ch.swaechter.reposly.*")
    }
    aot {
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}

graalvmNative {
    binaries {
        named("main") {
            imageName.set("reposly")
        }
    }
}

tasks.withType<JavaCompile> {
    options.isFork = true
    options.forkOptions.jvmArgs?.addAll(listOf("-Dmicronaut.openapi.views.spec=swagger-ui.enabled=true,swagger-ui.theme=flattop", "-Dfile.encoding=UTF8"))
}

graalvmNative.toolchainDetection.set(false)
