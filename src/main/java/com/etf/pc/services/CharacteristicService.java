package com.etf.pc.services;

import com.etf.pc.entities.Characteristic;
import com.etf.pc.repositories.CharacteristicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CharacteristicService {

    private final CharacteristicRepository repository;

    public List<Characteristic> getAll() {
        return repository.findAll();
    }

    public Optional<Characteristic> getByIdentifier(String identifier) {
        return repository.findByIdentifier(identifier);
    }

    public Characteristic create(Characteristic characteristic) {
        return repository.save(characteristic);
    }
}
