package com.etf.pc.services;

import com.etf.pc.entities.TariffPlan;
import com.etf.pc.entities.TariffPlanDiscounts;
import com.etf.pc.repositories.TariffPlanDiscountsRepository;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_DISCOUNT_UPDATED;
import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_DISCOUNT_CREATED;
import static com.etf.pc.common.PcConstants.ErrorCodes.TARIFF_PLAN_NOT_FOUND;
import static com.etf.pc.common.PcConstants.ErrorCodes.DISCOUNT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffPlanDiscountsService {

    private final TariffPlanDiscountsRepository discountsRepository;
    private final TariffPlanRepository tariffPlanRepository;

    public List<TariffPlanDiscounts> getAll() {
        return discountsRepository.findAll();
    }

    public TariffPlanDiscounts getById(UUID id) {
        return discountsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Discount not found"));
    }

    public String create(TariffPlanDiscounts entity) {
        if (entity.getTariffPlan() != null && entity.getTariffPlan().getId() != null) {
            TariffPlan plan = tariffPlanRepository.findById(entity.getTariffPlan().getId())
                    .orElseThrow(() -> new IllegalArgumentException(TARIFF_PLAN_NOT_FOUND));
            entity.setTariffPlan(plan);
        }
        discountsRepository.save(entity);
        return TARIFF_PLAN_DISCOUNT_CREATED;
    }

    public String update(UUID id, TariffPlanDiscounts updated) {
        TariffPlanDiscounts existing = discountsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(DISCOUNT_NOT_FOUND));

        existing.setDiscount(updated.getDiscount());
        existing.setMinAmountOfTariffPlans(updated.getMinAmountOfTariffPlans());
        existing.setMaxAmountOfTariffPlans(updated.getMaxAmountOfTariffPlans());
        existing.setModifiedByUser(updated.getModifiedByUser());

        discountsRepository.save(existing);
        return TARIFF_PLAN_DISCOUNT_UPDATED;
    }
}
