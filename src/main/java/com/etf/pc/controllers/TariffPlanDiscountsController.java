package com.etf.pc.controllers;

import com.etf.pc.dtos.MessageResponse;
import com.etf.pc.dtos.SaveTariffPlanDiscountDto;
import com.etf.pc.dtos.TariffPlanDiscountResponseDto;
import com.etf.pc.services.TariffPlanDiscountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tariff-plan-discounts")
@RequiredArgsConstructor
public class TariffPlanDiscountsController {

    private final TariffPlanDiscountsService service;

    @GetMapping("/tariff-plan/{tariffPlanIdentifier}/discounts")
    public ResponseEntity<TariffPlanDiscountResponseDto> getById(@PathVariable String tariffPlanIdentifier) {
        return ResponseEntity.ok(service.getDiscountsByTariffPlan(tariffPlanIdentifier));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody SaveTariffPlanDiscountDto entity) {
        return ResponseEntity.ok(new MessageResponse(service.create(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> update(@PathVariable UUID id,
                                                  @RequestBody SaveTariffPlanDiscountDto entity) {
        return ResponseEntity.ok(new MessageResponse(service.update(id, entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(new MessageResponse(service.delete(id)));
    }
}
