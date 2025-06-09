package com.example.jsb.utils;

import com.example.jsb.exceptions.JsonPatchNotValidException;
import com.example.jsb.product.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class JsonUtils {

    public static JsonNode convertToJson(Product product ){
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module());
        return objectMapper.valueToTree(product);

    }

    public static <T> T convertFromJson(JsonNode node, Class<T> _class) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module());
        try {
            return objectMapper.treeToValue(node, _class);
        } catch (JsonProcessingException e) {
            throw new JsonPatchNotValidException();
        }
    }

    public static JsonNode merge(JsonNode original, JsonNode updates){
        updates.fieldNames().forEachRemaining(fieldName -> {
            JsonNode updatedValue = updates.get(fieldName);
            ((com.fasterxml.jackson.databind.node.ObjectNode) original).set(fieldName, updatedValue);
        });

        return original;
    }
}
