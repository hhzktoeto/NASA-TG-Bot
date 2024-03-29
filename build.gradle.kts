plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.jsonschema2pojo") version "1.2.1"
}

group = "hhz.telegram.bots"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	/**
	 *  Spring boot starters
	 */
	implementation("org.springframework.boot:spring-boot-starter-web")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	/**
	 *  Bot needs this
	 */
	implementation("org.apache.httpcomponents.client5:httpclient5:5.3.1")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
	implementation("org.telegram:telegrambots:6.9.7.1")

	/**
	 *  Utils & Logging
	 */
	implementation("org.projectlombok:lombok:1.18.26")
	annotationProcessor("org.projectlombok:lombok:1.18.26")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("org.slf4j:slf4j-api:2.0.5")
	implementation("ch.qos.logback:logback-classic:1.4.14")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
