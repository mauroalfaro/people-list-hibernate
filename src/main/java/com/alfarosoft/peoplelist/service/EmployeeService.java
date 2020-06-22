package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.exception.PeopleListException;
import com.alfarosoft.peoplelist.model.Customer;
import com.alfarosoft.peoplelist.model.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityNotFoundException;
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
        return retrieveEmployeeFromDatabase(id);
    }

    public List<Employee> getEmployees() {
        employeeSession.beginTransaction();
        List<Employee> employeeList = employeeSession.createQuery("from Employee", Employee.class).list();
        employeeSession.getTransaction().commit();
        return employeeList;
    }

    public Employee addEmployee (Employee employee){
        employeeSession.beginTransaction();
        employeeSession.save(employee);
        employeeSession.getTransaction().commit();
        return employee;
    }

    public Employee updateEmployee (String id, Employee employee){
        Employee employeeRetrieved = retrieveEmployeeFromDatabase(id);
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
        employeeSession.delete(retrieveEmployeeFromDatabase(id));
        employeeSession.getTransaction().commit();
    }

    private Employee retrieveEmployeeFromDatabase (String id){
        Employee employeeRetrieved;
        try{
            employeeSession.beginTransaction();
            Query selectQuery = employeeSession.createQuery("from Employee WHERE Id=:paramId");
            selectQuery.setParameter("paramId", id);
            employeeRetrieved = (Employee) selectQuery.uniqueResult();
            return employeeRetrieved;
        } catch (EntityNotFoundException e){
            throw new PeopleListException("Employee with id " + id + " was not found", 404);
        }
    }
}
