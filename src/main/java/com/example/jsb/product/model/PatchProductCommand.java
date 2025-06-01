package com.example.jsb.product.model;


import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

@Getter
public class PatchProductCommand {
    private final Integer id;
    private final Product product;
    private final JsonNode patchNode;

    public PatchProductCommand(Integer id, Product product, JsonNode patchNode) {
        this.id = id;
        this.product = product;
        this.patchNode = patchNode;
    }
}
