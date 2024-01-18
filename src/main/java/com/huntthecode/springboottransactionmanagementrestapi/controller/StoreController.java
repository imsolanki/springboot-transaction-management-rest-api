package com.huntthecode.springboottransactionmanagementrestapi.controller;

import com.huntthecode.springboottransactionmanagementrestapi.dto.StoreDto;
import com.huntthecode.springboottransactionmanagementrestapi.dto.StoreResponse;
import com.huntthecode.springboottransactionmanagementrestapi.service.StoreService;
import com.huntthecode.springboottransactionmanagementrestapi.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    //Create Store Post endpoint
    @PostMapping
    public ResponseEntity<StoreDto> createStore(@Valid @RequestBody StoreDto storeDto){
        return new ResponseEntity<>(storeService.createStore(storeDto), HttpStatus.CREATED);
    }

    //Get All Stores endpoint
    @GetMapping
    public ResponseEntity<StoreResponse> getAllStore(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false)int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){
        return new ResponseEntity<>(storeService.getAllStores(pageNo,pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable(name ="id")long id){
        return new ResponseEntity<>(storeService.getStoreById(id), HttpStatus.OK);
    }
}
