package com.spending.repository;

import com.spending.model.RegistryType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistryTypeRepository extends MongoRepository<RegistryType, String> {

    List<RegistryType> findDistinctByName();

}
