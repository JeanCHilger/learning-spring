package com.jeanhilger.restservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jeanhilger.restservice.EmployeeNotFoundException;

@ControllerAdvice // Allow the @ExceptionHandler annotation to work globally - without it
				  // the @ExceptionHandler would be effective only in the controller method
				  // declared with it.
public class EmployeeNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler // Configures the advice to only respond if an 
					  // EmployeeNotFoundException is thrown.
	@ResponseStatus(HttpStatus.NOT_FOUND) // Issues and Not Found status (404)
										  // to the response.
	String employeeNotFoundHandler(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
	
}