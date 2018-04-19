package com.spending.controller;

import com.spending.model.Category;
import com.spending.service.CategoryService;
import com.spending.util.SwaggerStrings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api
@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @ApiOperation(
            value = SwaggerStrings.CATEGORY_VALUE,
            notes = SwaggerStrings.CATEGORY_NOTES
    )
    @PostMapping
    public ResponseEntity save(@RequestBody Category category) {
        log.info("save({})", category);
        this.service.save(category);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.getId()).toUri();

        if(category.getId() == null)
            return ResponseEntity.created(location).build();

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getOne(@PathVariable String categoryId) {
        log.info("getOne({})...", categoryId);
        return ResponseEntity.ok(this.service.findOne(categoryId));
    }

    @GetMapping
    public ResponseEntity getAll() {
        log.info("getAll()...");
        return ResponseEntity.ok(this.service.findAll());
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{categoryId}")
    public void delete(@PathVariable String categoryId){
        log.info("delete({})", categoryId);
        this.service.delete(categoryId);
    }

}
