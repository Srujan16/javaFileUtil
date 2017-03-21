package com.wavemaker.tutorial.fileUtil.utils;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wavemaker.tutorial.fileUtil.model.Employee;

/**
 * Created by srujant on 22/2/17.
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void readJsonFromInputStream(InputStream inputStream){
        try {
            JsonNode jsonNode = objectMapper.readTree(inputStream);
            Employee employee = objectMapper.readValue(jsonNode.toString(),Employee.class);
            System.out.println(objectMapper.writeValueAsString(employee));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
