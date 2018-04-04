package com.spending.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoadResponseDTO {

    private int totalPersisted;
}
