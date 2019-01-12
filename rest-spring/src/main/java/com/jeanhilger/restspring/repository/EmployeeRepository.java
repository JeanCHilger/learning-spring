package com.jeanhilger.restspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanhilger.restspring.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
