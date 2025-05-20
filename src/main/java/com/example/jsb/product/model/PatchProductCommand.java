package com.example.jsb.product.model;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class PatchProductCommand {
    private final Integer id;
    private final JsonNode patchNode;

    public PatchProductCommand(Integer id, JsonNode patchNode) {
        this.id = id;
        this.patchNode = patchNode;
    }
}
