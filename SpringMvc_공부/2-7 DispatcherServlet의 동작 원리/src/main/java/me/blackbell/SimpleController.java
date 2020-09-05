package me.blackbell;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@org.springframework.stereotype.Controller("/simple")
public class SimpleController implements Controller {

	// @GetMapping("/simple")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/WEB-INF/simple.jsp");
		return mv;
	}


	
	
	

}
