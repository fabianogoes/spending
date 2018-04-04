package com.spending.controller;

import com.spending.service.RankingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    private RankingService rankingService;

    @GetMapping(value = "/top/category/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topByCategory(
            @ApiParam(value = "limit", defaultValue = "5", name = "limit")
            @PathVariable(value = "limit", required = false, name = "limit") Integer limit
    ) {
        return ResponseEntity.ok(this.rankingService.topByCategory(limit));
    }

    @GetMapping(value = "/top/type/{limit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity topByType(
            @ApiParam(value = "limit", defaultValue = "5", name = "limit")
            @PathVariable(value = "limit", required = false, name = "limit") Integer limit
    ) {
        return ResponseEntity.ok(this.rankingService.topByType(limit));
    }

}
