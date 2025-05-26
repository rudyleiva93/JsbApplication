package com.example.jsb.product.model;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int statusCode;
    private final String message;
    private final long timeStamp;

    public ErrorResponse(int statusCode, String message, long timeStamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
