package com.zy17.user;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-15
 * Time: 上午10:05
 */
public class User {
    private String userName;
    private String password;
    private int role;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
