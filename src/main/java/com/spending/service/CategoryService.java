package com.spending.service;

import com.spending.exception.SpendingException;
import com.spending.model.Category;
import com.spending.model.RegistryType;
import com.spending.repository.CategoryRepository;
import com.spending.repository.RegistryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category save(Category category) {
        return this.save(category);
    }

    public List<Category> save(List<Category> categorys) {
        return this.repository.saveAll(categorys);
    }

    public Category findOne(String id) {
        return this.repository.findById(id).get();
    }

    public List<Category> findAll() {
        return this.repository.findAll();
    }

    public List<Category> findAllDistinct(String name) {
        return this.repository.findDistinctByName(name);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

    public List<Category> findByName(String name){
        return this.repository.findByName(name);
    }

    public void delete(String categoryId) throws SpendingException {
        Optional<Category> optional = this.repository.findById(categoryId);
        if(optional.isPresent())
            this.repository.delete(optional.get());

        throw new SpendingException("Category not found to delete!");
    }
}
