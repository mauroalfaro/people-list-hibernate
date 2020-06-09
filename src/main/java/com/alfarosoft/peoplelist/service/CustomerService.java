package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.model.Customer;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session customerSession;

    public CustomerService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        customerSession = hibernateSessionFactory.buildSession();
    }

    public Customer getCustomer(String id){
        return new Customer();
    }

    public List<Customer> getCustomers(){
        customerSession.beginTransaction();
        List<Customer> customers = customerSession.createQuery("from Customers", Customer.class).list();
        return customers;
    }

    public Customer addCustomer (Customer customer) {
        customerSession.beginTransaction();
        customerSession.save(customer);
        customerSession.getTransaction().commit();
        return customer;
    }

    public Customer updateCustomer(String id, Customer customer){
        return customer;
    }

    public void removeCustomer (String id){
    }
}
