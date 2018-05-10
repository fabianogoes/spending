package com.spending.service;

import com.spending.exception.SpendingException;
import com.spending.model.Category;
import com.spending.model.Registry;
import com.spending.model.Type;
import com.spending.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistryService {

    @Autowired
    private RegistryRepository repository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CategoryService categoryService;

    public Registry save(Registry registry){
        this.setTypeByMatchPattern(registry);
        this.setCategoryByMatchPattern(registry);
        return this.repository.save(registry);
    }

    private Registry setCategoryByMatchPattern(Registry registry) {
        Category category = this.getCategory(registry.getDescription());
        registry.setCategory(category);
        return registry;
    }

    private Registry setTypeByMatchPattern(Registry registry) {
        Type type = this.getType(registry.getDescription());
        registry.setType(type);
        return registry;
    }

    public void save(List<Registry> registries){
        registries.forEach(registry -> {
            registry = this.setTypeByMatchPattern(registry);
            registry = this.setCategoryByMatchPattern(registry);
            this.repository.save(registry);
        });
    }

    public Registry findOne(String id){
        Optional<Registry> optional = this.repository.findById(id);
        if(!optional.isPresent()) {
            throw new SpendingException(String.format("Registry(%s) not found", id));
        }
        return optional.get();
    }

    public List<Registry> findAll() {
        return this.repository.findAll();
    }

    public void delete(String id) {
        this.repository.deleteById(id);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

    public boolean exist(Registry registry) {
        return this.repository.existsByDateAndDescriptionAndValue(registry.getDate(), registry.getDescription(), registry.getValue());
    }

    public double sum() {
        return this.findAll().stream().mapToDouble(r -> r.getValue()).sum();
    }

    public Type getType(String pattern) {
        Type typeResponse = null;
        List<Type> types = typeService.findAll();
        for (Type type : types) {
            if(type.getPatterns() == null) continue;
            for(String p : type.getPatterns()) {
                if(pattern.toLowerCase().contains(p.toLowerCase())){
                    typeResponse = type;
                    break;
                }
            }
            if(typeResponse != null) break;
        }
        if(typeResponse == null) typeResponse = typeService.findByName("Outros");
        return typeResponse;
    }
    public Category getCategory(String pattern) {
        Category categoryResponse = null;
        List<Category> categories = categoryService.findAll();
        for (Category categ : categories) {
            if(categ.getPatterns() == null) continue;
            for(String p : categ.getPatterns()) {
                if(pattern.toLowerCase().contains(p.toLowerCase())){
                    categoryResponse = categ;
                    break;
                }
            }
            if(categoryResponse != null) break;
        }
        if(categoryResponse == null) categoryResponse = categoryService.findByName("Outros");
        return categoryResponse;
    }

    public List<Registry> findByCategory(String categoryId) {
        Category category = this.categoryService.findOne(categoryId);
        return repository.findByCategory(category);
    }

    public List<Registry> findByType(String typeId) {
        Type type = this.typeService.findOne(typeId);
        return repository.findByType(type);
    }
}
