package com.huntthecode.springboottransactionmanagementrestapi.service.impl;

import com.huntthecode.springboottransactionmanagementrestapi.dto.TransactionDto;
import com.huntthecode.springboottransactionmanagementrestapi.entity.Store;
import com.huntthecode.springboottransactionmanagementrestapi.entity.Transaction;
import com.huntthecode.springboottransactionmanagementrestapi.enums.CurrencyType;
import com.huntthecode.springboottransactionmanagementrestapi.exception.ResourceNotFoundException;
import com.huntthecode.springboottransactionmanagementrestapi.exception.StoreApiException;
import com.huntthecode.springboottransactionmanagementrestapi.repository.StoreRepository;
import com.huntthecode.springboottransactionmanagementrestapi.repository.TransactionRepository;
import com.huntthecode.springboottransactionmanagementrestapi.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private ModelMapper modelMapper;
    private StoreRepository storeRepository;

    private final String apiUrl = "https://api.fxratesapi.com/latest";
    private static final String CURRENCY_CODE = "\"INR\":";

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,ModelMapper modelMapper,
                                  StoreRepository storeRepository) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
        this.storeRepository=storeRepository;

    }
    @Override
    public TransactionDto createTransaction(long storeId, TransactionDto transactionDto){
        double rate = rateExtractor(apiUrl);
        //System.out.println(rate);
        if("USD".equalsIgnoreCase(String.valueOf(transactionDto.getCurrency()))){
            //converting Rates from USD to INR
            BigDecimal amountInUSD = transactionDto.getAmount();
            BigDecimal amountInINR = amountInUSD.multiply(BigDecimal.valueOf(rate));
            transactionDto.setAmount(amountInINR);
            transactionDto.setCurrency(CurrencyType.valueOf("INR"));

        }
        Transaction transaction = mapToEntity(transactionDto);
        //retrieve store by id
        Store store = storeRepository.findById(storeId).orElseThrow(()->
                new ResourceNotFoundException("Store","id",storeId));

        //Set store to transaction entity
        transaction.setStores(store);

        //Transaction entity to DB
        Transaction transaction1 = transactionRepository.save(transaction);

        return mapToDto(transaction1);
    }

    @Override
    public List<TransactionDto> getTransactionsByStoreId(Long id) {

        //retrieve transactions by store id.

        List<Transaction> transactions =transactionRepository.findByStoresId(id);
        List<TransactionDto> storeDtos= transactions.stream().map(transaction -> mapToDto(transaction))
                .collect(Collectors.toList());
        return storeDtos;
    }

    @Override
    public TransactionDto getTransactionById(Long storeId, Long transactionId) {
        //Retrieve Store by ID
        Store store = storeRepository.findById(storeId).orElseThrow(()->
                new ResourceNotFoundException("Store","id",storeId));
        //find transaction by ID
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(()->
                new ResourceNotFoundException("Store","id",transactionId));
        if(!transaction.getStores().getId().equals(store.getId())){
            throw new StoreApiException(HttpStatus.BAD_REQUEST,"Store Does Not belongs to");
        }
        return mapToDto(transaction);
    }

    /*
Methods used to convert DTO object to Entity and Vice-versa using Model Mapper Object
 */
    private TransactionDto mapToDto(Transaction transaction){
        TransactionDto transactionDto = modelMapper.map(transaction,TransactionDto.class);
        return transactionDto;
    }

    private Transaction mapToEntity(TransactionDto transactionDto){
        Transaction transaction = modelMapper.map(transactionDto,Transaction.class);
        return transaction;
    }

    private double rateExtractor(String url) {
        double todaysRate = 0;
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(url, String.class);
        int index = data.indexOf(CURRENCY_CODE);

        if (index != -1) {
            // Extract the value after "INR":
            int startIndex = index + CURRENCY_CODE.length();
            int endIndex = data.indexOf(",", startIndex);

            if (endIndex == -1) {
                endIndex = data.indexOf("}", startIndex);
            }

            String rateValue = data.substring(startIndex, endIndex).trim();
            todaysRate = Double.parseDouble(rateValue);
        }

        return todaysRate;
    }

}
