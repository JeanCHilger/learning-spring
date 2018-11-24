package com.jeanhilger.springjpahw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jeanhilger.springjpahw.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	// Spring Data JPA allows to define query methods by simply declaring their signature.
	List<Customer> findByLastName(String lastName);

}
