package me.blackbell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// @RestController // ��ü�� ����
@Controller  // view�� ����  <= ModelandView ��ü�� ����.
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
