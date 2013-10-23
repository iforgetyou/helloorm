/* Copyright (c) 2009 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zy17.filter;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.zy17.controller.protobufview.PBMessageConverter;
import com.zy17.protobuf.domain.Eng;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class UploadPost extends HttpServlet {

    private BlobstoreService blobstoreService =
            BlobstoreServiceFactory.getBlobstoreService();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws
            IOException {

        Eng.PbBlobKeyList.Builder pbBlobKeysBuilder = Eng.PbBlobKeyList.newBuilder();

        Map<String, List<BlobKey>> uploads = blobstoreService.getUploads(req);
        Map<String, List<BlobInfo>> blobInfos = blobstoreService.getBlobInfos(req);
        System.out.println(blobInfos);
        for (String key : blobInfos.keySet()) {
            List<BlobInfo> blobInfosList = blobInfos.get(key);
            for (BlobInfo blobInfo : blobInfosList) {
                Eng.PbBlobKey.Builder blobkeyBuilder = Eng.PbBlobKey.newBuilder()
                        .setBlobkey(blobInfo.getBlobKey().getKeyString())
                        .setOldFileName(key)
                        .setFileName(blobInfo.getFilename())
                        .setContentType(blobInfo.getContentType())
                        .setCreateDate(blobInfo.getCreation().getTime())
                        .setFileSize(blobInfo.getSize());
                pbBlobKeysBuilder.addBlobkeys(blobkeyBuilder);
            }
        }


//      构建响应信息
        resp.setContentType(PBMessageConverter.CONTENTTYPE + "/" + PBMessageConverter.SUBTYPE);
        byte[] bytes = pbBlobKeysBuilder.build().toByteArray();
        resp.setContentLength(bytes.length);
        resp.getOutputStream().write(bytes);
    }
}
