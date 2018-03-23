package com.spending.repository;

import com.spending.dto.RegistryCategoryAggregateDTO;

import java.util.List;

public interface RegistryRepositoryCustom {
    List<RegistryCategoryAggregateDTO> higherExpenses(Integer limit);
}
