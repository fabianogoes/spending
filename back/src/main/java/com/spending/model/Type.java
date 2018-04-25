package com.spending.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class Type extends BaseMongoModel {

    @Id
    private String id;
    @NotNull(message = "Name is required")
    private String name;
    private Set<String> patterns;

}
