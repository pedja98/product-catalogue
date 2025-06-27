package com.etf.pc.controllers;

import com.etf.pc.dtos.*;
import com.etf.pc.services.TariffPlanDiscountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tariff-plan-discounts")
@RequiredArgsConstructor
public class TariffPlanDiscountsController {

    private final TariffPlanDiscountsService discountService;

    @GetMapping("/tariff-plan/{tariffPlanIdentifier}/discounts")
    public ResponseEntity<TariffPlanDiscountResponseDto> getById(@PathVariable String tariffPlanIdentifier) {
        return ResponseEntity.ok(discountService.getDiscountsByTariffPlan(tariffPlanIdentifier));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody SaveTariffPlanDiscountDto entity) {
        return ResponseEntity.ok(new MessageResponse(discountService.create(entity)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> update(@PathVariable UUID id,
                                                  @RequestBody SaveTariffPlanDiscountDto entity) {
        return ResponseEntity.ok(new MessageResponse(discountService.update(id, entity)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(new MessageResponse(discountService.delete(id)));
    }

    @GetMapping
    public ResponseEntity<Map<String, List<DiscountInfoDto>>> getAllGroupedDiscounts() {
        Map<String, List<DiscountInfoDto>> result = discountService.getAllDiscountsGroupedByTariffPlanIdentifier();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{tariffPlanIdentifier}/first-fit-discount")
    public ResponseEntity<TariffPlanDiscountDto> getFirstFitDiscount(
            @PathVariable String tariffPlanIdentifier,
            @RequestParam("amount") int amount
    ) {
        TariffPlanDiscountDto result = discountService.getFirstFitDiscount(tariffPlanIdentifier, amount);
        return ResponseEntity.ok(result);
    }

}
