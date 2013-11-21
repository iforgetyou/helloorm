package com.zy17.service;

import com.zy17.protobuf.domain.Eng;
import com.zy17.protobuf.domain.Eng;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:50
 */

public interface UserService {
    void add( Eng.User  user);

    Eng.User  login( Eng.User  user);

    Eng.User  login( Eng.ThirdPartUser user);

    Eng.User findByNameAndPwd(String key, String credentials);
}
