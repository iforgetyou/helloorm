package com.zy17.dao.impl;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.zy17.dao.CardDao;
import com.zy17.domain.CardDomain;
import com.zy17.domain.PersonDomain;
import com.zy17.protobuf.domain.AddressBookProtos;
import com.zy17.protobuf.domain.Eng;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:59
 */
@Repository
public class CardDaoImpl extends BaseDaoImpl<CardDomain> implements CardDao {
    @Override
    public Eng.Card findOneCardRandom() {
        return super.findOneRandom().getCard();
    }

    @Override
    public Eng.CardList findCardList() {
        Eng.CardList.Builder builder =  Eng.CardList.newBuilder();
        List< CardDomain > all = findAll();
        for (CardDomain cardDomain : all) {
            builder.addCard(cardDomain.getCard());
        }
        return builder.build();
    }

    @Override
    public void add(Eng.Card card) {
        super.insertOne(new CardDomain(card));
    }
}
