plugins {
    `java-library`
}

dependencies {
    testImplementation("org.junit:junit-bom:5.10.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core:5.7.0")
}
