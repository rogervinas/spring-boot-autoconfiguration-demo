plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    `java-library`
}

dependencies {
    api(project(":foo-service"))
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
