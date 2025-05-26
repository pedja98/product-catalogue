package com.etf.pc.services;

import com.etf.pc.dtos.SaveCharacteristicDto;
import com.etf.pc.entities.Characteristic;
import com.etf.pc.exceptions.DuplicateItemException;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.CharacteristicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.etf.pc.common.PcConstants.ErrorCodes.DUPLICATE_IDENTIFIER;
import static com.etf.pc.common.PcConstants.ErrorCodes.CHAR_NOT_FOUND;
import static com.etf.pc.common.PcConstants.SuccessCodes.CHAR_CREATED;
import static com.etf.pc.common.PcConstants.SuccessCodes.CHAR_UPDATED;

@Service
@RequiredArgsConstructor
@Transactional
public class CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    public List<Characteristic> getAll() {
        return characteristicRepository.findAll();
    }

    public Characteristic getByIdentifier(String identifier) {
        return characteristicRepository.findByIdentifier(identifier).orElseThrow(() -> new ItemNotFoundException(CHAR_NOT_FOUND));
    }

    public String create(SaveCharacteristicDto characteristicDetails) {
        if (characteristicRepository.findByIdentifier(characteristicDetails.getIdentifier()).isPresent()) {
            throw new DuplicateItemException(DUPLICATE_IDENTIFIER);
        }

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

    public String update(String identifier, SaveCharacteristicDto characteristicDto) {
        Characteristic characteristic = characteristicRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new ItemNotFoundException(CHAR_NOT_FOUND));

        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("sr", characteristicDto.getName().getSr());
        nameMap.put("en", characteristicDto.getName().getEn());

        characteristic.setName(nameMap);
        characteristic.setValue(characteristicDto.getValue());
        characteristic.setDescription(characteristicDto.getDescription());
        characteristic.setModifiedByUser(SetCurrentUserFilter.getCurrentUsername());

        characteristicRepository.save(characteristic);
        return CHAR_UPDATED;
    }
}
