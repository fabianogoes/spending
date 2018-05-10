package com.spending.repository;


import com.spending.model.Category;
import com.spending.model.Registry;
import com.spending.model.Type;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistryRepository extends MongoRepository<Registry, String>, RegistryRepositoryCustom {

    boolean existsByDateAndDescriptionAndValue(String date, String description, Double value);

    List<Registry> findByCategory(Category category);

    List<Registry> findByType(Type type);
}
