import com.zy17.protobuf.domain.Eng;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class ApacheHttpRestClient {

    Logger log = LoggerFactory.getLogger(ApacheHttpRestClient.class);

    public HttpPost post;
    public HttpGet get;
    public HttpDelete delete;
    public DefaultHttpClient httpclient;
    Eng.Card card;
//        public String remoteUrl = "http://127.0.0.1:8080/";
    public String remoteUrl = "http://iforgetyou529.appsp0t.com/";

    @Before
    public void init() {
//        HttpClientBuilder.create()
        httpclient = new DefaultHttpClient();
        post = new HttpPost(remoteUrl);
        post.setHeader("Accept", "application/x-protobuf");
        post.setHeader("Content-Type", "application/x-protobuf");
        post.setHeader("AppKey", "dGVzdDp0ZXN0");
        post.setHeader("Authorization", "x");


        get = new HttpGet(remoteUrl);
        get.setHeader("Accept", "application/x-protobuf");
        get.setHeader("Content-Type", "application/x-protobuf");
        get.setHeader("AppKey", "dGVzdDp0ZXN0");
        get.setHeader("Authorization", "x");

        delete = new HttpDelete(remoteUrl);
        delete.setHeader("Accept", "application/x-protobuf");
        delete.setHeader("Content-Type", "application/x-protobuf");
        delete.setHeader("AppKey", "dGVzdDp0ZXN0");
        delete.setHeader("Authorization", "x");


        card = Eng.Card.newBuilder().setChiText("中文").setEngText("English" + new Date()).build();
    }

    @Test
    public void MultipartTest() throws Exception {
//        获取上传地址
        get.setURI(new URI(remoteUrl + "blobs"));
        HttpResponse response = httpclient.execute(get);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        byte[] bytes = IOUtils.toByteArray(content);
        Eng.BlobMessage blobMessage = Eng.BlobMessage.parseFrom(bytes);
        System.out.println(blobMessage);
        if (true){
            return;
        }
//        上传多媒体
        HttpPost multipartPost = new HttpPost();

        multipartPost.setURI(new URI(blobMessage.getBlobUploadUrl()));
        FileBody contentBody = new FileBody(new File("E:\\test2.png"), ContentType.create("image/png"), "test.png");
        FileBody soundContentBody = new FileBody(new File("E:\\test1.mp3"), ContentType.create("audio/mp3"), "test1.mp3");
//        ByteArrayBody contentBody = new ByteArrayBody(new byte[1024 ], ContentType.create("image/png"), "test.png");
        HttpEntity postEntity = MultipartEntityBuilder.create().addPart("image1", contentBody).addPart("sound", soundContentBody).build();
        multipartPost.addHeader("Content-Type", postEntity.getContentType().getValue());
        multipartPost.setEntity(postEntity);
        HttpResponse postResponse = httpclient.execute(multipartPost);
        byte[] bytes1 = IOUtils.toByteArray(postResponse.getEntity().getContent());
        Eng.MediaBlobInfoList mediaBlobInfoList = Eng.MediaBlobInfoList.parseFrom(bytes1);
        System.out.println(mediaBlobInfoList);

        Eng.Card.Builder builder = card.toBuilder();
        for (Eng.MediaBlobInfo mediaBlobInfo : mediaBlobInfoList.getMediaBlobInfosList()) {
            if (mediaBlobInfo.getContentType().contains("png")) {
                builder.setImage(Eng.PbImage.newBuilder().setMediaInfo(mediaBlobInfo));
            }
            if (mediaBlobInfo.getContentType().contains("mp3")) {
                builder.setSound(Eng.PbSound.newBuilder().setMediaInfo(mediaBlobInfo));
            }
        }
        System.out.println("send card" + builder.build().getChiText().toString());
        post.setURI(new URI(remoteUrl + "cards"));
//        post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
        post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
        HttpResponse response1 = httpclient.execute(post);
        org.apache.http.HttpEntity entity1 = response1.getEntity();

        if (entity1 != null) {
            EntityUtils.consume(entity1);
        }
        System.out.println(response1.getStatusLine().getStatusCode());
        assert (response1.getStatusLine().getStatusCode() == 201);
    }

    @Test
    public void testPostCard() throws URISyntaxException, IOException {
        post.setURI(new URI(remoteUrl + "cards"));
        post.setEntity(new ByteArrayEntity(card.toByteArray()));
        HttpResponse response = httpclient.execute(post);
        org.apache.http.HttpEntity entity = response.getEntity();

        if (entity != null) {
            EntityUtils.consume(entity);
        }
        assert (response.getStatusLine().getStatusCode() == 201);
    }

    @Test
    public void testGetOneCard() throws URISyntaxException, IOException {
        this.testPostCard();
        get.setURI(new URI(remoteUrl + "cards/random"));
        HttpResponse response = httpclient.execute(get);

        assert (response.getStatusLine().getStatusCode() == 200);


        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();


//        if (entity.isChunked()){
        //为了处理chunked数据，Todo 为了提升效率可以对非chunked 数据进行单独处理
        byte[] bytes = IOUtils.toByteArray(content);

        Eng.Card cart = Eng.Card.parseFrom(bytes);
        System.out.println(cart);
//        assert (list.getCardCount() != 0);
        if (entity != null) {
            EntityUtils.consume(entity);
        }
    }

    @Test
    public void testGetCard() throws URISyntaxException, IOException {
//        this.testPostCard();
        get.setURI(new URI(remoteUrl + "cards"));
        HttpResponse response = httpclient.execute(get);
//        org.apache.http.HttpEntity entity = response.getEntity();
        assert (response.getStatusLine().getStatusCode() == 200);


        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();


//        if (entity.isChunked()){
        //为了处理chunked数据，Todo 为了提升效率可以对非chunked 数据进行单独处理
        byte[] bytes = IOUtils.toByteArray(content);

        Eng.CardList list = Eng.CardList.parseFrom(bytes);
        log.debug("CardList: \n\r" + list.toString());
//        assert (list.getCardCount() != 0);
        if (entity != null) {
            EntityUtils.consume(entity);
        }
    }

    @Test
    public void getTest() throws URISyntaxException, IOException {
//        this.postTest();

        get.setURI(new URI(remoteUrl + "/cards"));
        HttpResponse response = httpclient.execute(get);
//        org.apache.http.HttpEntity entity = response.getEntity();
        assert (response.getStatusLine().getStatusCode() == 200);


        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();


//        if (entity.isChunked()){
        //为了处理chunked数据，Todo 为了提升效率可以对非chunked 数据进行单独处理
        byte[] bytes = IOUtils.toByteArray(content);
//        }else{
//            bytes=new byte[content.available()];
//            content.read(bytes);
//        }

//        AddressBookProtos.PersonList personList = AddressBookProtos.PersonList.parseFrom(bytes);
        Eng.Card card1 = Eng.Card.parseFrom(bytes);
        log.debug("card1: \n\r" + card1.toString());
        log.debug("中文: " + card1.getChiText());
        if (entity != null) {
            EntityUtils.consume(entity);
        }
    }
}