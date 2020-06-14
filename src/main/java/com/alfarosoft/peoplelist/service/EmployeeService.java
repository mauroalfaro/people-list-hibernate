package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.exception.PeopleListException;
import com.alfarosoft.peoplelist.model.Customer;
import com.alfarosoft.peoplelist.model.Employee;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session employeeSession;

    public EmployeeService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        employeeSession = hibernateSessionFactory.buildSession();
    }

    public Employee getEmployee(String id){
        employeeSession.beginTransaction();
        Employee employee = (Employee) employeeSession.createQuery("SELECT * FROM Employees WHERE Id=" + id).uniqueResult();
        employeeSession.getTransaction().commit();
        if(employee != null){
            return employee;
        } else {
            throw new PeopleListException("Employee with id " + id + " was not found");
        }
    }

    public List<Employee> getEmployees() {
        employeeSession.beginTransaction();
        List<Employee> employeeList = employeeSession.createQuery("from Employees", Employee.class).list();
        return employeeList;
    }

    public Employee addEmployee (Employee employee){
        employeeSession.beginTransaction();
        employeeSession.save(employee);
        employeeSession.getTransaction().commit();
        return employee;
    }

    public Employee updateEmployee (String id, Employee employee){
        Employee employeeRetrieved = this.getEmployee(id);
        employeeSession.beginTransaction();
        employeeRetrieved.setName(employee.getName());
        employeeRetrieved.setSurname(employee.getSurname());
        employeeRetrieved.setAddress(employee.getAddress());
        employeeRetrieved.setEmail(employee.getEmail());
        employeeRetrieved.setPhone(employee.getPhone());
        employeeRetrieved.setDateHired(employee.getDateHired());
        employeeRetrieved.setActiveEmployee(employee.isActiveEmployee());
        employeeSession.update(employeeRetrieved);
        employeeSession.getTransaction().commit();
        return employeeRetrieved;
    }

    public void removeEmployee (String id){
        Employee employeeRetrieved = this.getEmployee(id);
        employeeSession.delete(employeeRetrieved);
        employeeSession.getTransaction().commit();
    }
}
