package com.spending.service;

import com.spending.model.Type;
import com.spending.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    private static final String TYPE_NOT_FOUND = "Type not found!";
    private static final String TYPE_NOT_SHOULD_NULL = "Type not should null to save";
    private static final String TYPE_NAME_IS_REQUIRED = "Type name is required to save";
    private static final String TYPE_ID_NOT_SHOULD_NULL = "Type id not should null";

    @Autowired
    private TypeRepository repository;

    public Type save(Type type) {
        Assert.notNull(type, TYPE_NOT_SHOULD_NULL);
        Assert.notNull(type.getName(), TYPE_NAME_IS_REQUIRED);
        return this.repository.save(type);
    }

    public List<Type> save(List<Type> types) {
        Assert.notNull(types, TYPE_NOT_SHOULD_NULL);
        Assert.notEmpty(types, TYPE_NOT_SHOULD_NULL);
        return this.repository.saveAll(types);
    }

    public Type findOne(String id) {
        Assert.notNull(id, TYPE_ID_NOT_SHOULD_NULL);
        Optional<Type> optional = this.repository.findById(id);
        Assert.isTrue(optional.isPresent(), TYPE_NOT_FOUND);
        return optional.get();
    }

    public List<Type> findAll() {
        return this.repository.findAll();
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

    public void delete(String id) {
        Assert.notNull(id, TYPE_ID_NOT_SHOULD_NULL);
        Optional<Type> optional = this.repository.findById(id);
        Assert.isTrue(optional.isPresent(), TYPE_NOT_FOUND);
        this.repository.delete(optional.get());
    }

    public Type findByName(String name) {
        Optional<Type> optional = this.repository.findByName(name);
        Assert.isTrue(optional.isPresent(), TYPE_NOT_FOUND);
        return optional.get();
    }

    public void deletePattern(String typeId, String pattern) {
        Assert.notNull(typeId, TYPE_ID_NOT_SHOULD_NULL);
        Optional<Type> optional = this.repository.findById(typeId);
        Assert.isTrue(optional.isPresent(), TYPE_NOT_FOUND);
        Assert.isTrue(optional.get().getPatterns().contains(pattern), "Type/Pattern not found");
        optional.get().getPatterns().remove(pattern);
        this.repository.save(optional.get());
    }
}
