package test.com.example.GaeClient;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.zy17.domain.TextMessage;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: cat
 * Date: 13-9-21
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
public class TextMessageResponderFragmentTest  {


    @Test
    public void testGson(){

        String json="[\n" +
                "{\n" +
                "\"encodedKey\":\"ag1pZm9yZ2V0eW91NTI5chgLEgtURVhUTUVTU0FHRRiAgICAgIDwCgw\",\n" +
                "\"createdAt\":1379578826000,\n" +
                "\"updatedAt\":1379578826000,\n" +
                "\"name\":\"test\"\n" +
                "}\n" +
                "]";
        TextMessage.class.getClass();




        List<TextMessage> listfromJson = JSONUtils.getListfromJson(json, List<TextMessage>.getClass());
        for (TextMessage textMessage : listfromJson) {
            System.out.println(textMessage.getName());
        }
    }
}
