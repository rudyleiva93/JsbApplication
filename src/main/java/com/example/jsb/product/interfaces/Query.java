package com.example.jsb.product.interfaces;

import org.springframework.http.ResponseEntity;

//Adds abstraction with service class
// Enforces a contract
//Ensures the name "execute" is consistent across app
// This will only be for the get request

public interface Query <I, O>{
    ResponseEntity<O> execute(I input);
}
