package com.zy17.dao.impl;

import com.google.appengine.api.datastore.KeyFactory;
import com.zy17.domain.Base;
import com.zy17.domain.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:22
 */
public class BaseDaoImpl<T extends Base> {
    @Autowired
    protected PersistenceManagerFactory persistenceManagerFactory;


//    protected String DEFAULT_QUERY = "select from " + TextMessage.class.getName();

    protected String DEFAULT_QUERY = "select from " + this.getPersistentClass().getName();

    public List<T> findAll() {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            List<T> list = (List<T>) pm.newQuery(DEFAULT_QUERY).execute();
//            list.size();
            List<T> ts = (List<T>) pm.detachCopyAll(list);
            return ts;
        } finally {
            pm.close();
        }
    }

    public T findOne(String messageid) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            T one =  pm.getObjectById(this.getPersistentClass(),messageid);
            return one;
        } finally {
            pm.close();
        }
    }

    public T insertOne(T base) {
        base.setCreatedAt(new Date());
        base.setUpdatedAt(new Date());
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            pm.makePersistent(base);
        } finally {
            pm.close();
        }

        return base;
    }


    public void delete(String messageid) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            pm.deletePersistent(pm.getObjectById(this.getPersistentClass(),messageid));
        } finally {
            pm.close();
        }
    }


    public Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
