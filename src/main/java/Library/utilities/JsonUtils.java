package Library.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/***
     *
     * JSON utility method class
     * @author Ignas Ivoska
     *
     */
    public class JsonUtils {
        /**
         * Utility class - constructor is private so no instance of this class is created anywhere - we only need to call the static methods
         */
        private JsonUtils() {
        }

        /**
         * Parses a java object into it's JSON representation
         * @param object the object we desire to convert
         * @return json representation
         * @throws JsonProcessingException if parsing fails
         */
        public static String toJson(Object object) throws JsonProcessingException {
            return objectMapper().writeValueAsString(object);
        }

        public static String toPrettyJson(Object object) throws JsonProcessingException {
            return objectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }

        public static String ListToPrettyJson(ArrayList<Object> objects) throws JsonProcessingException {
            String jsonString = null;

            for (Object object : objects) {
                jsonString += toPrettyJson(object);
            }
            return jsonString;
    }


    /**
         * Parses a JSON representation into a Java object instance
         * @param json json representation
         * @param clazz type which we want the JSON representation to be converted to
         * @param <T> generics
         * @return instance of the Class we pass, e.g. Person.class in second parameter will make this method return an instance of Person
         * @throws JsonProcessingException if parsing from JSON fails
         */
        public static <T> T[] fromJson(String json, Class<T[]> clazz) throws JsonProcessingException {
            return objectMapper().readValue(json, clazz);
        }

        private static ObjectMapper objectMapper() {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
            return objectMapper;
        }

}
