package com.example.Leave.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Leave.Entity.Employee;
import com.example.Leave.Entity.LeaveRequest;
import com.example.Leave.repository.EmployeeRepository;
import com.example.Leave.service.EmployeeServiceImpl;
import com.example.Leave.service.LeaveServiceImpl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LeaveController {

    @Autowired
    private LeaveServiceImpl leaveService;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeService;

    // ------------------ SHOW APPLY PAGE ------------------
    @GetMapping("/employee/apply-leave")
    public String showApplyLeavePage() {
        return "apply-leave";
    }

    // ------------------ APPLY LEAVE ------------------
    @PostMapping("/employee/apply-leave")
    public String applyLeave(@ModelAttribute LeaveRequest leaveRequest,
                             Principal principal) {

        Employee employee = employeeRepository.findByEmail(principal.getName());

        leaveService.applyLeave(leaveRequest, employee);

        return "redirect:/employee/dashboard?success";
    }


    // ------------------ ADMIN APPROVE ------------------
    @GetMapping("/admin/approve/{id}")
    public String approveLeave(@PathVariable Long id) {

        leaveService.approveLeave(id);

        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/admin/reject/{id}")
    public String rejectLeave(@PathVariable Long id) {
        leaveService.rejectLeave(id);
        return "redirect:/admin/dashboard";
    }
    
 // ------------------ DOWNLOAD DOCUMENT (ADMIN) ------------------
    @GetMapping("/admin/document/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadDocument(@PathVariable String fileName) throws Exception {

        Path path = Paths.get("uploads").resolve(fileName).normalize();
        Resource resource = new UrlResource(path.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("File not found: " + fileName);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }



}
