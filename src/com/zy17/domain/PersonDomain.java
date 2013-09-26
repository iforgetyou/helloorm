package com.zy17.domain;


import com.google.protobuf.InvalidProtocolBufferException;
import com.zy17.protobuf.domain.AddressBookProtos;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;


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
public class PersonDomain extends Base {
    AddressBookProtos.Person person;
    private String name;
    byte[] bytes;

//    public PersonDomain() {
//        logger.debug("no args constructor");
//    }

    public PersonDomain(AddressBookProtos.Person person) {
        this.setPerson(person);
        this.name = person.getName();
    }

//    @PreGet(kinds = {"PersonDomain"})
//    public void onSave(PreGetContext context) {
//        logger.debug("PrePersist");
//        this.setBytes(pbfSerialize(person));
//    }
//
//    @PostPut(kinds = {"PersonDomain"})
//    public void onLoad(PutContext context) {
//        logger.debug("PostLoad");
//        try {
//            person = pbfDeserialize(this.getBytes());
//        } catch (InvalidProtocolBufferException e) {
//            logger.error("onLoad", e);
//        }
//    }

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

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }


    public AddressBookProtos.Person getPerson() {
        try {
            this.person = this.pbfDeserialize(this.bytes);
        } catch (InvalidProtocolBufferException e) {
            logger.error("ProtocolBuffer反序列化失败", e);
        }
        return this.person;
    }

    public void setPerson(AddressBookProtos.Person person) {
        this.setBytes(this.pbfSerialize(person));
        this.person = person;
    }
}
