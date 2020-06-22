package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.exception.PeopleListException;
import com.alfarosoft.peoplelist.model.Employee;
import com.alfarosoft.peoplelist.model.Store;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public class StoreService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session storeSession;

    public StoreService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        storeSession = hibernateSessionFactory.buildSession();
    }

    public Store getStore (String id){
        return retrieveStoreFromDatabase(id);
    }

    public List<Store> getStores() {
        storeSession.beginTransaction();
        List<Store> storeList = storeSession.createQuery("from Store", Store.class).list();
        storeSession.getTransaction().commit();
        return storeList;
    }

    public Store addStore (Store store){
        storeSession.beginTransaction();
        storeSession.save(store);
        storeSession.getTransaction().commit();
        return store;
    }

    public Store updateStore (String id, Store store){
        Store storeRetrieved = retrieveStoreFromDatabase(id);
        storeRetrieved.setStoreName(store.getStoreName());
        storeRetrieved.setAddress(store.getAddress());
        storeSession.update(storeRetrieved);
        storeSession.getTransaction().commit();
        return storeRetrieved;
    }

    public void removeStore (String id){
        storeSession.delete(retrieveStoreFromDatabase(id));
        storeSession.getTransaction().commit();
    }

    private Store retrieveStoreFromDatabase (String id){
        Store storeRetrieved;
        try {
            storeSession.beginTransaction();
            Query selectQuery = storeSession.createQuery("from Store WHERE Id=:paramId");
            selectQuery.setParameter("paramId", id);
            storeRetrieved = (Store) selectQuery.uniqueResult();
            return storeRetrieved;
        } catch (EntityNotFoundException e){
            throw new PeopleListException("Employee with id " + id + " was not found", 404);
        }
    }
}
