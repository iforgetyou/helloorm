package com.zy17.dao.objectifyimpl;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.LoadType;
import com.zy17.dao.PersonDomainDao;
import com.zy17.dao.impl.BaseDaoImpl;
import com.zy17.domain.AddressBookProtos;
import com.zy17.domain.PersonDomain;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-25
 * Time: 上午9:27
 */
@Repository
public class PersonDomainOfyDaoImpl  implements PersonDomainDao {
    static{
        ObjectifyService.register(PersonDomain.class);
    }
    @Override
    public void insert(AddressBookProtos.Person personDomain) {
        ObjectifyService.ofy().save().entity(new PersonDomain(personDomain));
    }

    @Override
    public List<AddressBookProtos.Person> findAllPerson() {
        List<PersonDomain> list = ObjectifyService.ofy().load().type(PersonDomain.class).list();

        List<AddressBookProtos.Person> persons = new ArrayList<AddressBookProtos.Person>(list.size());
        for (PersonDomain personDomain : list) {
            persons.add(personDomain.getPerson());
        }
        return persons;
    }
}
