package com.spending.controller;

import com.spending.model.Type;
import com.spending.service.TypeService;
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
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private TypeService service;

    @ApiOperation(
            value = SwaggerStrings.TYPE_VALUE,
            notes = SwaggerStrings.TYPE_NOTES
    )
    @PostMapping
    public ResponseEntity save(@RequestBody Type type) {
        log.info("save({})...", type);
        this.service.save(type);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(type.getId()).toUri();

        if(type.getId() == null)
            return ResponseEntity.created(location).build();

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/{typeId}")
    public ResponseEntity getOne(@PathVariable String typeId) {
        log.info("getOne({})", typeId);
        return ResponseEntity.ok(this.service.findOne(typeId));
    }

    @GetMapping
    public ResponseEntity getAll() {
        log.info("getAll()...");
        return ResponseEntity.ok(this.service.findAll());
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{typeId}")
    public void delete(@PathVariable String typeId){
        log.info("delete({})", typeId);
        this.service.delete(typeId);
    }

}
