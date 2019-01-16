package com.jeanhilger.restservice.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import com.jeanhilger.restservice.Status;
import com.jeanhilger.restservice.controller.OrderController;
import com.jeanhilger.restservice.model.Order;

@Component // This annotation mark a class as a Spring framework component, it means that
		   // Spring will autodetect this class for dependency injection.
		   // @Service, @Repository, and @Controller are specializations of @Component.
public class OrderResourceAssembler implements ResourceAssembler<Order, Resource<Order>> {
	
	@Override
	public Resource<Order> toResource(Order order) {
		
		// Create unconditional links (for one order and for all of them):
		
		Resource<Order> orderResource = new Resource<>(order,
				linkTo(methodOn(OrderController.class).listOne(order.getId())).withSelfRel(),
				linkTo(methodOn(OrderController.class).listAll()).withRel("orders"));
		
		// Create the conditional links based on the state of the order:
		
		if (order.getStatus() == Status.IN_PROGRESS) {
			orderResource.add(
					linkTo(methodOn(OrderController.class)
							.cancel(order.getId())).withRel("cancel"));
			
			orderResource.add(
					linkTo(methodOn(OrderController.class)
							.complete(order.getId())).withRel("complete"));
			
			// The 'add' method adds all given links to the resource.
			
			// Instead of let the clients decide about the status, 
			// links are given to them, to let them know what are valid
			// actions. This is one concept of HATEOAS
		}
		
		return orderResource;
	}
}