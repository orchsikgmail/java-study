package me.blacksheepbell;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Sample2Controller {
	
	// 미디어타입 설정
	@GetMapping(
			value = "/json",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,	// JSON 형식의 요청만 처리
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE	// JSON 형식으로 응답
			)
	@ResponseBody
	public String mediaTypeHandlerMapping() {
		return "json";
		
	}
	
	
	// 커스텀 애노테이션
	@GoodbyeGetMapping
	@ResponseBody
	public String goodbye() {
		return "goodbye";
	}
	
	
	
	
}
