package com.jeanhilger.securex.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* ::::IMPORTANT::::
 * Spring loads beans into it’s context before we have even requested it. 
 * This is to make sure all the beans are properly configured and application 
 * fail-fast if something goes wrong.
*/

@Configuration // Part of Spring Core.
			   // Indicates that a class declares one or more @Bean methods 
			   // and may be processed by the Spring container to generate 
			   // bean definitions and service requests for those beans at runtime.

public class MvcConfig implements WebMvcConfigurer {
	
	// The WebMvcConfigurer interface defines callback methods to customize the 
	// Java-base Spring MVC configuration.
	
	public void addViewControllers(ViewControllerRegistry registry) {
		/*
		 * "we can register view controllers that create a direct mapping 
		 * between the URL and the view name using the ViewControllerRegistry. 
		 * This way, there’s no need for any Controller between the two"
		 * */
		
		// addViewControllers method configure simple automated pre-configured controllers with
		// the response status and a view to render the body.
		// This is useful is cases where there is no need of custom controller logic
		// like a render simple home page, show a 404 message with HTML content...
		
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		
		// The 'addViewController' Maps a view controller to the given 
		// URL path (or pattern) in order to render a response with a 
		// pre-configured status code and view
		
		// what's inside 'setViewName' is the name of .html template file.
	}

}
