package com.sp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.sp.member.Member;
import com.sp.member.MemberService;
import com.sp.member.SessionInfo;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private MemberService memberService;

	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		try {
			Member dto = memberService.readMember(authentication.getName());
			memberService.updateLogindate(authentication.getName());
			
			SessionInfo info = new SessionInfo();
			info.setId(dto.getId());
			info.setName(dto.getName());
			
			HttpSession session = request.getSession();
			session.setAttribute("member", info);
			
			super.onAuthenticationSuccess(request, response, authentication);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
