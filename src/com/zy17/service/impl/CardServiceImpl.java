package com.zy17.service.impl;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.zy17.dao.CardDao;
import com.zy17.protobuf.domain.Eng;
import com.zy17.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:51
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardDao dao;

    @Override
    public void add(Eng.Card card) {
        dao.add(card);
    }

    @Override
    public Eng.CardList findCardList() {
        return dao.findCardList();
    }

    @Override
    public Eng.Card findOneCardRandom() {
        return dao.findOneCardRandom();
    }
}
