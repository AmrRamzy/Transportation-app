package com.transportation.app.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppRestController{

	@GetMapping("/")
	public String home() {
		return "Hello World/dev tools";
		
	}
	
	@GetMapping("/first")
	public String first() {
		return "first";
		
	}
	
	@GetMapping("/second")
	public String second() {
		return "second";
		
	}
	
	@GetMapping("/loggedOut")
	public String loggedOut() {
		return "user is loggedOut";
		
	}
	
}
