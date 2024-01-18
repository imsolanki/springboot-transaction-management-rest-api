package com.huntthecode.springboottransactionmanagementrestapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huntthecode.springboottransactionmanagementrestapi.deserializer.LocalDateDeserializer;
import com.huntthecode.springboottransactionmanagementrestapi.enums.CurrencyType;
import com.huntthecode.springboottransactionmanagementrestapi.enums.PaymentMode;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private Long id;

    @NonNull
    private BigDecimal amount;
    @NonNull
    private CurrencyType currency;

    @NotEmpty
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate date;

    @NotEmpty
    private PaymentMode paymentMode;
}
