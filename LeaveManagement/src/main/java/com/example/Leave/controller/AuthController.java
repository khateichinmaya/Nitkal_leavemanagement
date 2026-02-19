package com.example.Leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Leave.Entity.Employee;
import com.example.Leave.Entity.LeaveBalance;
import com.example.Leave.service.EmployeeService;

@Controller
public class AuthController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// ---------------- LOGIN PAGE ----------------
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	// ---------------- REGISTER PAGE ----------------
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "register";
	}

	// ---------------- SAVE EMPLOYEE ----------------
	@PostMapping("/register")
	public String registerEmployee(@ModelAttribute Employee employee) {

	    employee.setPassword(passwordEncoder.encode(employee.getPassword()));

	    if (employee.getRole() == null || employee.getRole().isEmpty()) {
	        employee.setRole("EMPLOYEE");
	    }

	    LeaveBalance balance = new LeaveBalance();
	    balance.setEmployee(employee);  // VERY IMPORTANT
	    employee.setLeaveBalance(balance);

	    employeeService.saveEmployee(employee);

	    return "redirect:/login";
	}

	// ---------------- DASHBOARD REDIRECT ----------------
	@GetMapping("/dashboard")
	public String redirectDashboard(org.springframework.security.core.Authentication authentication) {

		Employee employee = employeeService.findByEmail(authentication.getName());

		if (employee.getRole().equalsIgnoreCase("ADMIN")) {
			return "redirect:/admin/dashboard";
		} else {
			return "redirect:/employee/dashboard";
		}
	}

}
