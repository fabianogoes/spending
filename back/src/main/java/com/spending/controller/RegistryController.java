package com.spending.controller;

import com.spending.model.Registry;
import com.spending.service.RegistryService;
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
@RequestMapping("/register")
public class RegistryController {

    @Autowired
    private RegistryService registerService;

    @ApiOperation(
            value = SwaggerStrings.TYPE_VALUE,
            notes = SwaggerStrings.TYPE_NOTES
    )
    @PostMapping
    public ResponseEntity post(@RequestBody Registry registry) {
        this.registerService.save(registry);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(registry.getId()).toUri();

        if(registry.getId() == null)
            return ResponseEntity.created(location).build();

        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(this.registerService.findAll());
    }

    @GetMapping("/{registryId}")
    public ResponseEntity getOne(@PathVariable String registryId) {
        return ResponseEntity.ok(this.registerService.findOne(registryId));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{registryId}")
    public void delete(@PathVariable String registryId){
        this.registerService.delete(registryId);
    }

}
