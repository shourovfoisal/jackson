import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonToObject {
    private static final Logger logger = Logger.getLogger(JsonToObject.class.getName());
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void plainObject() {
        String jsonStr = "{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900}";

        try {
            Laptop laptop = mapper.readValue(jsonStr, Laptop.class);
            System.out.println(laptop);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }

    public static void arrayOfObjects() {
        String jsonStr = "[{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900},{\"brand\":\"Lenovo\",\"model\":\"Slim3i\",\"retailPrice\":74500}]";

        try {
            Laptop[] laptops = mapper.readValue(jsonStr, Laptop[].class);
            for(Laptop l : laptops) {
                System.out.println(l.toString());
            }
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }

    public static void arrayOfMaps() {
        String jsonStr = "[{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900},{\"brand\":\"Lenovo\",\"model\":\"Slim3i\",\"retailPrice\":74500}]";

        try {
            Map<String, Object>[] props = mapper.readValue(jsonStr, new TypeReference<>() {});

            for(Map<String, Object> prop : props) {
                prop.keySet().forEach(key -> {
                    System.out.println(key + ": " + prop.get(key));
                });
                System.out.println();
            }
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }

    public static void listOfMaps() {
        String jsonStr = "[{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900},{\"brand\":\"Lenovo\",\"model\":\"Slim3i\",\"retailPrice\":74500}]";

        try {
            List<Map<String, Object>> props = mapper.readValue(jsonStr, new TypeReference<>() {});

            props.forEach(prop -> {
                prop.keySet().forEach(key -> {
                    System.out.println(key + ": " + prop.get(key));
                });
                System.out.println();
            } );
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }
}
