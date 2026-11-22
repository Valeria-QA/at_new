plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("io.rest-assured:rest-assured:5.5.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.27.0")
    testImplementation("com.codeborne:selenide:7.5.1")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
    }
}

tasks.register("runTests") {
    dependsOn("test")
    finalizedBy("testRunIsOver")
}

tasks.register("testRunIsOver") {
    doLast {
        println("Test run is over")
    }
}

tasks.register<Test>("runSmoke") {
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("smoke")
    }
    testLogging {
        showStandardStreams = true
    }
}

tasks.register<Test>("runRegress") {
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("regress")
    }
    testLogging {
        showStandardStreams = true
    }
}

tasks.register<Test>("runNew") {
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("new")
    }
    testLogging {
        showStandardStreams = true
    }
}

tasks.register<Test>("runApiTests") {
    group = "verification"
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    useJUnitPlatform {
        includeTags("api")
    }
    testLogging {
        showStandardStreams = true
    }
    finalizedBy("testRunIsOver")
}