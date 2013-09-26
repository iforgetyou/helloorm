//package com.zy17.dao.impl;
//
//import com.google.appengine.api.datastore.*;
//import com.google.appengine.api.memcache.MemcacheService;
//import com.google.appengine.api.memcache.MemcacheServiceFactory;
//import com.google.appengine.api.taskqueue.Queue;
//import com.google.appengine.api.taskqueue.QueueFactory;
//import com.google.appengine.api.urlfetch.URLFetchService;
//import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
//import com.google.appengine.api.users.UserService;
//import com.google.appengine.api.users.UserServiceFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//class ManyCallbacks {
//    final static Logger logger = LoggerFactory
//            .getLogger(ManyCallbacks.class);
//
//
//    @PreQuery(kinds = {"PersonDomain"})
//    public void preQuery(PreQueryContext context) {
//        UserService users = UserServiceFactory.getUserService();
//        logger.debug("preQuery");
//    }
//
//    @PrePut(kinds = {"PersonDomain", "kind2"})
//    void foo(PutContext context) {
//      MemcacheService ms = MemcacheServiceFactory.getMemcacheService();
//        logger.debug("PrePut");
//    }
//
//
//    @PostPut(kinds = {"PersonDomain", "kind2"})
//    void baz(PutContext context) {
//        Queue q = QueueFactory.getDefaultQueue();
//        logger.debug("PostPut");
//        // ...
//    }
//
//    @PostDelete(kinds = {"PersonDomain"})
//    void yam(DeleteContext context) {
//        URLFetchService us = URLFetchServiceFactory.getURLFetchService();
//        logger.debug("PostDelete");
//        // ...
//    }
//}