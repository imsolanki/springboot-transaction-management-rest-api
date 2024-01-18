package com.huntthecode.springboottransactionmanagementrestapi.repository;

import com.huntthecode.springboottransactionmanagementrestapi.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreRepositoryTest {
    @Autowired
    private StoreRepository storeRepository;

    @Test
    void saveMethod(){
        //create store
        Store store = new Store();
        store.setTitle("TestingStore");
        store.setDescription("Test Store repository");
        //save Store
        Store storeObject = storeRepository.save(store);
        //display store Info
        System.out.println(storeObject.getId());
        System.out.println(storeObject.toString());

    }


}