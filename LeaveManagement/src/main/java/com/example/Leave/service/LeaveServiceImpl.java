package com.example.Leave.service;

import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Leave.Entity.Employee;
import com.example.Leave.Entity.LeaveBalance;
import com.example.Leave.Entity.LeaveRequest;
import com.example.Leave.repository.EmployeeRepository;
import com.example.Leave.repository.LeaveBalanceRepository;
import com.example.Leave.repository.LeaveRequestRepository;

@Service
public class LeaveServiceImpl {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private LeaveBalanceRepository leaveBalanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    // ================= APPLY LEAVE =================
    public LeaveRequest applyLeave(LeaveRequest leaveRequest, Employee employee) {

        // attach employee
        leaveRequest.setEmployee(employee);

        // calculate total days automatically
        long days = ChronoUnit.DAYS.between(
                leaveRequest.getStartDate(),
                leaveRequest.getEndDate()
        ) + 1;

        if (days <= 0) {
            throw new RuntimeException("End date must be after start date");
        }

        leaveRequest.setNumberOfDays((int) days);
        leaveRequest.setStatus("PENDING");

        return leaveRequestRepository.save(leaveRequest);
    }


    // ================= APPROVE LEAVE =================
    public LeaveRequest approveLeave(Long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leaveRequest.setStatus("APPROVED");

        Employee employee = leaveRequest.getEmployee();
        LeaveBalance balance = employee.getLeaveBalance();

        int days = leaveRequest.getNumberOfDays();

        if ("EL".equalsIgnoreCase(leaveRequest.getLeaveType())) {
            balance.setEarnedLeave(balance.getEarnedLeave() - days);

        } else if ("SL".equalsIgnoreCase(leaveRequest.getLeaveType())) {
            balance.setSickLeave(balance.getSickLeave() - days);
        }

        leaveBalanceRepository.save(balance);

        return leaveRequestRepository.save(leaveRequest);
    }
    
    public LeaveRequest rejectLeave(Long id) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        leaveRequest.setStatus("REJECTED");

        return leaveRequestRepository.save(leaveRequest);
    }



    // ================= GET PENDING =================
    public List<LeaveRequest> getPendingLeaves() {
        return leaveRequestRepository.findAll()
                .stream()
                .filter(l -> "PENDING".equalsIgnoreCase(l.getStatus()))
                .toList();
    }
    
    public List<LeaveRequest> getAllLeaves() {
        return leaveRequestRepository.findAll();
    }

}
