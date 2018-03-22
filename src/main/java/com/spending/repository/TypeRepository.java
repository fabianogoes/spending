package com.spending.repository;

import com.spending.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {

    Optional<Type> findByName(String name);

}
