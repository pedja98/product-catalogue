package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class TariffPlanDiscountDto {
    private UUID id;
    private BigDecimal discount;
    private Integer minAmountOfTariffPlans;
    private Integer maxAmountOfTariffPlans;
    private String createdByUser;
    private String modifiedByUser;
    private Instant dateCreated;
    private Instant dateModified;
}
