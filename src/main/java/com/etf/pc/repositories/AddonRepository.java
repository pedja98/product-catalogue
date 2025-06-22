package com.etf.pc.repositories;

import com.etf.pc.entities.Addon;
import com.etf.pc.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddonRepository extends JpaRepository<Addon, UUID> {
    Optional<Addon> findByIdentifier(String identifier);
    List<Addon> findByStatusAndValidToLessThanEqual(ItemStatus status, LocalDate date);
    List<Addon> findAllByStatus(ItemStatus status);
}
