package com.zy17.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:22
 */
public class BaseDaoImpl {
    protected PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactional");
}
