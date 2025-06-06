package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class TariffPlanCharacteristicResponseDto {
    private UUID id;
    private TariffPlanCharacteristicTariffPlanDto tariffPlan;
    private List<TariffPlanCharacteristicCharDto> characteristics;
}
