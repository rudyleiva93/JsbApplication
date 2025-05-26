package com.example.jsb.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

}
