import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.logging.Level;
import java.util.logging.Logger;

public class NodeMethods {
    private static final Logger logger = Logger.getLogger(NodeMethods.class.getName());
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void objectNodeToJsonExample() {
        ObjectNode node = mapper.createObjectNode();
        node.put("brand", "Asus");
        node.put("model", "Zenbook 10");
        node.put("retailPrice", 60000L);

        try {
            String jsonStr = mapper.writeValueAsString(node);
            System.out.println(jsonStr);
        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }

    public static void jsonToJsonNodeExample() {
        String jsonStr = "{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900}";

        try {
            JsonNode rootNode = mapper.readTree(jsonStr);
            String brand = rootNode.path("brand").asText();
            String model = rootNode.path("model").asText();
            String price = rootNode.path("retailPrice").asText();

            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Price: " + price);

        } catch (JsonProcessingException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }
    
    public static void objectToJsonNodeExample() {
        Laptop laptop = new Laptop();
        laptop.setBrand("Gigabyte");
        laptop.setModel("Aorus A3");
        laptop.setPrice(80000L);
        
        JsonNode node = mapper.convertValue(laptop, JsonNode.class);
        
        try {
            String brand = node.get("brand").asText();
            String model = node.get("model").asText();
            long price = node.get("retailPrice").asLong();
            
//            node.get() method produces NullPointerException when a non-existing key is used.
//            node.path() is a safer alternative.
            String nonExistingKey = node.get("nonExistingKey").asText();
//            String nonExistingKey = node.path("nonExistingKey").asText();

            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Price: " + price);
            System.out.println("nonExistingKey: " + nonExistingKey);
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Error occurred.", e);
        }
    }
}
