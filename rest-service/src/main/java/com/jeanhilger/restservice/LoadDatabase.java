package com.jeanhilger.restservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jeanhilger.restservice.model.Employee;
import com.jeanhilger.restservice.model.Order;
import com.jeanhilger.restservice.repository.EmployeeRepository;
import com.jeanhilger.restservice.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j // Is a lombok annotation that autocreate an Slf4j LoggerFactory as "log".
public class LoadDatabase {
	
	//private static final Logger log = LoggerFactory.getLogger(RestSpringApplication.class);
	
	@Bean
	CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
			OrderRepository orderRepository) {
		orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
		orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));
		
		orderRepository.findAll().forEach(order -> {
			log.info("Preloaded " + order);
		});
		
		return args -> {
			log.info("Preloading " + employeeRepository.save(new Employee("Bilbo", 
					"Baggins", 60, "burglar")));
			log.info("Preloading " + employeeRepository.save(new Employee("Frodo",
					"Baggins", 28, "thief")));
		};
	}

}