package com.zy17.service.impl;

import com.zy17.dao.MessageDao;
import com.zy17.dao.PersonDomainDao;
import com.zy17.domain.AddressBookProtos;
import com.zy17.domain.PersonDomain;
import com.zy17.domain.TextMessage;
import com.zy17.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    @Autowired
    private PersonDomainDao personDomainDao;

    @Override
    public void add(TextMessage message) {
        dao.insert(message);
    }

    @Override
    public List<TextMessage> getAllMessage() {
        return this.dao.findAllMessages();
    }

    @Override
    public void delete(String messageid) {
        this.dao.delete(messageid);
    }

    @Override
    public TextMessage getMessage(String messageid) {
        return this.dao.findMessage(messageid);
    }

    @Override
    public void add(AddressBookProtos.Person person) {
        this.personDomainDao.insert(person);
    }

    @Override
    public   List<AddressBookProtos.Person>  findAllPerson() {
        return personDomainDao.findAllPerson();
    }
}
