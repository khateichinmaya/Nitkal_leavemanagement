package com.example.Leave.Entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    
    @Column(name="EmployeeName")
    private String name;
    private String department;
    private double salary;

    @Column(unique = true)
    private String email;

    private String password;

    private String role; // ADMIN or EMPLOYEE

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<LeaveRequest> leaveRequests;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private LeaveBalance leaveBalance;

    // ------------------- Constructors -------------------

    public Employee() {
    }

    public Employee(Long empId, String name, String department, double salary,
                    String email, String password, String role) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Employee(String name, String department, double salary,
                    String email, String password, String role) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ------------------- Getters & Setters -------------------

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }

    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }

    public LeaveBalance getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(LeaveBalance leaveBalance) {
        this.leaveBalance = leaveBalance;
    }
}
