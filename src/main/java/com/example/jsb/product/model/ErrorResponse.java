package com.example.jsb.product.model;

public record ErrorResponse(int statusCode, String message, long timeStamp) { }
