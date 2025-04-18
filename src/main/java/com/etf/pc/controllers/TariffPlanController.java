package com.etf.pc.controllers;

import com.etf.pc.dtos.MessageResponse;
import com.etf.pc.entities.TariffPlan;
import com.etf.pc.services.TariffPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tariff-plans")
@RequiredArgsConstructor
public class TariffPlanController {

    private final TariffPlanService tariffPlanService;

    @GetMapping
    public ResponseEntity<List<TariffPlan>> getAll() {
        return ResponseEntity.ok(tariffPlanService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TariffPlan> getById(@PathVariable UUID id) {
        return tariffPlanService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody TariffPlan tariffPlan) {
        return ResponseEntity.ok(new MessageResponse(tariffPlanService.create(tariffPlan)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> update(@PathVariable UUID id, @RequestBody TariffPlan tariffPlan) {
        return ResponseEntity.ok(new MessageResponse(tariffPlanService.update(id, tariffPlan)));
    }
}
