package com.example.Leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.Leave.service.LeaveServiceImpl;

@Controller
public class AdminController {
	
	@Autowired
	private LeaveServiceImpl leaveService;
	
	@GetMapping("/admin/dashboard")
	public String adminDashboard(Model model) {

	    model.addAttribute("pendingLeaves", leaveService.getPendingLeaves());
	    model.addAttribute("allLeaves", leaveService.getAllLeaves());

	    return "admin-dashboard";
	}



}
