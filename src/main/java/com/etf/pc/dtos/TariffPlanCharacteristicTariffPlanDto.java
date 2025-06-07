package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class TariffPlanCharacteristicTariffPlanDto {
    private UUID id;
    private Map<String, String> name;
    private String identifier;
}
