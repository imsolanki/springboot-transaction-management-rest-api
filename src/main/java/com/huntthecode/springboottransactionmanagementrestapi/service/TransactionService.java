package com.huntthecode.springboottransactionmanagementrestapi.service;

import com.huntthecode.springboottransactionmanagementrestapi.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    TransactionDto createTransaction(long storeId,TransactionDto transactionDto);

    List<TransactionDto> getTransactionsByStoreId(Long id);

    TransactionDto getTransactionById(Long storeId,Long transactionId);
}
