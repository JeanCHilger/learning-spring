package com.jeanhilger.restservice.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhilger.restservice.EmployeeNotFoundException;
import com.jeanhilger.restservice.EmployeeResourceAssembler;
import com.jeanhilger.restservice.model.Employee;
import com.jeanhilger.restservice.repository.EmployeeRepository;

@RestController // This annotation indicates that every data returned by the methods will
				// be written to the response body instead of a view. Similar to use @ResponseBoby
				// in a method.

public class EmployeeController {
	
	private final EmployeeRepository repository;
	
	private final EmployeeResourceAssembler assembler;
	
	public EmployeeController(EmployeeRepository repository,
			EmployeeResourceAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
		
	}
	
	// index page
	
	@GetMapping("/employees")
	public Resources<Resource<Employee>> listAll() {
		// Resource<> is a Spring HATEOAS container that includes data an a collection of links.
		
		// Resources<> is a Spring HATEOAS container aimed at encapsulating collections of Resources.
		
		List<Resource<Employee>> employees = repository.findAll().stream()
				.map(assembler::toResource) // references the toResource method
				.collect(Collectors.toList());
				
		// 'map()' returns a stream with the result of applying the given function to the elements 
		// in that stream. TODO: STUDY THIS
		
		return new Resources<>(employees,
				linkTo(methodOn(EmployeeController.class).listAll()).withSelfRel());
	}
	
	@PostMapping("/employees")
	public ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) throws URISyntaxException {
		// The '@RequestBody' annotation indicates that the incoming JSON from request
		// will be mapped into a domain object.
		// In other words, spring automatically deserializes the JSON into a Java type
		
		Resource<Employee> resource = assembler.toResource(repository.save(newEmployee));
		
		return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
		// 'ResponseEntity' is used to represent the whole HTTP response: status code, headers, and body
		// so it can be used to fully configure the HTTP response. 
		
		// The '.created' method creates a new builder with a CREATED (HTTP 201) status with a 
		// location header set to the given URI.	- there are other methods to other status
		
		// the '.getId' method returns a link to the resource with a 'self' Rel. In REST, 
		// a resource’s id is the URI of that resource.
		
		// The '.body' method adds a body to the response?
	}
	
	// single item
	
	@GetMapping("/employees/{id}")
	public Resource<Employee> showSingleEmployee(@PathVariable Long id) {
		// The '@PathVariable' annotation indicates that a method parameter should be
		// bound to a variable in the URI.
		
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		// The 'orElseThrow()' method will return the value if found, otherwise
		// returns the exception provided.
		
		/*return new Resource<>(employee,
				linkTo(methodOn(EmployeeController.class).showSingleEmployee(id)).withSelfRel(), // (A)
				linkTo(methodOn(EmployeeController.class).listAll()).withRel("employees"));  // (B)
		*/
		
		// The code was replaced using ResourceAssembler to:
		
		return assembler.toResource(employee);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee,
			@PathVariable Long id) throws URISyntaxException {
		Employee updatedEmployee = repository.findById(id)
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
		
		// The 'map()' method will apply the (anonymous) function passed, to the present value (returned
		// from 'findById') and return the value.
		
		// The 'orElseGet()' function will return the value if found, otherwise
		// invoke another and returns it.
		
		Resource<Employee> resource = assembler.toResource(updatedEmployee);
		
		return ResponseEntity
				.created(new URI(resource.getId().expand().getHref()))
				.body(resource);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
		// The '.noContent' method yields a NO_CONTENT (HTTP 204) status.
		
		// The '.build' method builds the response with no body.
	}
	
}