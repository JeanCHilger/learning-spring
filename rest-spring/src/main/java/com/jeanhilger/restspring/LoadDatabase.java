package com.jeanhilger.restspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jeanhilger.restspring.model.Employee;
import com.jeanhilger.restspring.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j // Is a lombok annotation that autocreate an Slf4j LoggerFactory as "log".
public class LoadDatabase {
	
	//private static final Logger log = LoggerFactory.getLogger(RestSpringApplication.class);
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo", 
					"Baggins", 60, "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo",
					"Baggins", 28, "thief")));
		};
	}

}
