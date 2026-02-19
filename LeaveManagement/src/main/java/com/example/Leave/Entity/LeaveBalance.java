package com.example.Leave.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leave_balance")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sickLeave;
    private int earnedLeave;

    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

    // ------------------- Constructors -------------------

    public LeaveBalance() {
        this.sickLeave = 12;
        this.earnedLeave = 18;
    }

    public LeaveBalance(Long id, int sickLeave, int earnedLeave, Employee employee) {
        this.id = id;
        this.sickLeave = sickLeave;
        this.earnedLeave = earnedLeave;
        this.employee = employee;
    }

    public LeaveBalance(Employee employee) {
        this.sickLeave = 12;
        this.earnedLeave = 18;
        this.employee = employee;
    }

    // ------------------- Getters & Setters -------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getEarnedLeave() {
        return earnedLeave;
    }

    public void setEarnedLeave(int earnedLeave) {
        this.earnedLeave = earnedLeave;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
