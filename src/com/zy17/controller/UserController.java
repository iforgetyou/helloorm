package com.zy17.controller;

import com.zy17.protobuf.domain.Eng;
import com.zy17.service.UserService;
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
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //    本地用户注册
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createUser(@RequestBody Eng.User user) {
        this.userService.add(user);
    }

    //    第三方用户登录注册
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "thirdpart", method = RequestMethod.POST)
    public
    @ResponseBody
    void createUserByThirdpart(@RequestBody Eng.ThirdPartUser user) {
        this.userService.addByThirdpart(user);
    }

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


}