package com.zy17.dao;

import com.zy17.domain.EngUserDomain;
import com.zy17.protobuf.domain.Eng;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-18
 * Time: 上午11:00
 */
public interface UserDao {
    void add(EngUserDomain user);

    Eng.User findUser(EngUserDomain engUserDomain);

    Eng.User findUserByThirdpart(String openId, Eng.PlatformType platformType);
}
