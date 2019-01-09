package com.jeanhilger.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.jeanhilger.inventory.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
}
