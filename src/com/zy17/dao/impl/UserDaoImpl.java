package com.zy17.dao.impl;

import com.zy17.dao.UserDao;
import com.zy17.domain.EngUserDomain;
import com.zy17.protobuf.domain.Eng;
import org.springframework.stereotype.Repository;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-11-18
 * Time: 上午11:00
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<EngUserDomain> implements UserDao {
    @Override
    public void add(EngUserDomain user) {
        super.insertOne(user);
    }

    @Override
    public Eng.User findUser(EngUserDomain engUserDomain) {
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        Query q = pm.newQuery(this.getPersistentClass(),
                "email == emailParam && password = passwordParam");
        q.declareParameters("String emailParam");
        q.declareParameters("String password");
        EngUserDomain user = (EngUserDomain) q.execute(engUserDomain.getEmail(), engUserDomain.getPassword());
        return user.getUser();
    }

    @Override
    public Eng.User findUserByThirdpart(String openId, Eng.PlatformType platformType) {
        String openidType = null;
        PersistenceManager pm = this.persistenceManagerFactory.getPersistenceManager();
        switch (platformType.getNumber()) {
            case Eng.PlatformType.SINA_VALUE:
                openidType = "sinaOpenid";
                break;
            default:
                return null;
        }
        Query q = pm.newQuery(this.getPersistentClass(),
                openidType + " == " + openId);
        EngUserDomain user = (EngUserDomain) q.execute();
        return user.getUser();
    }
}
