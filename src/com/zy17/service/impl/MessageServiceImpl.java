package com.zy17.service.impl;

import com.zy17.dao.MessageDao;
import com.zy17.domain.TextMessage;
import com.zy17.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:06
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao dao;

    @Override
    public void add(TextMessage message) {
        dao.insert(message);
    }
}
