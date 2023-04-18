plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.0.6")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.0.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.6")
}
