package com.spending.controller;

import com.spending.dto.LoadResponseDTO;
import com.spending.service.LoadSheetBradescoImplService;
import com.spending.util.SwaggerStrings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api
@Slf4j
@RestController
public class UploadController {

    @Autowired
    private LoadSheetBradescoImplService service;

    @ApiOperation(
            value = SwaggerStrings.UPLOAD_VALUE,
            notes = SwaggerStrings.UPLOAD_NOTES
    )
    @RequestMapping(
            value = "/upload",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<LoadResponseDTO> upload(
            @ApiParam(value = SwaggerStrings.UPLOAD_FILE, required = true, example = SwaggerStrings.UPLOAD_FILE_EXAMPLE)
            @RequestPart(value = "multipartFile") MultipartFile multipartFile
    ) {
        LoadResponseDTO loadResponseDTO = this.service.load(multipartFile);
        return ResponseEntity.ok(loadResponseDTO);
    }

}
