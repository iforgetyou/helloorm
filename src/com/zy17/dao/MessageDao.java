package com.zy17.dao;

import com.zy17.domain.TextMessage;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-18
 * Time: 下午5:04
 */
public interface MessageDao {
    void insert(TextMessage message);
}
