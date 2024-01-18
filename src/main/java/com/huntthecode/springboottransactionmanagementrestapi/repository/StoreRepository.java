package com.huntthecode.springboottransactionmanagementrestapi.repository;

import com.huntthecode.springboottransactionmanagementrestapi.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
/*
JpaRepository class has implementation class called SimpleJpaRepository
which is already annotated with @Repository & @Transactional annotation
so no need to annotate the StoreRepository with it.
 */
public interface StoreRepository extends JpaRepository<Store,Long> {

}
