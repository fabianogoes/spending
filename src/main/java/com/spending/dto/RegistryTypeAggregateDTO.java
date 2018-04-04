package com.spending.dto;

import com.spending.model.Type;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistryTypeAggregateDTO {

    private Type type;
    private BigDecimal total;
    private Integer count;

    public BigDecimal getTotal(){
        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
