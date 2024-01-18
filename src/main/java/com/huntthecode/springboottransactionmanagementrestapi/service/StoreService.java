package com.huntthecode.springboottransactionmanagementrestapi.service;

import com.huntthecode.springboottransactionmanagementrestapi.dto.StoreDto;
import com.huntthecode.springboottransactionmanagementrestapi.dto.StoreResponse;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto);

    StoreResponse getAllStores(int pageNo, int PageSize,String sortBy,String sortDir);

    StoreDto getStoreById(long id);


}
