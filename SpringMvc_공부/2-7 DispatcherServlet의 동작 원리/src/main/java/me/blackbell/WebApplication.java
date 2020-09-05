package me.blackbell;

//Web.xml 파일 생성하지 않고, 아래와 같이 자바파일만 가지고 서블릿을 등록해서 설정 가능
//참고만.

/*
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class WebApplication implements WebApplicationInitializer{
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebConfig.class);
		context.refresh();

		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic app = servletContext.addServlet("app", dispatcherServlet);
		app.addMapping("/");
	}
}
*/
