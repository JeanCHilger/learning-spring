package com.jeanhilger.restspring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data // This is a Lombok annotation that creates all getters, setters, 
	  // equals, hash and toString methods based on the fields;
@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String firstName;
	private String lastName;
	private int age;
	private String role;
	
	protected Employee() {} // This is necessary because of JPA
	
	public Employee(String firstName, String lastName, int age, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.role = role;
	}
}
