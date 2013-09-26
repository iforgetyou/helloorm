//package com.zy17.dao.objectifyimpl;
//
//import com.googlecode.objectify.Objectify;
//import com.googlecode.objectify.ObjectifyFactory;
//import com.zy17.dao.PersonDao;
//import com.zy17.domain.*;
//import com.zy17.protobuf.domain.AddressBookProtos;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
///**
// * Created with IntelliJ IDEA.
// * User: yan.zhang
// * Date: 13-9-25
// * Time: 上午9:27
// */
//@Repository
//public class PersonDomainOfyDaoImpl  implements PersonDao {
//    @Autowired
//    private ObjectifyFactory objectifyFactory;
//    Objectify objectify;
//    public void PersonDomainOfyDaoImpl(){
//         objectify = objectifyFactory.begin();
//    }
//
//    @Override
//    public void insert(AddressBookProtos.Person person) {
//        objectifyFactory.begin().save().entity(new PersonDomain(person));
////        MyEntity entity = new MyEntity();
////        objectify.save().entity(entity);
//    }
//
//    @Override
//    public List<AddressBookProtos.Person> findAllPerson() {
///*        List<PersonDomain> list = objectifyFactory.begin().load().type(PersonDomain.class).list();
//
//        List<AddressBookProtos.Person> persons = new ArrayList<AddressBookProtos.Person>(list.size());
//        for (PersonDomain personDomain : list) {
//            persons.add(personDomain.getPerson());
//        }*/
////        List<MyEntity> list = objectifyFactory.begin().load().type(MyEntity.class).list();
////        System.out.println(list.get(0));
////        return persons;
//        return null;
//    }
//}
