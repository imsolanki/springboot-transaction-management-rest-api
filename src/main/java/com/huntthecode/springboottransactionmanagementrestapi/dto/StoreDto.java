package com.huntthecode.springboottransactionmanagementrestapi.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class StoreDto {

    private Long id;

    //title should not be empty or null
    //title should have at least 5 characters
    @NotEmpty
    @Size(min = 5,message = "Store title must be of at least 5 characters")
    private String title;

    @NotEmpty
    @Size(min =10,message = "Description must be of at least 10 characters")
    private String description;

    private Set<TransactionDto> transactions;
}
