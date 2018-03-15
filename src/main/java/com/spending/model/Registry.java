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
public class Registry {

    @Id
    private String id;
    private String date;
    private String description;
    private String type;
    private Double value;
    private String category;

}
