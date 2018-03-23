package com.spending.dto;

import com.spending.model.Category;
import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistryCategoryAggregateDTO {

    private Category category;
    private Double total;

}
