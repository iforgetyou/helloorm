import com.google.appengine.repackaged.com.google.protobuf.ByteString;
import com.zy17.protobuf.domain.AddressBookProtos;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-24
 * Time: 下午3:42
 */
public class PersonJsonTest {
    public static void main(String[] args) throws IOException {
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

        System.out.println(bytes.length);
    }
}
