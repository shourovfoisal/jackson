import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TypeToType {
    private static final Logger logger = Logger.getLogger(TypeToType.class.getName());
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void objectToMapExample () {
        Laptop laptop = new Laptop();
        laptop.setBrand("Google");
        laptop.setModel("Chromebook 2");
        laptop.setPrice(56000L);

        Map<String, Object> map = mapper.convertValue(laptop, new TypeReference<>() {});
        map.keySet().forEach(key -> {
            System.out.println(key + ": " + map.get(key));
        });
    }

    public static void mapToObjectExample() {
        Map<String, Object> map = new HashMap<>();
        map.put("brand", "Apple");
        map.put("model", "Macbook Air");
        map.put("retailPrice", 88000L);
        
        Laptop laptop = mapper.convertValue(map, Laptop.class);
        System.out.println(laptop.toString());
    }
    
    public static void listToArrayExample() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        String[] array = mapper.convertValue(list, new TypeReference<>() {});
        for(String a : array) {
            System.out.println(a);
        }
    }
    
    private static class A {
        public String value;
    }
    
    private static class B {
        public String value;
    }
    
    public static void objectToObjectExample() {
        A a = new A();
        a.value = "Test";
        
        B b = mapper.convertValue(a, B.class);
        System.out.println(b.value);
    }
}