package com.lil.demo.aspect;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lil.demo.model.Employee;
import com.lil.demo.model.PostLogger;
import com.lil.demo.repository.EmployeeRepository;
import com.lil.demo.repository.LoggRepository;

@Aspect
@Component
public class AspectTryout {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	LoggRepository loggRepo;
	
	@Before("execution (* com.lil.demo.service.EmployeeService.getAll())")
	public void doSomething() {
		System.out.println("worked");
	}
	
	
	// This method works only with @After
	@After("@annotation(org.springframework.web.bind.annotation.PostMapping) && execution(public * *(..))")
	public void loggPostTime(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		logg(request);
	}
	
	
	@Pointcut("execution (* com.lil.demo.service.EmployeeService.addEmp(*))")
	public void add() {}

	
	void logg(HttpServletRequest request) {
		//get emp bean
		Employee emp = (Employee)request.getSession().getAttribute("emp");
		request.getSession().removeAttribute("emp");
		
		//logg posted emp bean
		PostLogger postLogg =
				new PostLogger(emp.getId(),emp.getName()+" --description: "+emp.getDescription(),LocalDate.now()+" "+LocalTime.now());
		
		//bean is unique then >>
		if((boolean)request.getSession().getAttribute("saved")) {
			loggRepo.save(postLogg);
		}
	}
	
}
