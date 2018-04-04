package com.spending.repository.impl;

import com.spending.dto.RegistryCategoryAggregateDTO;
import com.spending.dto.RegistryTypeAggregateDTO;
import com.spending.model.Registry;
import com.spending.repository.RegistryRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Slf4j
@Repository
public class RegistryRepositoryImpl implements RegistryRepositoryCustom {

    private static final String COUNT = "count";
    private static final String TOTAL = "total";
    private static final String CATEGORY = "category";
    private static final String TYPE = "type";
    private static final String ID = "_id";
    private static final String VALUE = "value";

    @Autowired
    private MongoTemplate mongo;

    public List<RegistryCategoryAggregateDTO> topByCategory(Integer limit) {

        /**
         {
         $group: {
         _id: {
         category: "$category",
         },
         count: {$sum: 1},
         total: {$sum: "$value"}
         }
         },
         {
         $sort: {total: -1}
         }
         */

        Aggregation aggregation = newAggregation(
                group(fields().and(CATEGORY, CATEGORY)).count().as(COUNT).sum(VALUE).as(TOTAL),
                project(ID, TOTAL, COUNT).and(CATEGORY).previousOperation(),
                sort(Sort.Direction.DESC, TOTAL),
                limit(limit)
        );

        AggregationResults<RegistryCategoryAggregateDTO> groupResults = mongo.aggregate(
                aggregation,
                Registry.class,
                RegistryCategoryAggregateDTO.class
        );

        return groupResults.getMappedResults();
    }

    @Override
    public List<RegistryTypeAggregateDTO> topByType(Integer limit) {

        /**
         {
         $group: {
         _id: {
         type: "$type",
         },
         count: {$sum: 1},
         total: {$sum: "$value"}
         }
         },
         {
         $sort: {total: -1}
         }
         */

        Aggregation aggregation = newAggregation(
                group(fields().and(TYPE, TYPE)).count().as(COUNT).sum(VALUE).as(TOTAL),
                project(ID, TOTAL, COUNT).and(TYPE).previousOperation(),
                sort(Sort.Direction.DESC, TOTAL),
                limit(limit)
        );

        AggregationResults<RegistryTypeAggregateDTO> groupResults = mongo.aggregate(
                aggregation,
                Registry.class,
                RegistryTypeAggregateDTO.class
        );

        return groupResults.getMappedResults();
    }
}
