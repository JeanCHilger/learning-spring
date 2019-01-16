package com.jeanhilger.restservice.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jeanhilger.restservice.Status;
import com.jeanhilger.restservice.assembler.OrderResourceAssembler;
import com.jeanhilger.restservice.exception.OrderNotFoundException;
import com.jeanhilger.restservice.model.Order;
import com.jeanhilger.restservice.repository.OrderRepository;

@RestController
public class OrderController {

	private final OrderRepository repository;
	private final OrderResourceAssembler assembler;
	
	public OrderController(OrderRepository repository,
			OrderResourceAssembler assembler) {
		
		this.repository = repository;
		this.assembler = assembler;
	}
	
	@GetMapping("/orders")
	public Resources<Resource<Order>> listAll() {
		List<Resource<Order>> orders = repository.findAll().stream()
				.map(assembler::toResource)
				.collect(Collectors.toList()); // Returns a 'Collector' that accumulates the input 
											   // elements into a new 'List'.
		
		return new Resources<>(orders,
				linkTo(methodOn(OrderController.class).listAll()).withSelfRel());
	}
	
	@GetMapping("/orders/{id}")
	public Resource<Order> listOne(@PathVariable Long id) {
		Order order = repository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));
		
		return assembler.toResource(order);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Resource<Order>> newOrder(@RequestBody Order order) {
		order.setStatus(Status.IN_PROGRESS);
		Order newOrder = repository.save(order);
		
		return ResponseEntity
				.created(linkTo(methodOn(OrderController.class).listOne(newOrder.getId())).toUri())
				.body(assembler.toResource(newOrder));
	}
	
	@DeleteMapping("/orders/{id}/cancel")
	public ResponseEntity<ResourceSupport> cancel(@PathVariable Long id) {
		Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		
		// "Inheriting from ResourceSupport will allow adding links easily"
		
		if (order.getStatus() == Status.IN_PROGRESS) {
			order.setStatus(Status.CANCELLED);
			return ResponseEntity.ok(assembler.toResource(repository.save(order)));
		}
		
		return ResponseEntity
				.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(new VndErrors.VndError("Method not allowed", 
						"You can't cancel an order that is in the " + order.getStatus() + "status"));
		
		// 'VndError' is a hypermedia-supporting error container. 
	}
	
	@PutMapping("/orders/{id}/complete")
	public ResponseEntity<ResourceSupport> complete(@PathVariable Long id) {
		Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
		
		if (order.getStatus() == Status.IN_PROGRESS) {
			order.setStatus(Status.COMPLETED);
			return ResponseEntity.ok(assembler.toResource(repository.save(order)));
		}
		
		return ResponseEntity
				.status(HttpStatus.METHOD_NOT_ALLOWED)
				.body(new VndErrors.VndError("Method not allowed",
						"You can't complete an order that is in the " + order.getStatus() + "status"));
	}
}
