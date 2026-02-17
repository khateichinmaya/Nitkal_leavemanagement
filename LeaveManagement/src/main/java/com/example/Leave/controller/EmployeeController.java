package com.example.Leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Leave.Entity.Employee;
import com.example.Leave.Entity.LeaveBalance;
import com.example.Leave.service.EmployeeServiceImpl;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@GetMapping("/employee/dashboard")
	public String employeeDashboard(Model model, Authentication authentication) {

	    String email = authentication.getName();
	    Employee employee = employeeService.findByEmail(email);

	    model.addAttribute("employee", employee);
	    model.addAttribute("balance", employee.getLeaveBalance());
	    model.addAttribute("leaveHistory", employee.getLeaveRequests());

	    return "employee-dashboard";
	}



}
