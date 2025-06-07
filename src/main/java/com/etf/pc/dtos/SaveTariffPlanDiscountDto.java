package com.etf.pc.dtos;

import com.etf.pc.entities.TariffPlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTariffPlanDiscountDto {
    private BigDecimal discount;
    private Integer minAmountOfTariffPlans;
    private Integer maxAmountOfTariffPlans;
    private String tariffPlanIdentifier;
}
