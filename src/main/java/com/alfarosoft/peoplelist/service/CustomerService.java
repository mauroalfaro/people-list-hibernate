package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.exception.PeopleListException;
import com.alfarosoft.peoplelist.model.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class CustomerService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session customerSession;

    public CustomerService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        customerSession = hibernateSessionFactory.buildSession();
    }

    public Customer getCustomer(String id){
        return retrieveCustomerFromDatabase(id);
    }

    public List<Customer> getCustomers(){
        customerSession.beginTransaction();
        List<Customer> customers = customerSession.createQuery("from Customer", Customer.class).list();
        customerSession.getTransaction().commit();
        return customers;
    }

    public Customer addCustomer (Customer customer) {
        customerSession.beginTransaction();
        customerSession.save(customer);
        customerSession.getTransaction().commit();
        return customer;
    }

    public Customer updateCustomer(String id, Customer customer){
        Customer customerRetrieved = retrieveCustomerFromDatabase(id);
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
        customerSession.delete(retrieveCustomerFromDatabase(id));
        customerSession.getTransaction().commit();
    }

    private Customer retrieveCustomerFromDatabase (String id){
        Customer customerRetrieved;
        try{
            customerSession.beginTransaction();
            Query selectQuery = customerSession.createQuery("from Customer WHERE Id=:paramId");
            selectQuery.setParameter("paramId", id);
            customerRetrieved = (Customer) selectQuery.uniqueResult();
            return customerRetrieved;
        } catch (EntityNotFoundException e){
            throw new PeopleListException("Customer with id " + id + " was not found", 404);
        }
    }
}
