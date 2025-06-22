package com.etf.pc.repositories;

import com.etf.pc.entities.TariffPlan;
import com.etf.pc.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TariffPlanRepository extends JpaRepository<TariffPlan, UUID> {
    Optional<TariffPlan> findByIdentifier(String identifier);
    List<TariffPlan> findAllByStatus(ItemStatus status);
}
