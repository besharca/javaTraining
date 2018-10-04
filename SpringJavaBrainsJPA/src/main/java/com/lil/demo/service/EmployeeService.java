package com.lil.demo.service;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lil.demo.model.Employee;
import com.lil.demo.repository.EmployeeRepository;

@RestController
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/emps")
	public List<Employee> getAll(){
		return empRepo.findAll();
	}
	
	@PostMapping("/emps")
	public Employee addEmp(@RequestBody final Employee emp, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("emp", emp);
		Employee result = new Employee(-1, "Success", "Entry inserted");
		if(empRepo.existsById(emp.getId())) {
			result.setName("Error");
			result.setDescription("Entry already exists");
			session.setAttribute("saved", false);
		}else {
		empRepo.save(emp);
		session.setAttribute("saved", true);
		}
		
		return result;
	}
	
	
}
