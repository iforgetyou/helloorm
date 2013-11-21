package com.zy17.domain;

import com.zy17.protobuf.domain.Eng;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-18
 * Time: 上午11:01
 */
@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class EngUserDomain extends Base {
    private String email;
    private String password;
    private String sinaOpenid;
    private String qqOpenid;
    private String weixinOpenid;
    public EngUserDomain(Eng.User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public Eng.User getUser() {
        return Eng.User.newBuilder()
                .setEmail(this.email)
                .setPassword(this.password)
                .build();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSinaOpenid() {
        return sinaOpenid;
    }

    public void setSinaOpenid(String sinaOpenid) {
        this.sinaOpenid = sinaOpenid;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }
}
