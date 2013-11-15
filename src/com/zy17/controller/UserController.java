package com.zy17.controller;

import com.zy17.protobuf.domain.Eng;
import com.zy17.protobuf.domain.EngUser;
import com.zy17.service.CardService;
import com.zy17.service.UserService;
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

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createUser(@RequestBody EngUser user) {
        this.userService.add(user);
    }
}