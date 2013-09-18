package com.zy17.controller;

import com.zy17.domain.TextMessage;
import com.zy17.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    //    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    TextMessage getMessage() {
        TextMessage textMessage = new TextMessage();
        textMessage.setName("hello");
        return textMessage;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    TextMessage createTopic(@RequestBody @Valid TextMessage message) {
        Date date = new Date();
        messageService.add(message);
        return message;
    }
}
