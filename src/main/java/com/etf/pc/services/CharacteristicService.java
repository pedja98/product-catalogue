package com.etf.pc.services;

import com.etf.pc.entities.Characteristic;
import com.etf.pc.repositories.CharacteristicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String create(Characteristic characteristic) {
        characteristicRepository.save(characteristic);
        return CHAR_CREATED;
    }
}
