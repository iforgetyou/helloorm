package com.zy17.dao.impl;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:17
 */
public class PMFHelper {
    private PersistenceManagerFactory INSTANCE;

    public void init() {
        INSTANCE = JDOHelper.getPersistenceManagerFactory("transactional");
    }

    public PersistenceManagerFactory get() {
        return INSTANCE;
    }
}
