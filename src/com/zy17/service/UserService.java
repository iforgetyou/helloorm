package com.zy17.service;

import com.zy17.protobuf.domain.EngUser;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-17
 * Time: 上午9:50
 */

public interface UserService {
    void add(EngUser user);
}
