package me.blackbell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @RestController // 按眉甫 府畔
@Controller  // view甫 府畔  <= ModelandView 按眉甫 积己.
public class HelloController {

	@Autowired
	HelloService helloService;
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello  " + helloService.getName();
	}
	
	
	@GetMapping("/goodbye")
	public String goodbye() {
		return "goodbye";
	}
	
	
}
