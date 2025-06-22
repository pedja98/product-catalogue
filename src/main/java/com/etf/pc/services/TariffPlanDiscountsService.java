package com.etf.pc.services;

import com.etf.pc.dtos.*;
import com.etf.pc.entities.TariffPlan;
import com.etf.pc.entities.TariffPlanDiscounts;
import com.etf.pc.exceptions.BadRequestException;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.TariffPlanDiscountsRepository;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.etf.pc.common.PcConstants.ErrorCodes.*;
import static com.etf.pc.common.PcConstants.SuccessCodes.*;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffPlanDiscountsService {

    private final TariffPlanDiscountsRepository discountsRepository;
    private final TariffPlanRepository tariffPlanRepository;
    private final TariffPlanDiscountsRepository tariffPlanDiscountsRepository;

    public TariffPlanDiscountResponseDto getDiscountsByTariffPlan(String tariffPlanIdentifier) {
        TariffPlan tariffPlan = this.tariffPlanRepository
                .findByIdentifier(tariffPlanIdentifier)
                .orElseThrow(() -> new ItemNotFoundException(TARIFF_PLAN_NOT_FOUND));

        List<TariffPlanDiscounts> discountList = tariffPlanDiscountsRepository.findByTariffPlanId(tariffPlan.getId());

        TariffPlanRelationshipDto tariffPlanDto = TariffPlanRelationshipDto.builder()
                .id(tariffPlan.getId())
                .name(tariffPlan.getName())
                .identifier(tariffPlan.getIdentifier())
                .build();

        List<TariffPlanDiscountDto> discountDtos = discountList.stream()
                .map(d -> TariffPlanDiscountDto.builder()
                        .id(d.getId())
                        .discount(d.getDiscount())
                        .minAmountOfTariffPlans(d.getMinAmountOfTariffPlans())
                        .maxAmountOfTariffPlans(d.getMaxAmountOfTariffPlans())
                        .createdByUser(d.getCreatedByUser())
                        .modifiedByUser(d.getModifiedByUser())
                        .dateCreated(d.getDateCreated())
                        .dateModified(d.getDateModified())
                        .build())
                .toList();

        return TariffPlanDiscountResponseDto.builder()
                .tariffPlan(tariffPlanDto)
                .discounts(discountDtos)
                .build();
    }

    public String create(SaveTariffPlanDiscountDto tariffPlanDiscountDetails) {
        TariffPlan tariffPlan = this.tariffPlanRepository
                .findByIdentifier(tariffPlanDiscountDetails.getTariffPlanIdentifier())
                .orElseThrow(() -> new ItemNotFoundException(TARIFF_PLAN_NOT_FOUND));

        if (tariffPlanDiscountDetails.getMinAmountOfTariffPlans() >= tariffPlanDiscountDetails.getMaxAmountOfTariffPlans()) {
            throw new BadRequestException(MAX_TP_LOWER_THEN_MIN);
        }

        if (tariffPlanDiscountDetails.getDiscount().compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new BadRequestException(DISCOUNT_BAD_VALUE);
        }

        TariffPlanDiscounts tariffPlanDiscounts = TariffPlanDiscounts.builder()
                .tariffPlan(tariffPlan)
                .discount(tariffPlanDiscountDetails.getDiscount())
                .minAmountOfTariffPlans(tariffPlanDiscountDetails.getMinAmountOfTariffPlans())
                .maxAmountOfTariffPlans(tariffPlanDiscountDetails.getMaxAmountOfTariffPlans())
                .createdByUser(SetCurrentUserFilter.getCurrentUsername())
                .build();
        this.discountsRepository.save(tariffPlanDiscounts);
        return TARIFF_PLAN_DISCOUNT_CREATED;
    }

    public String update(UUID id, SaveTariffPlanDiscountDto updated) {
        TariffPlanDiscounts existing = discountsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(DISCOUNT_NOT_FOUND));

        existing.setDiscount(updated.getDiscount());
        existing.setMinAmountOfTariffPlans(updated.getMinAmountOfTariffPlans());
        existing.setMaxAmountOfTariffPlans(updated.getMaxAmountOfTariffPlans());
        existing.setModifiedByUser(SetCurrentUserFilter.getCurrentUsername());

        discountsRepository.save(existing);
        return TARIFF_PLAN_DISCOUNT_UPDATED;
    }

    public String delete(UUID id) {
        if (!this.discountsRepository.existsById(id)) {
            throw new IllegalArgumentException(DISCOUNT_NOT_FOUND);
        }
        this.discountsRepository.deleteById(id);
        return DISCOUNT_DELETED;
    }

    public Map<String, List<DiscountInfoDto>> getAllDiscountsGroupedByTariffPlanIdentifier() {
        List<TariffPlanDiscounts> allDiscounts = discountsRepository.findAll();

        return allDiscounts.stream()
                .filter(discount -> discount.getTariffPlan() != null)
                .collect(Collectors.groupingBy(
                        discount -> discount.getTariffPlan().getIdentifier(),
                        Collectors.mapping(this::mapToDto, Collectors.toList())
                ));
    }

    private DiscountInfoDto mapToDto(TariffPlanDiscounts entity) {
        return DiscountInfoDto.builder()
                .id(entity.getId())
                .discount(entity.getDiscount())
                .minAmountOfTariffPlans(entity.getMinAmountOfTariffPlans())
                .maxAmountOfTariffPlans(entity.getMaxAmountOfTariffPlans())
                .createdByUser(entity.getCreatedByUser())
                .modifiedByUser(entity.getModifiedByUser())
                .dateCreated(entity.getDateCreated())
                .dateModified(entity.getDateModified())
                .build();
    }
}
