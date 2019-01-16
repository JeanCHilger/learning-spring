package com.jeanhilger.restservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // Allow the @ExceptionHandler annotation to work globally - without it
				  // the @ExceptionHandler would be effective only in the controller method
				  // declared with it.
public class OrderNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler // Configures the advice to only respond if an 
					  // EmployeeNotFoundException is thrown.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected String orderNotFoundHandler(OrderNotFoundException ex) {
		return ex.getMessage();
	}

}
