import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Service {

    public void doSmthInService() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonPointer empty = JsonPointer.empty();  // JsonPointer is absent in the lowest version 2.0.6
        System.out.println("===" + empty);

        JsonParser arrayParser = objectMapper.createNonBlockingByteArrayParser();
        Object object = arrayParser.getNumberValueDeferred(); // method only in new version 2.15
    }
}
