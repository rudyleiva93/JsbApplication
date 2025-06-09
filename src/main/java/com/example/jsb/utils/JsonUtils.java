package com.example.jsb.utils;

import com.example.jsb.exceptions.JsonPatchNotValidException;
import com.example.jsb.product.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class JsonUtils {

    public static <T> JsonNode convertToJson(T object){
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new Jdk8Module());
        return objectMapper.valueToTree(object);

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
        if (original instanceof ObjectNode originalObject && updates != null) {
            updates.fields().forEachRemaining(fieldName ->
                    originalObject.set(fieldName.getKey(), fieldName.getValue()));
        }

        return original;
    }
}
