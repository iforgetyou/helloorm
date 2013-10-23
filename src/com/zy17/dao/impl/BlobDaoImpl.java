package com.zy17.dao.impl;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.zy17.dao.BlobDao;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-23
 * Time: 上午9:04
 */
@Repository
public class BlobDaoImpl implements BlobDao {
    protected BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public String getUploadUrl() {
        String url = blobstoreService.createUploadUrl("/upload").replace("appspot", "appsp0t");
        return url;
    }
}
