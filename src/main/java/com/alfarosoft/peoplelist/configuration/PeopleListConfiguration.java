package com.alfarosoft.peoplelist.configuration;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.service.CustomerService;
import com.alfarosoft.peoplelist.service.EmployeeService;
import com.alfarosoft.peoplelist.service.StoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class PeopleListConfiguration {

    @Bean
    @RequestScope
    public CustomerService customerService() throws Exception {
        return new CustomerService(hibernateSessionFactory());
    }

    @Bean
    @RequestScope
    public EmployeeService employeeService(){
        return new EmployeeService();
    }

    @Bean
    @RequestScope
    public StoreService storeService(){
        return new StoreService();
    }

    @Bean
    @RequestScope
    public HibernateSessionFactory hibernateSessionFactory(){
        return new HibernateSessionFactory();
    }
}
