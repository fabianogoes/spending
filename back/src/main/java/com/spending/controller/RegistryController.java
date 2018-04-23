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

@Api
@Slf4j
@RestController
@RequestMapping("/registries")
public class RegistryController {

    @Autowired
    private RegistryService service;

    @ApiOperation(
            value = SwaggerStrings.TYPE_VALUE,
            notes = SwaggerStrings.TYPE_NOTES
    )
    @PostMapping
    public ResponseEntity post(@RequestBody Registry registry) {
        log.info("save({})...", registry);
        this.service.save(registry);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(type.getId()).toUri();

        if(registry.getId() == null)
            return new ResponseEntity(registry, HttpStatus.CREATED);

//        return ResponseEntity.accepted().build();
        return new ResponseEntity(registry, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(this.service.findAll());
    }

    @GetMapping("/{registryId}")
    public ResponseEntity getOne(@PathVariable String registryId) {
        return ResponseEntity.ok(this.service.findOne(registryId));
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{registryId}")
    public void delete(@PathVariable String registryId){
        this.service.delete(registryId);
    }

}
