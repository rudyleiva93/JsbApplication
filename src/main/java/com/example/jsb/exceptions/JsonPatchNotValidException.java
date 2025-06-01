package com.example.jsb.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JsonPatchNotValidException extends RuntimeException {
    public JsonPatchNotValidException() {
        super(ErrorMessages.JSON_PATCH_NODE_NOT_VALID.getMessage());
    }

}
