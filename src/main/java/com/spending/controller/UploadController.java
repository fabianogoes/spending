package com.spending.controller;

import com.spending.dto.LoadResponseDTO;
import com.spending.service.LoadSheetBradescoImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    @Autowired
    private LoadSheetBradescoImplService service;

    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> upload(@RequestParam(value = "multipartFile") MultipartFile multipartFile) {
        LoadResponseDTO loadResponseDTO = this.service.load(multipartFile);
        return ResponseEntity.ok(loadResponseDTO);
    }

}
