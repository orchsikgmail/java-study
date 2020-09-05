package me.blackbell;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context initialized");
		sce.getServletContext().setAttribute("name", "hc");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroyed");
	}

	
	
}
