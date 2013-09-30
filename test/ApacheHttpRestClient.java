import com.zy17.protobuf.domain.AddressBookProtos;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * This class is the same as the ApacheHttpRestClient2 class, but with
 * fewer try/catch clauses, and fewer comments.
 */
public class ApacheHttpRestClient {

    Logger log = LoggerFactory.getLogger(ApacheHttpRestClient.class);

    public HttpPost post;
    public HttpGet get;
    public DefaultHttpClient httpclient;

    public String remoteUrl="http://127.0.0.1:8080/";

    @Before
    public void init() {
//        HttpClientBuilder.create()
        httpclient = new DefaultHttpClient();
        post = new HttpPost(remoteUrl);
        post.setHeader("Accept", "application/x-protobuf");
        post.setHeader("Content-Type", "application/x-protobuf");

        get = new HttpGet(remoteUrl);
        get.setHeader("Accept", "application/x-protobuf");
        get.setHeader("Content-Type", "application/x-protobuf");

    }

    @Test
    public void postTest() throws IOException, URISyntaxException {
        post.setURI(new URI(remoteUrl+"message/person"));
        post.setEntity(new ByteArrayEntity(getPersonBytes()));
//      System.out.println(post.getEntity().getContent());
        CloseableHttpResponse response = httpclient.execute(post);
        org.apache.http.HttpEntity entity = response.getEntity();


        assert(response.getStatusLine().getStatusCode()==201);
//        System.out.println("post get result: " + response.getStatusLine().getStatusCode());
        if (entity != null) {
            EntityUtils.consume(entity);
        }
    }

    @Test
    public void getTest() throws URISyntaxException, IOException {
        this.postTest();

        get.setURI(new URI(remoteUrl+"message/person"));
        CloseableHttpResponse response = httpclient.execute(get);
        org.apache.http.HttpEntity entity = response.getEntity();
        assert(response.getStatusLine().getStatusCode()==200);


        Header firstHeader = response.getFirstHeader("Content-Length");

        InputStream content = entity.getContent();
        byte[] bytes = new byte[Integer.parseInt(firstHeader.getValue())];
        content.read(bytes);
        AddressBookProtos.PersonList personList = AddressBookProtos.PersonList.parseFrom(bytes);
        log.debug("persons: \n\r" + personList.toString());
        assert( personList.getPersonList().size()!=0);


        if (entity != null) {
            EntityUtils.consume(entity);
//            entity.consumeContent();
        }

    }

    private byte[] getPersonBytes() {
        AddressBookProtos.Person john =
                AddressBookProtos.Person.newBuilder()
                        .setId(1234)
                        .setName("John Doe")
                        .setEmail("jdoe@example.com")
                        .addPhone(
                                AddressBookProtos.Person.PhoneNumber.newBuilder()
                                        .setNumber("555-4321")
                                        .setType(AddressBookProtos.Person.PhoneType.HOME))
                        .build();
        byte[] bytes = john.toByteArray();
        return bytes;
    }

}