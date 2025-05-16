package com.etf.pc.services;

import com.etf.pc.dtos.SaveCharacteristicDto;
import com.etf.pc.entities.Characteristic;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.CharacteristicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static com.etf.pc.common.PcConstants.SuccessCodes.CHAR_CREATED;

@Service
@RequiredArgsConstructor
@Transactional
public class CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    public List<Characteristic> getAll() {
        return characteristicRepository.findAll();
    }

    public Optional<Characteristic> getByIdentifier(String identifier) {
        return characteristicRepository.findByIdentifier(identifier);
    }

    public String create(SaveCharacteristicDto characteristicDetails) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("sr", characteristicDetails.getName().getSr());
        nameMap.put("en", characteristicDetails.getName().getEn());

        Characteristic characteristic = Characteristic.builder()
                .name(nameMap)
                .identifier(characteristicDetails.getIdentifier())
                .value(characteristicDetails.getValue())
                .description(characteristicDetails.getDescription())
                .createdByUser(SetCurrentUserFilter.getCurrentUsername())
                .build();

        characteristicRepository.save(characteristic);
        return CHAR_CREATED;
    }
}
