package com.etf.pc.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveCharacteristicDto {
    private ItemNameDto name;
    private String identifier;
    private String value;
    private String description;
}

