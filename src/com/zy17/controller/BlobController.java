package com.zy17.controller;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.zy17.controller.protobufview.PBMessageConverter;
import com.zy17.dao.BlobDao;
import com.zy17.protobuf.domain.Eng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-10-23
 * Time: 上午8:50
 */
@Controller
@RequestMapping("/blobs")
public class BlobController {
    private static final Logger log = Logger.getLogger(BlobController.class.getName());
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
        //可以向其他账号申请上传地址
        Eng.BlobMessage blobMessage = Eng.BlobMessage.newBuilder().setBlobUploadUrl(dao.getUploadUrl()).build();
        return blobMessage;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void mediaUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Eng.MediaBlobInfoList.Builder infoList = Eng.MediaBlobInfoList.newBuilder();

        Map<String, List<BlobInfo>> blobInfos = BlobstoreServiceFactory.getBlobstoreService().getBlobInfos(request);
        System.out.println(blobInfos);
        for (String key : blobInfos.keySet()) {
            List<BlobInfo> blobInfosList = blobInfos.get(key);
            for (BlobInfo blobInfo : blobInfosList) {
                String servingUrl = "";
                if (blobInfo.getContentType().contains("image")) {
                    servingUrl = ImagesServiceFactory.getImagesService().getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobInfo.getBlobKey()));
                } else {
                    servingUrl = request.getRequestURL().toString().replace("appspot", "appsp0t") + "/" + blobInfo.getBlobKey().getKeyString();
                }
                Eng.MediaBlobInfo.Builder blobkeyBuilder = Eng.MediaBlobInfo.newBuilder()
                        .setBlobkey(blobInfo.getBlobKey().getKeyString())
                        .setOldFileName(key)
                        .setFileName(blobInfo.getFilename())
                        .setContentType(blobInfo.getContentType())
                        .setCreateDate(blobInfo.getCreation().getTime())
                        .setFileSize(blobInfo.getSize()).setServUrl(servingUrl);
                infoList.addMediaBlobInfos(blobkeyBuilder);
            }
        }

//      构建响应信息
        response.setContentType(PBMessageConverter.CONTENTTYPE + "/" + PBMessageConverter.SUBTYPE);
        byte[] bytes = infoList.build().toByteArray();
        response.setContentLength(bytes.length);
        response.getOutputStream().write(bytes);
    }

    @RequestMapping(value = "/{blobkey}", method = RequestMethod.GET)
    public void mediaServe(@PathVariable String blobkey, HttpServletRequest request, HttpServletResponse response) throws IOException {
        long started = System.currentTimeMillis();
        BlobstoreServiceFactory.getBlobstoreService().serve(new BlobKey(blobkey), response);
        log.info("收到多媒体服务请求,耗时" + String.valueOf(System.currentTimeMillis() - started));
    }
}
