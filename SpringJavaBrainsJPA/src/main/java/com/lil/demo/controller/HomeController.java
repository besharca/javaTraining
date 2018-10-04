package com.lil.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lil.demo.model.Employee;
import com.lil.demo.repository.EmployeeRepository;

@Controller
public class HomeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home.jsp");
		mv.addObject("WelcomeMessage", "Hi there SHITTER");
		return mv;
	}
	
	@GetMapping("rusnak")
	public ModelAndView shitter(HttpServletRequest request) {
		System.out.println("ALOOO ECHIPA");
		Employee result = empRepo.findById(Integer.parseInt(request.getParameter("id"))).orElse(new Employee(-1,"not found","unluck"));
		ModelAndView mv = new ModelAndView("home.jsp");
		String  message = result.getId() + " "+result.getName()+" "+result.getDescription();
		System.out.println(message);
		mv.addObject("WelcomeMessage", message);
		
		return mv;
		
	}
	
}
