package com.example.jsb.product.model;
import com.fasterxml.jackson.databind.JsonNode;

public record PatchProductCommand(Integer id, JsonNode patchNode) { }
