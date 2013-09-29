package com.zy17.dao;

import com.zy17.protobuf.domain.AddressBookProtos;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-25
 * Time: 上午9:26
 */
public interface PersonDao {
    void insert(AddressBookProtos.Person person);
    List<byte[]> findAllPerson();
}
