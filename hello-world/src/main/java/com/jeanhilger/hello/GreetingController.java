package com.jeanhilger.hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// The RestController annotation marks the class as a controller 
// where every method returns a domain object instead of a view.
@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s";
	private final AtomicLong greetingCounter = new AtomicLong();
	private final AtomicLong usrCounter = new AtomicLong();
	
	@RequestMapping(value="/hello-world", method=RequestMethod.GET)
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		// Populates a new Greeting object and returns it
		// as the HTTP response body. The object is automatically
		// converted to JSON using Jackson - Spring's MappingJackson2HttpMessageConverter.
		return new Greeting(greetingCounter.incrementAndGet(), String.format(template, name));
	}
	
	// The option params allows receiving parameters from the HTTP requisition.
	// For instance, instead of /hello-world/{name}, the name parameter is passed
	// using /hello-world?name=something in requisition.
	@RequestMapping(
			value="/hello-world/user", 
			method=RequestMethod.GET,
			params= {"name", "age", "genre"})
	public User user(@RequestParam(value="name") String name,
					 @RequestParam(value="age") int age,
					 @RequestParam(value="genre") String genre) {
		return new User(usrCounter.incrementAndGet(), name, age, genre);
		
	}

}
