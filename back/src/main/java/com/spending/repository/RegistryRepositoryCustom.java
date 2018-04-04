package com.spending.repository;

import com.spending.dto.RegistryCategoryAggregateDTO;
import com.spending.dto.RegistryTypeAggregateDTO;

import java.util.List;

public interface RegistryRepositoryCustom {
    List<RegistryCategoryAggregateDTO> topByCategory(Integer limit);
    List<RegistryTypeAggregateDTO> topByType(Integer limit);
}
