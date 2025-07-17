import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//        arrayOfObjects(mapper);
//        arrayOfMaps(mapper);
//        listOfMaps(mapper);
//        plainObject(mapper);
        objectToJson(mapper);
    }


    private static void plainObject(ObjectMapper mapper) {
        String jsonStr = "{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900}";

        try {
            Laptop laptop = mapper.readValue(jsonStr, Laptop.class);
            System.out.println(laptop);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void objectToJson(ObjectMapper mapper) {
        Laptop laptop = new Laptop();
        laptop.setModel("DM-01");
        laptop.setBrand("Dell");
        laptop.setPrice(75000L);

        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, laptop);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String string = writer.toString();
        System.out.println(string);
    }

    private static void arrayOfObjects(ObjectMapper mapper) {
        String jsonStr = "[{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900},{\"brand\":\"Lenovo\",\"model\":\"Slim3i\",\"retailPrice\":74500}]";

        try {
            Laptop[] laptops = mapper.readValue(jsonStr, Laptop[].class);
            for(Laptop l : laptops) {
                System.out.println(l.toString());
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void arrayOfMaps(ObjectMapper mapper) {
        String jsonStr = "[{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900},{\"brand\":\"Lenovo\",\"model\":\"Slim3i\",\"retailPrice\":74500}]";

        try {
            Map<String, String>[] props = mapper.readValue(jsonStr, new TypeReference<>() {});

            for(Map<String, String> prop : props) {
                prop.keySet().forEach(key -> {
                    System.out.println(key + ": " + prop.get(key));
                });
                System.out.println();
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listOfMaps(ObjectMapper mapper) {
        String jsonStr = "[{\"brand\":\"HP\",\"model\":\"M22f\",\"retailPrice\":13900},{\"brand\":\"Lenovo\",\"model\":\"Slim3i\",\"retailPrice\":74500}]";

        try {
            List<Map<String, String>> props = mapper.readValue(jsonStr, new TypeReference<>() {});

            props.forEach(prop -> {
                prop.keySet().forEach(key -> {
                    System.out.println(key + ": " + prop.get(key));
                });
                System.out.println();
            } );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
