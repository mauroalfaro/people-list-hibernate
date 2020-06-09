package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    public EmployeeService() {
    }

    public Employee getEmployee(String id){
        return new Employee();
    }

    public List<Employee> getEmployees() {
        return new ArrayList<Employee>();
    }

    public Employee addEmployee (Employee employee){
        return employee;
    }

    public Employee updateEmployee (String id, Employee employee){
        return employee;
    }

    public void removeEmployee (String id){

    }
}
