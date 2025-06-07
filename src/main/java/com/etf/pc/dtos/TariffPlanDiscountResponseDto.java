package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TariffPlanDiscountResponseDto {
    private TariffPlanRelationshipDto tariffPlan;
    private List<TariffPlanDiscountDto> discount;
}
