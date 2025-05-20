package com.example.jsb.product.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {
    @JsonProperty
    private final Integer id;
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String description;


    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
    }
}
