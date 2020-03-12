package com.fredrikpedersen.obligatoryassignment.web.controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 21:55
 */

public class BaseControllerTest {
    /**
     * @param object POJO to be mapped to a JSON object
     * @return JSON mapping of the  param object as a String
     * @throws JsonProcessingException if the conversion fails
     */
    protected String mapToJson(final Object object) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(object);
    }

    /**
     * @param json  JSON object to be converted to a POJO
     * @param clazz Provides the class which is going to be used as a template for mapping the JSON object
     * @param <T>   Generic enabling us to return whatever Java class we want
     * @return A POJO of the specified class
     * @throws JsonParseException   if the conversion fails
     * @throws JsonMappingException if the conversion fails
     * @throws IOException          if the conversion fails
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        return new ObjectMapper().readValue(json, clazz);
    }
}
