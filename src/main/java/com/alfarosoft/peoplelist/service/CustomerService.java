package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.exception.PeopleListException;
import com.alfarosoft.peoplelist.model.Customer;
import org.hibernate.Session;

import java.util.List;

public class CustomerService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session customerSession;

    public CustomerService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        customerSession = hibernateSessionFactory.buildSession();
    }

    public Customer getCustomer(String id){
        customerSession.beginTransaction();
        Customer customer = (Customer) customerSession.createQuery("SELECT * FROM Customers WHERE Id=" + id).uniqueResult();
        customerSession.getTransaction().commit();
        if(customer != null){
            return customer;
        } else {
            throw new PeopleListException("Customer with id " + id + " was not found");
        }
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
        Customer customerRetrieved = this.getCustomer(id);
        customerSession.beginTransaction();
        customerRetrieved.setName(customer.getName());
        customerRetrieved.setSurname(customer.getSurname());
        customerRetrieved.setAddress(customer.getAddress());
        customerRetrieved.setLoyaltyId(customer.getLoyaltyId());
        customerRetrieved.setEmail(customer.getEmail());
        customerRetrieved.setPhone(customer.getPhone());
        customerSession.update(customerRetrieved);
        customerSession.getTransaction().commit();
        return customerRetrieved;
    }

    public void removeCustomer (String id){
        Customer customerRetrieved = this.getCustomer(id);
        customerSession.delete(customerRetrieved);
        customerSession.getTransaction().commit();
    }
}
