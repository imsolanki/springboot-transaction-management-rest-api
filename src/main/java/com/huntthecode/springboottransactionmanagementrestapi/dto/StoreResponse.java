package com.huntthecode.springboottransactionmanagementrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/*
* This is a Javadoc comment for the StoreResponse class.
* It is used to support the Pagination & Sorting in the application.

 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StoreResponse {
    private List<StoreDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean last;
}
