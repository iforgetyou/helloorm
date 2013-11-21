package com.zy17.dao.impl;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.UploadOptions;
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
        //限制每个blob大小为3M
        UploadOptions uploadOptions = UploadOptions.Builder.withMaxUploadSizeBytesPerBlob(3 * 1024 * 1024);
        String url = blobstoreService.createUploadUrl("/blobs",uploadOptions).replace("appspot", "appsp0t");
        return url;
    }
}
