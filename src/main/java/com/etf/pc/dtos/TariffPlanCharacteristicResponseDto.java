package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TariffPlanCharacteristicResponseDto {
    private TariffPlanCharacteristicTariffPlanDto tariffPlan;
    private List<TariffPlanCharacteristicCharDto> characteristics;
}
