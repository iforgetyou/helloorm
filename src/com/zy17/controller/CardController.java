package com.zy17.controller;

import com.zy17.protobuf.domain.Eng;
import com.zy17.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/cards")
public class CardController {
    private static final Logger log = LoggerFactory.getLogger(CardController.class.getName());

    @Autowired
    private CardService cardService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createCard(@RequestBody Eng.Card card) {
        this.cardService.add(card);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Eng.Card getOneRandomCard(RedirectAttributes redirectAttrs, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return cardService.findOneCardRandom();
    }

    /**
     * 获取所有cardlist
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Eng.CardList getCards() {
        return cardService.findCardList();
    }

}