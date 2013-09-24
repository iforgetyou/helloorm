package com.zy17.domain;


import com.google.protobuf.InvalidProtocolBufferException;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;


/**
 * Created with IntelliJ IDEA.
 * User: cat
 * Date: 13-9-24
 * Time: 下午10:49
 * To change this template use File | Settings | File Templates.
 */
public class PersonDomain extends Base {
    @Transient
    AddressBookProtos.Person person;
    private String name;
    byte[] protoPersonBuf;

    @PrePersist
    void onSave() {
        protoPersonBuf = pbfSerialize(person);
        // 可能需要索引或者单独处理的字段，放到gae数据库中
        name = person.getName();
    }

    @PostLoad
    void onLoad() throws InvalidProtocolBufferException {
        person = pbfDeserialize(protoPersonBuf);
    }

    private byte[] pbfSerialize(AddressBookProtos.Person person) {
         return person.toByteArray();
    }

    private AddressBookProtos.Person pbfDeserialize(byte[] protoPersonBuf) throws InvalidProtocolBufferException {
               return AddressBookProtos.Person.parseFrom(protoPersonBuf);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
