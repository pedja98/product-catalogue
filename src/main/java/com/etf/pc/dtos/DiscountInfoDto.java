package com.etf.pc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscountInfoDto {
    private UUID id;
    private BigDecimal discount;
    private Integer minAmountOfTariffPlans;
    private Integer maxAmountOfTariffPlans;
    private String createdByUser;
    private String modifiedByUser;
    private Instant dateCreated;
    private Instant dateModified;
}
