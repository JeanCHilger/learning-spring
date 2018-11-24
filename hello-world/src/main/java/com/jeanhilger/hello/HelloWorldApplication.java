package com.jeanhilger.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//The @SpringBootApplication adds the annotations:
//@Configuration, @EnableAutoConfiguration, @EnableWebMvc, @ComponentScan
@SpringBootApplication
public class HelloWorldApplication {

	public static void main(String[] args) {
		// Launches the application
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}
