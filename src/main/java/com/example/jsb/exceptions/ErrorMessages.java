package com.example.jsb.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description must be 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be negative"),
    JSON_PATCH_NODE_NOT_VALID("Json request body not invalid");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
