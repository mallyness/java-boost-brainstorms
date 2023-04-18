import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Repository repository = new Repository(); // -> data - 2.0.6
        repository.doSmthInRepository();

        try {
            Service service = new Service(); // 2.15
            service.doSmthInService();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonParser arrayParser = objectMapper.createNonBlockingByteArrayParser();
            Object object = arrayParser.getNumberValueDeferred(); // 2.15
            System.out.println(object);

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
