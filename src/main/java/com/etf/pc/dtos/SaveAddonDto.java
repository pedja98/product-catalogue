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
public class SaveAddonDto {
    private ItemNameDto name;
    private String identifier;
    private String description;
    private ItemStatus status;
    private BigDecimal price;
    private LocalDate validFrom;
    private LocalDate validTo;
}
