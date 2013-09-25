package com.zy17.domain;


import com.google.protobuf.InvalidProtocolBufferException;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
@EntitySubclass(index=true)
public class PersonDomain extends Base {
    @Transient
    @Ignore
    AddressBookProtos.Person person;
    @Persistent
    @Id
    private String name;
    @Persistent
    byte[] protoPersonBuf;

    public PersonDomain(AddressBookProtos.Person person) {
        this.person = person;
    }

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

    public byte[] getProtoPersonBuf() {
        return protoPersonBuf;
    }

    public void setProtoPersonBuf(byte[] protoPersonBuf) {
        this.protoPersonBuf = protoPersonBuf;
    }

    public AddressBookProtos.Person getPerson() {
        return person;
    }

    public void setPerson(AddressBookProtos.Person person) {
        this.person = person;
    }
}
