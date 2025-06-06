package com.etf.pc.controllers;

import com.etf.pc.dtos.MessageResponse;
import com.etf.pc.dtos.SaveTariffPlanCharacteristicDto;
import com.etf.pc.dtos.TariffPlanCharacteristicResponseDto;
import com.etf.pc.entities.TariffPlanCharacteristic;
import com.etf.pc.services.TariffPlanCharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tariff-plan-characteristics")
@RequiredArgsConstructor
public class TariffPlanCharacteristicController {

    private final TariffPlanCharacteristicService service;

    @GetMapping("/tariff-plan/{tariffPlanId}/characteristics")
    public ResponseEntity<List<TariffPlanCharacteristicResponseDto>> getByTariffPlan(@PathVariable UUID tariffPlanId) {
        return ResponseEntity.ok(service.getByTariffPlan(tariffPlanId));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> add(@RequestBody SaveTariffPlanCharacteristicDto tariffPlanCharacteristic) {
        return ResponseEntity.ok(new MessageResponse(service.add(tariffPlanCharacteristic)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(new MessageResponse(service.delete(id)));
    }
}
