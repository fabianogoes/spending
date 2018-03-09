package com.spending.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"name", "pattern"})
public class RegistryType {

    @Id
    private String id;
    private String name;
    private Set<String> pattern;

}
