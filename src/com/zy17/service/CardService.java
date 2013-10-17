package com.zy17.service;

import com.zy17.protobuf.domain.Eng;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:50
 */
public interface CardService {
    void add(Eng.Card card);

    Eng.CardList findCardList();

    Eng.Card findOneCardRandom();
}
