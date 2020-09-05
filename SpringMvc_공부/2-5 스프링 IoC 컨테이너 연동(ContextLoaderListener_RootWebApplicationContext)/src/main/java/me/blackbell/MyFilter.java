package me.blackbell;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("filter init");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter dofilter");
		chain.doFilter(request, response); // dofilter를 해줘야 다음 필터 혹은 서블릿으로 하당 request와 response를 전달한다.
		
	}

	public void destroy() {
		System.out.println("filter destroy");
	}
	
	

}
