package com.zy17.exception;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-22
 * Time: 上午9:22
 */
public class UserAlreadyExsists extends Exception {
    public UserAlreadyExsists() {
        super("user already exsists");
    }
}
