package com.spending.service;

import com.spending.model.Category;
import com.spending.repository.CategoryRepository;
import com.spending.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final String CATEGORY_NOT_FOUND = "Category not found!";
    private static final String CATEGORY_NOT_SHOULD_NULL = "Category not should null to save";
    private static final String CATEGORY_NAME_IS_REQUIRED = "Category name is required to save";
    private static final String CATEGORY_ID_NOT_SHOULD_NULL = "Category id not should null";

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private RegistryRepository registryRepository;

    public Category save(Category category) {
        Assert.notNull(category, CATEGORY_NOT_SHOULD_NULL);
        Assert.notNull(category.getName(), CATEGORY_NAME_IS_REQUIRED);
        return this.save(category);
    }

    public List<Category> save(List<Category> categories) {
        Assert.notNull(categories, CATEGORY_NOT_SHOULD_NULL);
        Assert.notEmpty(categories, CATEGORY_NOT_SHOULD_NULL);
        return this.repository.saveAll(categories);
    }

    public Category findOne(String id) {
        Assert.notNull(id, CATEGORY_ID_NOT_SHOULD_NULL);
        Optional<Category> optional = this.repository.findById(id);
        Assert.isTrue(optional.isPresent(), CATEGORY_NOT_FOUND);
        return optional.get();
    }

    public List<Category> findAll() {
        this.registryRepository.higherExpenses(5).forEach(System.out::println);
        return this.repository.findAll();
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

    public Category findByName(String name){
        return this.repository.findByName(name);
    }

    public void delete(String categoryId) {
        Assert.notNull(categoryId, CATEGORY_ID_NOT_SHOULD_NULL);
        Optional<Category> optional = this.repository.findById(categoryId);
        Assert.isTrue(optional.isPresent(), CATEGORY_NOT_FOUND);
        this.repository.delete(optional.get());
    }
}
