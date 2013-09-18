package com.zy17.dao.impl;

import com.zy17.domain.Base;
import com.zy17.domain.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.lang.reflect.ParameterizedType;
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


    protected String DEFAULT_QUERY = "select from " + TextMessage.class.getName();

    public List<T> findAll() {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            System.out.println(DEFAULT_QUERY);
            return (List<T>) pm.newQuery(DEFAULT_QUERY).execute();
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


    public Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
