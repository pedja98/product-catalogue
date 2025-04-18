package com.etf.pc.controllers;

import com.etf.pc.dtos.MessageResponse;
import com.etf.pc.entities.TariffPlanDiscounts;
import com.etf.pc.services.TariffPlanDiscountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tariff-plan-discounts")
@RequiredArgsConstructor
public class TariffPlanDiscountsController {

    private final TariffPlanDiscountsService service;

    @GetMapping
    public ResponseEntity<List<TariffPlanDiscounts>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TariffPlanDiscounts> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody TariffPlanDiscounts entity) {
        return ResponseEntity.ok(new MessageResponse(service.create(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> update(@PathVariable UUID id,
                                                  @RequestBody TariffPlanDiscounts entity) {
        return ResponseEntity.ok(new MessageResponse(service.update(id, entity)));
    }
}
