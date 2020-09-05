package me.blackbell;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	public void init() throws ServletException {
		System.out.println("init");
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet");
		
		resp.getWriter().println("<html>");
		resp.getWriter().println("<head>");
		resp.getWriter().println("<body>");
		resp.getWriter().println("<p>");
		resp.getWriter().println("Hello "+ getServletContext().getAttribute("name") + " I'm HelloServlet");
		resp.getWriter().println("</p>");
		resp.getWriter().println("</body>");
		resp.getWriter().println("</head>");
		resp.getWriter().println("</html>");
	}

	
	@Override
	public void destroy() {
		System.out.println("destroy");
	}
}
