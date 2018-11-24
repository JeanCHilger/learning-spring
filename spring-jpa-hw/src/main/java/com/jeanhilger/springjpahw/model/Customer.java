package com.jeanhilger.springjpahw.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// @Entity indicates that the class is a JPA entity
// @Table is omitted indicating that the class will be mapped to a 
// table named Customer
@Entity
public class Customer {
	
	// Indicates that it is the id of the object 
	@Id
	// Indicates that the id should be generated automatically
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	
	// This default constructor exists for the sake of JPA.
	protected Customer() {}
	
	public Customer(String firstName, String lastName, int age, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s, %d years old, %s for contact", 
				this.firstName, this.lastName, this.age, this.email);
	}
}