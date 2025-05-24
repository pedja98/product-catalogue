package com.etf.pc.services;

import com.etf.pc.entities.TariffPlan;
import com.etf.pc.exceptions.DuplicateItemException;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.etf.pc.common.PcConstants.ErrorCodes.DUPLICATE_IDENTIFIER;
import static com.etf.pc.common.PcConstants.ErrorCodes.TARIFF_PLAN_NOT_FOUND;
import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_CREATED;
import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_UPDATED;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffPlanService {

    private final TariffPlanRepository tariffPlanRepository;

    public List<TariffPlan> getAll() {
        return tariffPlanRepository.findAll();
    }

    public TariffPlan getByIdentifier(String identifier) {
        return tariffPlanRepository.findByIdentifier(identifier).orElseThrow(() -> new ItemNotFoundException(TARIFF_PLAN_NOT_FOUND));
    }

    public String create(TariffPlan tariffPlanDetails) {
        if (tariffPlanRepository.findByIdentifier(tariffPlanDetails.getIdentifier()).isPresent()) {
            throw new DuplicateItemException(DUPLICATE_IDENTIFIER);
        }

        tariffPlanRepository.save(tariffPlanDetails);
        return TARIFF_PLAN_CREATED;
    }

    public String update(UUID id, TariffPlan updatedPlan) {
        return tariffPlanRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedPlan.getName());
                    existing.setIdentifier(updatedPlan.getIdentifier());
                    existing.setDescription(updatedPlan.getDescription());
                    existing.setPrice(updatedPlan.getPrice());
                    existing.setModifiedByUser(updatedPlan.getModifiedByUser());
                    tariffPlanRepository.save(existing);
                    return TARIFF_PLAN_UPDATED;
                })
                .orElseThrow(() -> new IllegalArgumentException("Tariff plan not found"));
    }
}


