package com.etf.pc.services;

import com.etf.pc.dtos.SaveTariffPlanDto;
import com.etf.pc.entities.TariffPlan;
import com.etf.pc.exceptions.DuplicateItemException;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.etf.pc.common.PcConstants.ErrorCodes.*;
import static com.etf.pc.common.PcConstants.SuccessCodes.*;

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

    public String create(SaveTariffPlanDto tpDetails) {
        if (this.tariffPlanRepository.findByIdentifier(tpDetails.getIdentifier()).isPresent()) {
            throw new DuplicateItemException(DUPLICATE_IDENTIFIER);
        }

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("sr", tpDetails.getName().getSr());
        nameMap.put("en", tpDetails.getName().getEn());
        TariffPlan tariffPlan = TariffPlan.builder()
                .name(nameMap)
                .description(tpDetails.getDescription())
                .price(tpDetails.getPrice())
                .identifier(tpDetails.getIdentifier())
                .createdByUser(SetCurrentUserFilter.getCurrentUsername())
                .build();

        this.tariffPlanRepository.save(tariffPlan);
        return TARIFF_PLAN_CREATED;
    }

    public String update(String identifier, SaveTariffPlanDto addonDetails) {
        TariffPlan tariffPlan = this.tariffPlanRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new ItemNotFoundException(ADDON_NOT_FOUND));

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("sr", addonDetails.getName().getSr());
        nameMap.put("en", addonDetails.getName().getEn());

        tariffPlan.setName(nameMap);
        tariffPlan.setPrice(addonDetails.getPrice());
        tariffPlan.setDescription(addonDetails.getDescription());
        tariffPlan.setModifiedByUser(SetCurrentUserFilter.getCurrentUsername());

        this.tariffPlanRepository.save(tariffPlan);
        return TARIFF_PLAN_UPDATED;
    }
}


