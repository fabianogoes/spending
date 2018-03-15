package com.spending.service;

import com.spending.model.Category;
import com.spending.model.Registry;
import com.spending.model.RegistryType;
import com.spending.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistryService {

    @Autowired
    private RegistryRepository repository;

    @Autowired
    private RegistryTypeService registryTypeService;

    @Autowired
    private CategoryService categoryService;

    public Registry save(Registry registry){
        String type = this.getType(registry.getDescription());
        String category = this.getCategory(registry.getDescription());

        registry.setType(type);
        registry.setCategory(category);
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

    public String getType(String pattern) {
        String type = null;
        List<RegistryType> registryTypes = registryTypeService.findAll();
        for (RegistryType rt : registryTypes) {
            if(rt.getPattern() == null) continue;
            for(String p : rt.getPattern()) {
                if(pattern.toLowerCase().contains(p.toLowerCase())){
                    type = rt.getName();
                    break;
                }
            }
            if(type != null) break;;
        }
        if(type == null) type = "Outros";
        return type.trim();
    }
    public String getCategory(String pattern) {
        String categoryResponse = null;
        List<Category> categories = categoryService.findAll();
        for (Category categ : categories) {
            if(categ.getPattern() == null) continue;
            for(String p : categ.getPattern()) {
                if(pattern.toLowerCase().contains(p.toLowerCase())){
                    categoryResponse = categ.getName();
                    break;
                }
            }
            if(categoryResponse != null) break;;
        }
        if(categoryResponse == null) categoryResponse = "Outros";
        return categoryResponse.trim();
    }

}
