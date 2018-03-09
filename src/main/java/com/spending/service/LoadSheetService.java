package com.spending.service;

import com.spending.dto.LoadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface LoadSheetService {

    LoadResponseDTO load(MultipartFile multipartFile);

}
