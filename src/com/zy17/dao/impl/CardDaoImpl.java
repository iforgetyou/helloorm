package com.zy17.dao.impl;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.zy17.controller.BlobController;
import com.zy17.controller.CardController;
import com.zy17.dao.CardDao;
import com.zy17.domain.CardDomain;
import com.zy17.protobuf.domain.Eng;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:59
 */
@Repository
public class CardDaoImpl extends BaseDaoImpl<CardDomain> implements CardDao {
    private static final Logger log = Logger.getLogger(BlobController.class.getName());
    private MemcacheService memcacheService = MemcacheServiceFactory.getMemcacheService();

    @Override
    public Eng.Card findOneCardRandom() {
        return super.findOneRandom().getCard();
    }

    @Override
    public Eng.CardList findCardList() {
        Eng.CardList cardlist = (Eng.CardList) memcacheService.get("cardlist");// read from cache
        if (cardlist == null) {
            Eng.CardList.Builder builder = Eng.CardList.newBuilder();
            List<CardDomain> all = findAll();
            for (CardDomain cardDomain : all) {
                builder.addCard(cardDomain.getCard());
            }
            cardlist = builder.build();
            log.info("从数据库获取，放入缓存");
            memcacheService.put("cardlist", cardlist);
        } else {
            log.info("从缓存获取数据");
        }
        return cardlist;
    }

    @Override
    public void add(Eng.Card card) {
        Eng.CardList cardlist = (Eng.CardList) memcacheService.get("cardlist");// read from cache
        if (cardlist!=null){
            Eng.CardList newCardList = cardlist.toBuilder().addCard(card).build();
            memcache.delete("cardlist");
            memcache.put("cardlist", newCardList);
            log.info("缓存放入新数据");
        }
        super.insertOne(new CardDomain(card));
    }
}
