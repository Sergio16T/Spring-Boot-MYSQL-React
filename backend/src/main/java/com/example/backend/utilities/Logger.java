package com.example.backend.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Logger {

    private static ObjectMapper mapper = new ObjectMapper();

    public static void printObjectAsString(Object o, String message) {
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            System.out.println(message + ": " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
