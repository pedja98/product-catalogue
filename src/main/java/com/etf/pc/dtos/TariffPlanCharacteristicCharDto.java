package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class TariffPlanCharacteristicCharDto {
    private UUID charId;
    private UUID relationId;
    private Map<String, String> name;
    private String identifier;
    private String createdByUser;
    private Instant dateCreated;
}
