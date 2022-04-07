/*
 * Copyright (c) 2022 by Fred George
 * May be used freely except for training; license required for training.
 * @author Fred George  fredgeorge@acm.org
 */

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Needs exercises to test
    implementation(project(":exercises"))

    // Jupiter using JUnit 5
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.test {
    useJUnitPlatform()  // Encourages(?) JUnit 5 use by Kotlin
    testLogging {
        events("passed", "skipped", "failed")
    }
}
