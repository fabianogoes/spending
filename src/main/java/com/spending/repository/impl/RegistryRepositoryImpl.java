package com.spending.repository.impl;

import com.spending.dto.RegistryCategoryAggregateDTO;
import com.spending.model.Registry;
import com.spending.repository.RegistryRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Slf4j
@Repository
public class RegistryRepositoryImpl implements RegistryRepositoryCustom {

    @Autowired
//    @Qualifier("defaultMongoTemplate")
    private MongoTemplate mongo;

    @Override
    public List<RegistryCategoryAggregateDTO> higherExpenses(Integer limit) {

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

//        GroupOperation group = group(
//                    fields()
//                        .and("category", "category.name")
//                )
//                .count().as("count")
//                .sum("value").as("total");
//
//        SortOperation sort = sort(Sort.Direction.DESC, "total");

        Aggregation aggregation = newAggregation(
                group(fields().and("category", "category")).count().as("count").sum("value").as("total"),
                project("_id", "total", "count").and("category").previousOperation(),
                sort(Sort.Direction.DESC, "total"),
                limit(limit)
        );

        AggregationResults<RegistryCategoryAggregateDTO> groupResults = mongo.aggregate(
                aggregation,
                Registry.class,
                RegistryCategoryAggregateDTO.class
        );

        return groupResults.getMappedResults();
    }
}
