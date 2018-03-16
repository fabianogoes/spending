package com.spending.service;

import com.spending.exception.SpendingException;
import com.spending.model.Type;
import com.spending.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    private TypeRepository repository;

    public Type save(Type type) {
        return this.save(type);
    }

    public List<Type> save(List<Type> types) {
        return this.repository.saveAll(types);
    }

    public Type findOne(String id) {
        return this.repository.findById(id).get();
    }

    public List<Type> findAll() {
        return this.repository.findAll();
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

    public void delete(String typeId) {
        Optional<Type> optional = this.repository.findById(typeId);
        if(optional.isPresent())
            this.repository.delete(optional.get());
        throw new SpendingException("Type Registry not found to delete!");
    }

    public Type findByName(String name) {
        return this.repository.findByName(name);
    }
}
