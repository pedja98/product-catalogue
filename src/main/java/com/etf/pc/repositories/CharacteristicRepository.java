package com.etf.pc.repositories;

import com.etf.pc.entities.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CharacteristicRepository extends JpaRepository<Characteristic, UUID> {
    Optional<Characteristic> findByIdentifier(String identifier);
}
