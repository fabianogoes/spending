package com.spending.repository;

import com.spending.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends MongoRepository<Type, String> {

//    List<Type> findDistinctByName(String name);
    Type findByName(String name);

}
