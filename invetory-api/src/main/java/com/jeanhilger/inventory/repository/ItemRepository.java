package com.jeanhilger.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.jeanhilger.inventory.model.Item;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface ItemRepository extends CrudRepository<Item, Long> {
	
}