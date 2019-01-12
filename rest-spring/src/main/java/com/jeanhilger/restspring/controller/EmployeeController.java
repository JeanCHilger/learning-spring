package com.jeanhilger.restspring.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhilger.restspring.EmployeeNotFoundException;
import com.jeanhilger.restspring.model.Employee;
import com.jeanhilger.restspring.repository.EmployeeRepository;

@RestController // This annotation indicates that every data returned by the methods will
				// be written to the response body instead of a view. Similar to use @ResponseBoby
				// in a method.

public class EmployeeController {
	
	private final EmployeeRepository repository;
	
	public EmployeeController(EmployeeRepository repository) {
		this.repository = repository;
	}
	
	// index page
	
	@GetMapping("/employees")
	public List<Employee> listAll() {
		return repository.findAll();
	}
	
	@PostMapping("/employees")
	public Employee newEmployee(@RequestBody Employee newEmployee) {
		// The @RequestBody annotation indicates that the incoming JSON from request
		// will be mapped into a domain object.
		// In other words, spring automatically deserializes the JSON into a Java type
		
		return repository.save(newEmployee);
	}
	
	// single item
	
	@GetMapping("/employees/{id}")
	public Employee showSingleEmployee(@PathVariable Long id) {
		// The @PathVariable annotation indicates that a method parameter should be
		// bound to a variable in the URI.
		
		return repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		// The orElseThrow() method will return the value if found, otherwise
		// returns the exception provided.
	}
	
	@PutMapping("/employees/{id}")
	public Employee replaceEmployee(@RequestBody Employee newEmployee,
			@PathVariable Long id) {
		return repository.findById(id)
				.map(employee -> {
					employee.setFirstName(newEmployee.getFirstName());
					employee.setLastName(newEmployee.getLastName());
					employee.setAge(newEmployee.getAge());
					employee.setRole(newEmployee.getRole());
					return repository.save(employee);
				})
				.orElseGet(() -> {
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});
		
		// The map() method will apply the (anonymous) function passed, to the present value (returned
		// from findById) and return the value.
		
		// The orElseGet() function will return the value if found, otherwise
		// invoke another and returns it.
	}
	
	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}