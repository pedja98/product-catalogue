package com.etf.pc.services;

import com.etf.pc.entities.TariffPlan;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<TariffPlan> getById(UUID id) {
        return tariffPlanRepository.findById(id);
    }

    public String create(TariffPlan tariffPlan) {
        tariffPlanRepository.save(tariffPlan);
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


