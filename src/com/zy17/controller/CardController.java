package com.zy17.controller;

import com.zy17.protobuf.domain.Eng;
import com.zy17.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createPerson(@RequestBody Eng.Card card) {
        this.cardService.add(card);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public
    @ResponseBody
    Eng.Card getOneRandomCard() {
        return cardService.findOneCardRandom();
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Eng.CardList getCards() {
        return cardService.findCardList();
    }


}