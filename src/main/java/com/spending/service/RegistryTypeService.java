package com.spending.service;

import com.spending.model.RegistryType;
import com.spending.repository.RegistryTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistryTypeService {

    @Autowired
    private RegistryTypeRepository repository;

    public RegistryType save(RegistryType registryType) {
        return this.save(registryType);
    }

    public List<RegistryType> save(List<RegistryType> registryTypes) {
        return this.repository.saveAll(registryTypes);
    }

    public RegistryType findOne(String id) {
        return this.repository.findById(id).get();
    }

    public List<RegistryType> findAll() {
        return this.repository.findAll();
    }

    public List<RegistryType> findAllDistinct() {
        return this.repository.findDistinctByName();
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

}
