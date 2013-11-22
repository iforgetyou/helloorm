package com.zy17.controller;

import com.zy17.dao.impl.BaseDaoImpl;
import com.zy17.exception.UserAlreadyExsists;
import com.zy17.protobuf.domain.Eng;
import com.zy17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/users")
public class UserController {
    final static Logger log = Logger.getLogger(BaseDaoImpl.class.getName());
    @Autowired
    private UserService userService;

    //    本地用户注册
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createUser(@RequestBody Eng.User user) throws UserAlreadyExsists {
        this.userService.add(user);
    }

    //    本地用户注册
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/email/{username}", method = RequestMethod.GET)
    public
    @ResponseBody
    void checkusername(@PathVariable("username") String username) {
        this.userService.checkusername(username);
    }


    //    第三方用户登录注册
//    @ResponseStatus(value = HttpStatus.CREATED)
//    @RequestMapping(value = "thirdpart", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    void createUserByThirdpart(@RequestBody Eng.ThirdPartUser user) {
//        this.userService.addByThirdpart(user);
//    }

    /*
   * 用户登录
   */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Eng.User login(@RequestBody Eng.User user) {
        return userService.login(user);
    }

    /*
    * 第三方用户登录
    */
    @RequestMapping(value = "/loginThirdpart", method = RequestMethod.POST)
    public Eng.User loginThirdpart(@RequestBody Eng.ThirdPartUser user) {
        return userService.login(user);
    }


    /**
     * Catch IOException and redirect to a 'personal' page.
     */
//    @ExceptionHandler(UserAlreadyExsists.class)
//    public ModelAndView handleIOException(UserAlreadyExsists ex) {
//        log.info("handleIOException - Catching: " + ex.getClass().getSimpleName());
//        return null;
//    }

}