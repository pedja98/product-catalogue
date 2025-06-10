package com.etf.pc.dtos;

import com.etf.pc.enums.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveTariffPlanDto {
    private ItemNameDto name;
    private ItemStatus status;
    private String identifier;
    private String description;
    private BigDecimal price;
}
