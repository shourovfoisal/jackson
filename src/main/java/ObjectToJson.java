import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectToJson {
    private static final Logger logger = Logger.getLogger(ObjectToJson.class.getName());
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void writerExample() {
        Laptop laptop = new Laptop();
        laptop.setModel("DM-01");
        laptop.setBrand("Dell");
        laptop.setPrice(75000L);

        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, laptop);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
        String string = writer.toString();
        System.out.println(string);
    }

    public static void directStringExample() {
        Laptop laptop = new Laptop();
        laptop.setBrand("Lenovo");
        laptop.setModel("Slim 3i");
        laptop.setPrice(35000L);

        try {
            String content = mapper.writeValueAsString(laptop);
            System.out.println(content);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }
}
