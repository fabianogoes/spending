package com.spending.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"date", "description", "value"})
@Data
@Document
public class Registry extends BaseMongoModel {

    @Id
    private String id;
    private String date;
    private String description;
    private Type type;
    private Double value;
    private Category category;

}
