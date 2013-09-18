package com.zy17.dao.impl;

import com.zy17.dao.MessageDao;
import com.zy17.domain.TextMessage;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:07
 */
@Repository
public class MessageDaoImpl extends BaseDaoImpl implements MessageDao {
    @Override
    public void insert(TextMessage message) {
        PersistenceManager pm = PMF.getPersistenceManager();
        try {
            pm.makePersistent(message);
        } finally {
            pm.close();
        }
    }
}
