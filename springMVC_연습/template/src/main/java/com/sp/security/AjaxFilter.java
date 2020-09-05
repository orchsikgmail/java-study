package com.sp.security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;

public class AjaxFilter implements Filter {

	private String ajaxHeader;
	public void setAjaxHeader(String ajaxHeader) {
		this.ajaxHeader = ajaxHeader;
	}

	private boolean isAjaxRequest(HttpServletRequest req) {
		// AJAX라는 Header의 값이 존재 && AJAX라는 Header 값이 true면 true
		return req.getHeader(ajaxHeader) != null &&
					req.getHeader(ajaxHeader).equals(Boolean.TRUE.toString());
	}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 객체가 생성될 때 호출되는 메소드
		// 웹애플리케이션 서비스가 올라가면, 즉 웹서버가 시작될 때 한번만 생성되어 한번만 호출된다.
		// 주로 초기화 기능 구현
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터링 설정한 서블릿을 실행할 때마다 호출되는 메소드로서 실제 필터링 기능 구현
		
		// ServletRequest으로는 http프로토콜에 존재하는 session과 cookie같은 작업을 할 수 없기 때문에 바꿔줘.
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(isAjaxRequest(req)) {
            try {
                chain.doFilter(req, res);
           } catch (AccessDeniedException e) {
        	   // java 예외
               res.sendError(403);
           } catch (AuthenticationException e) {
        	   // spring 예외
               res.sendError(401);
           }
       } else {
           chain.doFilter(req, res);
       }
	}

	@Override
	public void destroy() {
		// 필터 객체 삭제될때 호출되는 메소드
	}
	
}
