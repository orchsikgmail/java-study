package me.blacksheepbell;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })  // 다중설정 가능 with배열
public class SampleController {
	
	@RequestMapping(value= "/hello")
	@ResponseBody
	public String hello() {
		return "hello";
	}
	
	@RequestMapping(value= "/hi")
	@ResponseBody
	public String hi() {
		return "hi";
	}
	
	
	@RequestMapping(value= "/anyword??")
	@ResponseBody
	public String hi2() {
		return "anywordOk";
	}
	
	@RequestMapping(value= "/manyword*")
	@ResponseBody
	public String hi3() {
		return "manywordsssOk";
	}
	
	@RequestMapping(value= "/starManyword*/**") // boot는 기본적으로 확자자 맵핑을 지원하지 않으므로 확장자매핑하고 싶은 경우 * 사용,, 하지만 권장하지않음
	@ResponseBody
	public String hi4() {
		return "starManywordsssOk";
	}
	
	

}
