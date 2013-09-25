package com.zy17.dao;

import com.zy17.domain.AddressBookProtos;
import com.zy17.domain.PersonDomain;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-25
 * Time: 上午9:26
 */
public interface PersonDomainDao {
    void insert(AddressBookProtos.Person person);
    List<AddressBookProtos.Person> findAllPerson();
}
