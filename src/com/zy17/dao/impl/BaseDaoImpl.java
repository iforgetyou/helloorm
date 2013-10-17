package com.zy17.dao.impl;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.zy17.domain.Base;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.util.*;

//import net.sf.jsr107cache.*;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:22
 */
public class BaseDaoImpl<T extends Base> {
    final static Logger logger = LoggerFactory
            .getLogger(BaseDaoImpl.class);
    @Autowired
    protected PersistenceManagerFactory persistenceManagerFactory;
    protected BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    //    Cache cache;
    MemcacheService memcache = MemcacheServiceFactory.getMemcacheService();
    List<String> keyList=new ArrayList<String>();

    protected String DEFAULT_QUERY = "select from " + this.getPersistentClass().getName();

    public List<T> findAll() {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();

        try {
            List<T> list = (List<T>) pm.newQuery(DEFAULT_QUERY).execute();
//            for (T t : list) {
//                memcache.put(t.getEncodedKey(), t);
//            }
            return (List<T>) pm.detachCopyAll(list);
        } finally {
            pm.close();
        }
    }

    public T findOne(String messageid) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            return pm.getObjectById(this.getPersistentClass(), messageid);
        } finally {
            pm.close();
        }
    }

    public T findOneRandom() {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            int index = RandomUtils.nextInt(keyList.size());
            String key = keyList.get(index);
            return (T) memcache.get(key);
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
            keyList.add(base.getEncodedKey());
            memcache.put(base.getEncodedKey(), base);
        } finally {
            pm.close();
        }

        return base;
    }


    public void delete(String messageid) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            pm.deletePersistent(pm.getObjectById(this.getPersistentClass(), messageid));
            memcache.delete(messageid);
        } finally {
            pm.close();
        }
    }


    public Class<T> getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


}
