package com.zy17.controller;

import com.zy17.dao.BlobDao;
import com.zy17.protobuf.domain.Eng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-23
 * Time: 上午8:50
 */
@Controller
@RequestMapping("/blobs")
public class BlobController {
    @Autowired
    BlobDao dao;

    /**
     * 获取上传blob路径
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Eng.BlobMessage getUploadUrl() {
        Eng.BlobMessage blobMessage = Eng.BlobMessage.newBuilder().setBlobUploadUrl(dao.getUploadUrl()).build();
        return blobMessage;
    }

    /**
     * 上传blob，暂时由客户端直接完成
     */
//    @RequestMapping(method = RequestMethod.POST)
//    public String  uploadBlob() {
//        return null;
//    }

}
