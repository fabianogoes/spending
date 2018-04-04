package com.spending.service;

import com.spending.model.Category;
import com.spending.model.Registry;
import com.spending.model.Type;
import com.spending.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistryService {

    @Autowired
    private RegistryRepository repository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CategoryService categoryService;

    public Registry save(Registry registry){
        Type type = this.getType(registry.getDescription());
        Category category = this.getCategory(registry.getDescription());

        registry.setType(type);
        registry.setCategory(category)
        ;
        return this.repository.save(registry);
    }

    public Registry findOne(String id){
        return this.repository.findById(id).get();
    }

    public List<Registry> findAll() {
        return this.repository.findAll();
    }

    public void delete(String id) {
        this.repository.deleteById(id);
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
            if(type.getPattern() == null) continue;
            for(String p : type.getPattern()) {
                if(pattern.toLowerCase().contains(p.toLowerCase())){
                    typeResponse = type;
                    break;
                }
            }
            if(typeResponse != null) break;;
        }
        if(typeResponse == null) typeResponse = typeService.findByName("Outros");
        return typeResponse;
    }
    public Category getCategory(String pattern) {
        Category categoryResponse = null;
        List<Category> categories = categoryService.findAll();
        for (Category categ : categories) {
            if(categ.getPattern() == null) continue;
            for(String p : categ.getPattern()) {
                if(pattern.toLowerCase().contains(p.toLowerCase())){
                    categoryResponse = categ;
                    break;
                }
            }
            if(categoryResponse != null) break;;
        }
        if(categoryResponse == null) categoryResponse = categoryService.findByName("Outros");
        return categoryResponse;
    }

}
