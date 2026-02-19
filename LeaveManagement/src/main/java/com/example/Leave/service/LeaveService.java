package com.example.Leave.service;

import java.util.List;

import com.example.Leave.Entity.LeaveRequest;

public interface LeaveService {

    LeaveRequest applyLeave(Long empId, String leaveType, int days);

    LeaveRequest approveLeave(Long leaveId);

    List<LeaveRequest> getAllRequests();

    List<LeaveRequest> getEmployeeLeaves(Long empId);
}
