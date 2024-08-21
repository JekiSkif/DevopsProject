package com.example.demo;

import org.junit.jupiter.api.Test; // Provides support for writing tests in JUnit 5.
import org.springframework.boot.test.context.SpringBootTest; // Indicates that the test is a Spring Boot test.

@SpringBootTest // Tells Spring Boot to look for a main configuration class and use it to start the application context for the test.
class DemoApplicationTests {

	@Test // Marks this method as a test method.
	void contextLoads() {
		// This method will test if the Spring application context loads successfully.
		// No implementation is needed; the test will fail if the context cannot be loaded.
	}

}
