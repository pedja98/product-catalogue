package com.etf.pc.services;

import com.etf.pc.entities.TariffPlanCharacteristic;
import com.etf.pc.repositories.CharacteristicRepository;
import com.etf.pc.repositories.TariffPlanCharacteristicRepository;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_CHARACTERISTIC_DELETED;
import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_CHARACTERISTIC_ADDED;
import static com.etf.pc.common.PcConstants.ErrorCodes.CHAR_NOT_FOUND;
import static com.etf.pc.common.PcConstants.ErrorCodes.TARIFF_PLAN_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffPlanCharacteristicService {

    private final TariffPlanCharacteristicRepository tariffPlanCharacteristicRepository;
    private final TariffPlanRepository tariffPlanRepository;
    private final CharacteristicRepository characteristicRepository;

    public List<TariffPlanCharacteristic> getByTariffPlan(UUID tariffPlanId) {
        return tariffPlanCharacteristicRepository.findByTariffPlanId(tariffPlanId);
    }

    public String add(TariffPlanCharacteristic entity) {
        if (entity.getTariffPlan() != null && entity.getTariffPlan().getId() != null) {
            entity.setTariffPlan(tariffPlanRepository.findById(entity.getTariffPlan().getId())
                    .orElseThrow(() -> new IllegalArgumentException(TARIFF_PLAN_NOT_FOUND)));
        }
        if (entity.getCharacteristic() != null && entity.getCharacteristic().getId() != null) {
            entity.setCharacteristic(characteristicRepository.findById(entity.getCharacteristic().getId())
                    .orElseThrow(() -> new IllegalArgumentException(CHAR_NOT_FOUND)));
        }

        tariffPlanCharacteristicRepository.save(entity);
        return TARIFF_PLAN_CHARACTERISTIC_ADDED;
    }

    public String delete(UUID id) {
        if (!tariffPlanCharacteristicRepository.existsById(id)) {
            throw new IllegalArgumentException(CHAR_NOT_FOUND);
        }
        tariffPlanCharacteristicRepository.deleteById(id);
        return TARIFF_PLAN_CHARACTERISTIC_DELETED;
    }
}

