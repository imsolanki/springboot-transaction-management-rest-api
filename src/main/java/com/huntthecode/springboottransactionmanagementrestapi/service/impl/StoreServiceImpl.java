package com.huntthecode.springboottransactionmanagementrestapi.service.impl;

import com.huntthecode.springboottransactionmanagementrestapi.dto.StoreDto;
import com.huntthecode.springboottransactionmanagementrestapi.dto.StoreResponse;
import com.huntthecode.springboottransactionmanagementrestapi.entity.Store;
import com.huntthecode.springboottransactionmanagementrestapi.exception.ResourceNotFoundException;
import com.huntthecode.springboottransactionmanagementrestapi.repository.StoreRepository;
import com.huntthecode.springboottransactionmanagementrestapi.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    private StoreRepository storeRepository;

    private  ModelMapper modelMapper;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository,ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public StoreDto createStore(StoreDto storeDto) {
        //Convert DTO to Entity using Model Mapper
        Store store = mapToEntity(storeDto);
        Store newStore = storeRepository.save(store);
        //convert Entity to DTO using Model Mapper & returning it
        return mapToDto(newStore);
    }

    /*
    Implementation of getAllStores endpoint along with achieving sorting and pagination
     */
    @Override
    public StoreResponse getAllStores(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        //create Pageable Instance
        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);

        Page<Store> stores = storeRepository.findAll(pageable);

        //get content for  page object
        List<Store> listOfStores =  stores.getContent();
        List<StoreDto> storeDtos= stores.stream().map(store -> mapToDto(store)).collect(Collectors.toList());

        StoreResponse storeResponse = new StoreResponse();
        storeResponse.setContent(storeDtos);
        storeResponse.setPageNo(stores.getNumber());
        storeResponse.setPageSize(stores.getSize());
        storeResponse.setTotalElement(stores.getTotalElements());
        storeResponse.setTotalPages(stores.getTotalPages());
        storeResponse.setLast(stores.isLast());
        return storeResponse;
    }

    @Override
    public StoreDto getStoreById(long id) {
        Store store = storeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Store","id",id));
        return mapToDto(store);
    }


    /*
    Methods used to convert DTO object to Entity and Vice-versa using Model Mapper Object
     */
    private StoreDto mapToDto(Store store){
        StoreDto storeDto = modelMapper.map(store,StoreDto.class);
        return storeDto;
    }

    private Store mapToEntity(StoreDto storeDto){
        Store store = modelMapper.map(storeDto,Store.class);
        return store;
    }
}
