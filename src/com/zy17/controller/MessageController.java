package com.zy17.controller;

import com.zy17.domain.TextMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/message")
public class MessageController {

//    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    TextMessage getMessage() {
        TextMessage textMessage = new TextMessage();
        textMessage.setName("hello");
        return textMessage;
    }
}
