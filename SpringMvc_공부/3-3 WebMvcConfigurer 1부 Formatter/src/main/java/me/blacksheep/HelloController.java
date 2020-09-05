package me.blacksheep;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	// {value} UrlPath로 변수를 받는 방식
	@GetMapping("/hello/{name}")  
	public String hello1(
			@PathVariable String name
			) {
		return "Your name is " + name;
	}

	
	// age로 들어오는 문자열을 person으로 어떻게 변환해줘야하는지 모름 -> formatter 필요.
	// 사용자정의 Formatter 클래스를 만들고, WebConfig 클래스에서 등록해준다.
	@GetMapping("/hello/{name}/{age}") 
	public String hello2(
			@PathVariable String name,
			@PathVariable("age") Person person
			) {
		return name + ",You age is " + person.getAge();
	}
	
	
	// 파라미터로 넘어오는 문자열도 Formatter 처리 가능.
	@GetMapping("/hello/age")
	public String hello3(
			// @RequestParam String number
			@RequestParam("number") Person person
			) {
		return "your age is " + person.getAge();
	}
	
	
}
