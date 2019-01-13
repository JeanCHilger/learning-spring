package com.jeanhilger.restservice.controller;

import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhilger.restservice.EmployeeNotFoundException;
import com.jeanhilger.restservice.model.Employee;
import com.jeanhilger.restservice.repository.EmployeeRepository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

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
	public Resource<Employee> showSingleEmployee(@PathVariable Long id) {
		// The @PathVariable annotation indicates that a method parameter should be
		// bound to a variable in the URI.
		
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		// The orElseThrow() method will return the value if found, otherwise
		// returns the exception provided.
		
		return new Resource<>(employee,
				linkTo(methodOn(EmployeeController.class).showSingleEmployee(id)).withSelfRel(), // (A)
				linkTo(methodOn(EmployeeController.class).listAll()).withRel("employees"));  // (B)
		
		// The line (A) will build a link to the method 'showSingleEmployee' from EmployeeController
		// class, labeling it as 'self';
		
		// The line (B) will build a link to the method 'listAll' from EmployeeController
		// class, labeling it as 'employees';
		
		// This format of JSON and links between resources are called HAL.
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