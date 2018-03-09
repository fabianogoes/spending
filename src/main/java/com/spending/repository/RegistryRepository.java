package com.spending.repository;


import com.spending.model.Registry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends MongoRepository<Registry, String> {

    boolean existsByDateAndDescriptionAndValue(String date, String description, Double value);

}
