package com.zy17.controller;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.zy17.controller.protobufview.PBMessageConverter;
import com.zy17.protobuf.domain.Eng;
import com.zy17.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    void createCard(@RequestBody Eng.Card card) {
        this.cardService.add(card);
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Eng.Card getOneRandomCard(RedirectAttributes redirectAttrs, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return cardService.findOneCardRandom();
    }

    /**
     * 获取所有cardlist
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Eng.CardList getCards() {
        return cardService.findCardList();
    }

}