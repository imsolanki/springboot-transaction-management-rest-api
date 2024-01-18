package com.huntthecode.springboottransactionmanagementrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/*
Class used as the POJO class for formatting the exception message.
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorDetails {

    private Date timestamp;
    private String message;
    private String details;
}
