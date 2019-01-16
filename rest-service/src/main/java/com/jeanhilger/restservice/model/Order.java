package com.jeanhilger.restservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jeanhilger.restservice.Status;

import lombok.Data;

@Entity
@Data
@Table(name="CUSTOMER_TABLE") // Changes the table's name to CUSTOMER_ORDER because
							  // ORDER is a invalid name.
public class Order {

	@Id
	@GeneratedValue
	private Long id;
	
	private String description;
	private Status status;
	
	protected Order() {}
	
	public Order(String description, Status status) {
		
		this.description = description;
		this.status = status;
	}
	
	
}
