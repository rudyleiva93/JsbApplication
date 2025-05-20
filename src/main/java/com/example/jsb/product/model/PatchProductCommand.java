package com.example.jsb.product.model;

import com.example.jsb.product.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

import java.util.Optional;

@Getter
public class PatchProductCommand {
    private final Integer id;
    private final JsonNode patchNode;
    private ProductRepository productRepository;

    public PatchProductCommand(Integer id, JsonNode patchNode) {
        this.id = id;
        this.patchNode = patchNode;
    }
}
