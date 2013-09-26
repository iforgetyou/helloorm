package com.zy17.service;

import com.zy17.domain.PersonDomain;
import com.zy17.domain.TextMessage;
import com.zy17.protobuf.domain.AddressBookProtos;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:05
 */
public interface MessageService {
    void add(TextMessage message);

    List<TextMessage> getAllMessage();

    void delete(String messageid);

    TextMessage getMessage(String messageid);

    void add(AddressBookProtos.Person person);

    List<AddressBookProtos.Person>  findAllPerson();
}
