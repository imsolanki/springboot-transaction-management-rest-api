package com.huntthecode.springboottransactionmanagementrestapi.controller;

import com.huntthecode.springboottransactionmanagementrestapi.dto.TransactionDto;
import com.huntthecode.springboottransactionmanagementrestapi.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores/{id}/transactions")
public class TransactionController {
    private TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    public ResponseEntity<TransactionDto> createTransaction(@PathVariable(name="id") long id,
                                                            @Valid @RequestBody TransactionDto transactionDto){
        return new ResponseEntity<>(transactionService.createTransaction(id,transactionDto), HttpStatus.CREATED);

    }

    @GetMapping
    public List<TransactionDto> getTransactionsByStoreId(@PathVariable(name="id")
                                                                       Long id){
        return transactionService.getTransactionsByStoreId(id);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable(name="id") Long storeId,
                                                             @PathVariable(name="transactionId") Long transactionId){
        return new ResponseEntity<>(transactionService.getTransactionById(storeId,transactionId),HttpStatus.OK);
    }

}
