package com.etf.pc.dtos;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTariffPlanCharacteristicDto {
    private UUID tariffPlanId;
    private UUID charId;
}
