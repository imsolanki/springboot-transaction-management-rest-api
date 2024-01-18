package com.huntthecode.springboottransactionmanagementrestapi.enums;

import lombok.Getter;
/*
* Enum to set the currency type
 */

@Getter
public enum CurrencyType {
    INR("₹"),
    USD("$");

    private final String symbol;

    CurrencyType(String symbol) {
        this.symbol = symbol;
    }

}
