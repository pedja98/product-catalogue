package com.etf.pc.services;

import com.etf.pc.dtos.SaveTariffPlanCharacteristicDto;
import com.etf.pc.dtos.TariffPlanCharacteristicCharDto;
import com.etf.pc.dtos.TariffPlanCharacteristicResponseDto;
import com.etf.pc.dtos.TariffPlanRelationshipDto;
import com.etf.pc.entities.Characteristic;
import com.etf.pc.entities.TariffPlan;
import com.etf.pc.entities.TariffPlanCharacteristic;
import com.etf.pc.exceptions.DuplicateItemException;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.CharacteristicRepository;
import com.etf.pc.repositories.TariffPlanCharacteristicRepository;
import com.etf.pc.repositories.TariffPlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
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

        TariffPlanRelationshipDto tariffPlanDto = TariffPlanRelationshipDto.builder()
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
        System.out.println(tariffPlan.getId());
        Characteristic characteristic = characteristicRepository.findById(tariffPlanCharacteristic.getCharId())
                .orElseThrow(() -> new IllegalArgumentException(CHAR_NOT_FOUND));

        boolean alreadyExists = tariffPlanCharacteristicRepository
                .existsByTariffPlanAndCharacteristic(tariffPlan, characteristic);

        if (alreadyExists) {
            throw new DuplicateItemException(CHAR_ALREADY_ADDED);
        }

        TariffPlanCharacteristic entity = new TariffPlanCharacteristic();
        entity.setTariffPlan(tariffPlan);
        entity.setCharacteristic(characteristic);
        entity.setCreatedByUser(SetCurrentUserFilter.getCurrentUsername());

        tariffPlanCharacteristicRepository.save(entity);
        return TARIFF_PLAN_CHARACTERISTIC_ADDED;
    }

    public String delete(UUID id) {
        TariffPlanCharacteristic tpc = this.tariffPlanCharacteristicRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(TARIFF_PLAN_CHARACTERISTIC_DELETED));

        Instant now = Instant.now();
        Duration duration = Duration.between(tpc.getDateCreated(), now);

        if (duration.toHours() > 12) {
            throw new RuntimeException(CHAR_ALREADY_ADDED);
        }

        tariffPlanCharacteristicRepository.deleteById(id);
        return TARIFF_PLAN_CHARACTERISTIC_DELETED;
    }
}

