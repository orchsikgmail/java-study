package me.blacksheep;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	// 와일드카드 형식 URL처리
	@GetMapping("/hello/{name}")  
	public String hello1(
			@PathVariable String name
			) {
		return "Your name is " + name;
	}
	
	// age로 들어오는 문자열을 person으로 어떻게 변환해줘야하는지 모름 -> Formatter 필요.
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
	
	// Converter를 이용해서 
	@GetMapping("/hello")
	public String hello4(
			@RequestParam("id") Person person
			) {
		return "hello, " + person.getName();
	}
		
		
	// RequestBody로 오는 문자열 처리 - HttpMsgConverter
	@GetMapping("/msg")
	public String requestBody(@RequestBody String msg) {
		return msg;
		/*	Http Msg Converter : 하위 기술하는 어노테이션 사용시 BODY의 내용을 사용하기 위해 변환해줌.
			@RequestBody : 요청시 본문으로 보내오는 객체, 문자열을  받음
			@ResponseBody :  응답시 본문에 객체,문자열을 담아서 전송
			
			WebConfigure에서 사용자 정의 HTTP Msg Converter등록이 가능하지만
			사용할 일이  없다. 기본으로 제공하는 컨버터가 좋다.
		 */
	}
	
	// RequestBody로 오는 객체 처리(Json으로 처리 됨) - HttpMsgConverter
	@GetMapping("/jsonMsg")
	public Person requestBody2(@RequestBody Person person) {
		return person;
	}
	
	
	
	
	
	
	
	
	
	
}
