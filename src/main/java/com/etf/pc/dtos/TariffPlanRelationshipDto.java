package com.etf.pc.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
@Builder
public class TariffPlanRelationshipDto {
    private UUID id;
    private Map<String, String> name;
    private String identifier;
}
