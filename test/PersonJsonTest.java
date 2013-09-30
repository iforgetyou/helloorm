import com.google.appengine.repackaged.com.google.protobuf.ByteString;
import com.google.protobuf.Message;
import com.zy17.protobuf.domain.AddressBookProtos;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-24
 * Time: 下午3:42
 */
public class PersonJsonTest {

    @Test
    public  void protobufBytesConvert() throws IOException {
        ObjectMapper mapper;
        mapper = new ObjectMapper(); // can reuse, share globally
        mapper.configure(SerializationConfig.Feature.DEFAULT_VIEW_INCLUSION, true);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mapper.writerWithDefaultPrettyPrinter();
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
        for (byte aByte : bytes) {
            System.out.print(aByte);
        }
        String str = "CghKb2huIERvZRDSCRoQamRvZUBleGFtcGxlLmNvbSIMCgg1NTUtNDMyMRAB";
        Byte.parseByte(str);
        byte[] bytes1 = str.getBytes("utf-8");
        System.out.println("str:"+new String(bytes,"utf-8"));
        AddressBookProtos.Person personCopy = AddressBookProtos.Person.parseFrom(bytes1);
        assert(personCopy.getName().equals(john.getName()));
        assert(personCopy.getEmail().equals(john.getEmail()));
        assert(personCopy.getPhone(0).equals(john.getPhone(0)));
    }

    @Test
    public void MediaTest(){
        System.out.println();
    }
}
