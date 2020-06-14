package com.alfarosoft.peoplelist.service;

import com.alfarosoft.peoplelist.database.HibernateSessionFactory;
import com.alfarosoft.peoplelist.exception.PeopleListException;
import com.alfarosoft.peoplelist.model.Store;
import org.hibernate.Session;

import java.util.List;

public class StoreService {
    private HibernateSessionFactory hibernateSessionFactory;
    private Session storeSession;

    public StoreService(HibernateSessionFactory hibernateSessionFactory) throws Exception {
        this.hibernateSessionFactory = hibernateSessionFactory;
        storeSession = hibernateSessionFactory.buildSession();
    }

    public Store getStore (String id){
        storeSession.beginTransaction();
        Store store = (Store) storeSession.createQuery("SELECT * FROM Stores WHERE Id=" + id).uniqueResult();
        storeSession.getTransaction().commit();
        if(store != null){
            return store;
        } else {
            throw new PeopleListException("Store with id " + id + " was not found");
        }
    }

    public List<Store> getStores() {
        storeSession.beginTransaction();
        List<Store> storeList = storeSession.createQuery("from Stores", Store.class).list();
        return storeList;
    }

    public Store addStore (Store store){
        storeSession.beginTransaction();
        storeSession.save(store);
        storeSession.getTransaction().commit();
        return store;
    }

    public Store updateStore (String id, Store store){
        Store storeRetrieved = this.getStore(id);
        storeSession.beginTransaction();
        storeRetrieved.setStoreName(store.getStoreName());
        storeRetrieved.setAddress(store.getAddress());
        storeSession.update(storeRetrieved);
        storeSession.getTransaction().commit();
        return storeRetrieved;
    }

    public void removeStore (String id){
        Store storeRetrieved = this.getStore(id);
        storeSession.delete(storeRetrieved);
        storeSession.getTransaction().commit();
    }
}
