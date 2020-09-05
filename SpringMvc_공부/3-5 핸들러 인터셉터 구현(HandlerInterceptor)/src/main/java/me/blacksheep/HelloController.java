package me.blacksheep;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello/{name}")  
	public String hello1(
			@PathVariable String name
			) {
		return "Your name is " + name;
	}

	
}
