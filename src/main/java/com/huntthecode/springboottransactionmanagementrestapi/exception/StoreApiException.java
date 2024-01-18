package com.huntthecode.springboottransactionmanagementrestapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/*
Exception class to handle the exception if particular transaction does not belong
to the particular Store.
 */
@Getter
public class StoreApiException extends RuntimeException{

    public StoreApiException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public StoreApiException(String message, HttpStatus httpStatus, String message1) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message1;
    }

    private HttpStatus httpStatus;
    private  String message;
}
