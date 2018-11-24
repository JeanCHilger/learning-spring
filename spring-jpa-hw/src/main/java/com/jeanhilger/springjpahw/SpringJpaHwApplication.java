package com.jeanhilger.springjpahw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jeanhilger.springjpahw.repository.*;
import com.jeanhilger.springjpahw.model.*;


@SpringBootApplication
public class SpringJpaHwApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringJpaHwApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaHwApplication.class, args);
	}
	
	@Bean
	// Tests the CustomerRepository
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save some Customers to the data base
			repository.save(new Customer("John", "Doe", 25, "jhon.doe@gmail.com"));
			repository.save(new Customer("Dolly", "Parton", 38, "parton@dolly.com"));
			repository.save(new Customer("Jorge", "Trento", 999, "bueno@bueno.com"));
			repository.save(new Customer("Conta", "Portões", 70, "trash@hotmail.com"));
			repository.save(new Customer("Jack", "Bauer", 40, "j.b@aa.cu"));
			
			// search all Customers
			log.info("");
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for(Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");
			
			// search individual Customers by Id
			repository.findById(1L)
				.ifPresent(customer -> {
					log.info("Customer found with findById(1L):");
					log.info("--------------------------------");
					log.info(customer.toString());
					log.info("");
				});
			
			// search Customers by lastName
			log.info("Customer found with findByLastName('Trento'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Trento").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}
}
