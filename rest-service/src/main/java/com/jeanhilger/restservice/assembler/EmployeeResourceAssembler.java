package com.jeanhilger.restservice.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.jeanhilger.restservice.controller.EmployeeController;
import com.jeanhilger.restservice.model.Employee;

@Component // This annotation mark a class as a Spring framework component, it means that
		   // Spring will autodetect this class for dependency injection.
		   // @Service, @Repository, and @Controller are specializations of @Component.
public class EmployeeResourceAssembler implements ResourceAssembler<Employee, Resource<Employee>> {
	
	@Override
	public Resource<Employee> toResource(Employee employee) {
		return new Resource<>(employee,
				linkTo(methodOn(EmployeeController.class).listOne(employee.getId())).withSelfRel(), // (A)
				linkTo(methodOn(EmployeeController.class).listAll()).withRel("employees")); // (B)
	}
	
	// The line (A) will build a link to the method 'showSingleEmployee' from EmployeeController
	// class, labeling it as 'self'.
			
	// The line (B) will build a link to the method 'listAll' from EmployeeController
	// class, labeling it as 'employees'.
			
	// This format of JSON and links between resources is called HAL.
	
	// The ResourceAssembler<> interface has one method: toResource, that converts a non-resource
	// object into a resource-based object.

}
