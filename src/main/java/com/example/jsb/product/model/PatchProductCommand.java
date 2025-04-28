package com.example.jsb.product.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

@Getter
public class PatchProductCommand {
    private Integer id;
    private JsonNode patchNode;

    public PatchProductCommand(Integer id, JsonNode patchNode) {
        this.id = id;
        this.patchNode = patchNode;
    }
}
