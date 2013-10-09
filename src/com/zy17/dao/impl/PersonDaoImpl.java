package com.zy17.dao.impl;

import com.zy17.dao.PersonDao;
import com.zy17.domain.PersonDomain;
import com.zy17.protobuf.domain.AddressBookProtos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-26
 * Time: 下午3:30
 */
@Repository
public class PersonDaoImpl extends BaseDaoImpl<PersonDomain> implements PersonDao {
    final static Logger logger = LoggerFactory
            .getLogger(PersonDaoImpl.class);
    @Override
    public void insert(AddressBookProtos.Person person) {
        super.insertOne(new PersonDomain(person));
        logger.debug("insert success");
    }

    @Override
    public List<byte[]> findAllPersonBytes() {
        ArrayList<byte[]> persons = new ArrayList<byte[]>();
        List<PersonDomain> all = findAll();
        logger.debug("db find personDomains",all.size());
        for (PersonDomain personDomain : all) {
            persons.add(personDomain.getBytes());
        }
        return persons;
    }

    @Override
    public AddressBookProtos.PersonList findPersonList() {
        AddressBookProtos.PersonList.Builder builder = AddressBookProtos.PersonList.newBuilder();
        List< PersonDomain > all = findAll();
        for (PersonDomain personDomain : all) {
            builder.addPerson(personDomain.getPerson());
        }
        return builder.build();
    }

    @Override
    public void deletePerson(String personName) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        try {
            Query q = pm.newQuery(getPersistentClass());
            q.setFilter("name == personName");
            q.declareParameters("String personName");
            q.deletePersistentAll(personName);
        } finally {
            pm.close();
        }
    }
}