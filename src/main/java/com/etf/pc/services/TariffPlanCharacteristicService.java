package com.etf.pc.services;

import com.etf.pc.dtos.SaveTariffPlanCharacteristicDto;
import com.etf.pc.dtos.TariffPlanCharacteristicCharDto;
import com.etf.pc.dtos.TariffPlanCharacteristicResponseDto;
import com.etf.pc.dtos.TariffPlanCharacteristicTariffPlanDto;
import com.etf.pc.entities.Characteristic;
import com.etf.pc.entities.TariffPlan;
import com.etf.pc.entities.TariffPlanCharacteristic;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.CharacteristicRepository;
import com.etf.pc.repositories.TariffPlanCharacteristicRepository;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.etf.pc.common.PcConstants.ErrorCodes.*;
import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_CHARACTERISTIC_DELETED;
import static com.etf.pc.common.PcConstants.SuccessCodes.TARIFF_PLAN_CHARACTERISTIC_ADDED;

@Service
@RequiredArgsConstructor
@Transactional
public class TariffPlanCharacteristicService {

    private final TariffPlanCharacteristicRepository tariffPlanCharacteristicRepository;
    private final TariffPlanRepository tariffPlanRepository;
    private final CharacteristicRepository characteristicRepository;

    public TariffPlanCharacteristicResponseDto getByTariffPlan(String tariffPlanIdentifier) {
        TariffPlan tariffPlan = this.tariffPlanRepository
                .findByIdentifier(tariffPlanIdentifier)
                .orElseThrow(() -> new ItemNotFoundException(TARIFF_PLAN_NOT_FOUND));

        List<TariffPlanCharacteristic> list = tariffPlanCharacteristicRepository.findByTariffPlanId(tariffPlan.getId());

        TariffPlanCharacteristicTariffPlanDto tariffPlanDto = TariffPlanCharacteristicTariffPlanDto.builder()
                .id(tariffPlan.getId())
                .name(tariffPlan.getName())
                .identifier(tariffPlan.getIdentifier())
                .build();

        List<TariffPlanCharacteristicCharDto> charDtos = list.stream()
                .map(tpc -> {
                    var c = tpc.getCharacteristic();
                    return TariffPlanCharacteristicCharDto.builder()
                            .charId(c.getId())
                            .relationId(tpc.getId())
                            .name(c.getName())
                            .identifier(c.getIdentifier())
                            .createdByUser(tpc.getCreatedByUser())
                            .dateCreated(tpc.getDateCreated())
                            .build();
                })
                .toList();

        return TariffPlanCharacteristicResponseDto.builder()
                .tariffPlan(tariffPlanDto)
                .characteristics(charDtos)
                .build();
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

