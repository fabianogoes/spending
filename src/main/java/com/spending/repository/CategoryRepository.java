package com.spending.repository;

import com.spending.model.Category;
import com.spending.model.RegistryType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findDistinctByName(String name);

    List<Category> findByName(String name);
}
