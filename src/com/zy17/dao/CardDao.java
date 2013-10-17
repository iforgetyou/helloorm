package com.zy17.dao;

import com.zy17.protobuf.domain.Eng;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:57
 */
public interface CardDao {
    Eng.Card findOneCardRandom();

    Eng.CardList findCardList();

    void add(Eng.Card card);
}
