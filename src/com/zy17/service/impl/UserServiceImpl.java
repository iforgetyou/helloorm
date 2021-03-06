package com.zy17.service.impl;

import com.zy17.dao.UserDao;
import com.zy17.domain.EngUserDomain;
import com.zy17.exception.UserAlreadyExsists;
import com.zy17.protobuf.domain.Eng;
import com.zy17.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-15
 * Time: 下午5:19
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Override
    public void add(Eng.User user) throws UserAlreadyExsists {
        if (dao.findUserByEmail(user.getEmail())) {
            throw new UserAlreadyExsists();
        }
        dao.add(new EngUserDomain(user));
    }


    @Override
    public Eng.User login(Eng.User user) {
        return dao.findUser(new EngUserDomain(user));
    }

    @Override
    public Eng.User login(Eng.ThirdPartUser user) {
        return dao.findUserByThirdpart(user.getOpenId(), user.getPlatformType());
    }

    @Override
    public Eng.User findByNameAndPwd(String key, String credentials) {
        return null;
    }

    @Override
    public boolean checkusername(String username) {
        return dao.findUserByEmail(username);
    }
}
