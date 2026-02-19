package com.example.Leave.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    private String leaveType;  // SL or EL

    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    @Column(name = "number_of_days")
    private int numberOfDays;
    
    @Column(name="document_name")
    private String documentName;

    private String status;  // PENDING, APPROVED, REJECTED

    
    // ------------------- Constructors -------------------

    public LeaveRequest() {
    }

    public LeaveRequest(Long id, String leaveType, int numberOfDays,
                        String status, LocalDate startDate,
                        LocalDate endDate, Employee employee) {
        this.id = id;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    public LeaveRequest(String leaveType, int numberOfDays,
                        String status, LocalDate startDate,
                        LocalDate endDate, Employee employee) {
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.employee = employee;
    }

    // ------------------- Getters & Setters -------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
    
    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
