package com.example.Leave.service;


import java.util.List;

import com.example.Leave.Entity.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee findByEmail(String email);

    Employee findById(Long id);

    List<Employee> getAllEmployees();
}
