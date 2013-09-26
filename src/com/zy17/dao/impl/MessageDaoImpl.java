package com.zy17.dao.impl;

import com.zy17.dao.MessageDao;
import com.zy17.domain.TextMessage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:07
 */
@Repository
public class MessageDaoImpl extends BaseDaoImpl<TextMessage> implements MessageDao {

    @Override
    public void insert(TextMessage message) {
        super.insertOne(message);
    }

    @Override
    public List<TextMessage> findAllMessages() {
        return super.findAll();
    }

    @Override
    public void delete(String messageid) {
        super.delete(messageid);
    }

    @Override
    public TextMessage findMessage(String messageid) {
        return super.findOne(messageid);
    }
}
