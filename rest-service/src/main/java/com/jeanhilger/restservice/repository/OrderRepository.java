package com.jeanhilger.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanhilger.restservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
