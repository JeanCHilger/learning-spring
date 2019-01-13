package com.jeanhilger.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanhilger.restservice.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}