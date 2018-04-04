package com.spending.service;

import com.spending.dto.RegistryCategoryAggregateDTO;
import com.spending.dto.RegistryTypeAggregateDTO;
import com.spending.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    @Autowired
    private RegistryRepository registryRepository;

    public List<RegistryCategoryAggregateDTO> topByCategory(Integer limit) {
        limit = limit == null ? 5 : limit;
        return this.registryRepository.topByCategory(limit);
    }

    public List<RegistryTypeAggregateDTO> topByType(Integer limit) {
        limit = limit == null ? 5 : limit;
        return this.registryRepository.topByType(limit);
    }


}
