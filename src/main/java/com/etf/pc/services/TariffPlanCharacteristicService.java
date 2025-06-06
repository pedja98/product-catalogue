package com.etf.pc.services;

import com.etf.pc.dtos.SaveTariffPlanCharacteristicDto;
import com.etf.pc.dtos.TariffPlanCharacteristicCharDto;
import com.etf.pc.dtos.TariffPlanCharacteristicResponseDto;
import com.etf.pc.dtos.TariffPlanCharacteristicTariffPlanDto;
import com.etf.pc.entities.Characteristic;
import com.etf.pc.entities.TariffPlan;
import com.etf.pc.entities.TariffPlanCharacteristic;
import com.etf.pc.filters.SetCurrentUserFilter;
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

    public List<TariffPlanCharacteristicResponseDto> getByTariffPlan(UUID tariffPlanId) {
        List<TariffPlanCharacteristic> list = tariffPlanCharacteristicRepository.findByTariffPlanId(tariffPlanId);

        return list.stream()
                .map(tpc -> TariffPlanCharacteristicResponseDto.builder()
                        .id(tpc.getId())
                        .tariffPlan(TariffPlanCharacteristicTariffPlanDto.builder()
                                .id(tpc.getTariffPlan().getId())
                                .name(tpc.getTariffPlan().getName())
                                .identifier(tpc.getTariffPlan().getIdentifier())
                                .description(tpc.getTariffPlan().getDescription())
                                .price(tpc.getTariffPlan().getPrice())
                                .createdByUser(tpc.getTariffPlan().getCreatedByUser())
                                .modifiedByUser(tpc.getTariffPlan().getModifiedByUser())
                                .dateCreated(tpc.getTariffPlan().getDateCreated())
                                .dateModified(tpc.getTariffPlan().getDateModified())
                                .build())
                        .characteristics(
                                list.stream()
                                        .map(inner -> {
                                            var c = inner.getCharacteristic();
                                            return TariffPlanCharacteristicCharDto.builder()
                                                    .id(c.getId())
                                                    .name(c.getName())
                                                    .identifier(c.getIdentifier())
                                                    .createdByUser(c.getCreatedByUser())
                                                    .modifiedByUser(c.getModifiedByUser())
                                                    .dateCreated(c.getDateCreated())
                                                    .dateModified(c.getDateModified())
                                                    .build();
                                        })
                                        .toList()
                        )
                        .build())
                .toList();
    }


    public String add(SaveTariffPlanCharacteristicDto tariffPlanCharacteristic) {
        TariffPlan tariffPlan = tariffPlanRepository.findById(tariffPlanCharacteristic.getTariffPlanId())
                .orElseThrow(() -> new IllegalArgumentException(TARIFF_PLAN_NOT_FOUND));

        Characteristic characteristic = characteristicRepository.findById(tariffPlanCharacteristic.getCharId())
                .orElseThrow(() -> new IllegalArgumentException(CHAR_NOT_FOUND));

        TariffPlanCharacteristic entity = new TariffPlanCharacteristic();
        entity.setTariffPlan(tariffPlan);
        entity.setCharacteristic(characteristic);
        entity.setCreatedByUser(SetCurrentUserFilter.getCurrentUsername());

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

