package com.sp.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("member.memberController")
public class MemberController {
	
	@Autowired
	private MemberService memberservice;
	
	@RequestMapping(value="/member/created", method=RequestMethod.GET)
	public String createForm (Model model) throws Exception {
		
		model.addAttribute("mode", "created");
		
		return ".member.created";
	}
	
	
	@RequestMapping(value="/member/created", method = RequestMethod.POST)
	public String createSubmit (
			Member dto,
			Model model
			) throws Exception {
		
		try {
			memberservice.insertMember(dto);
		} catch (Exception e) {
			return ".member.created";
		}
		
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login(
			String login_error,
			Model model
			) {
		
		boolean isLoginError = login_error!=null;
		String msg = "";
		
		if(isLoginError) {
			msg = "아이디 또는 패스워드가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return ".member.login";
	}
	
	@RequestMapping(value="/member/noAuthorized")
	public String noAuthority(
			Model model
			) {
		
		model.addAttribute("msg", "권한이 제한된 페이지입니다.");
		return ".mainLayout";
	}
	
	@RequestMapping(value="/member/expired")
	public String expired(
			Model model
			) {
		
		model.addAttribute("msg", "로그인 상태가 유효하지 않습니다. 재로그인 해주세요.");
		return ".mainLayout";
	}
	
	
	@RequestMapping(value="/member/checkId", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> checkId(
			@RequestParam String id
			) throws Exception {
		
		Map<String, Object> json = new HashMap<>();
		Member dto = memberservice.readMember(id);
		
		if(dto!=null && dto.getId().length()!=0) {
			json.put("isUsed", "true");
		} else {
			json.put("isUsed", "false");
		}
		
		return json;
	}
	
	
	
	
	
	
	
	

}
