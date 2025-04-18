package com.etf.pc.repositories;

import com.etf.pc.entities.TariffPlanCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TariffPlanCharacteristicRepository extends JpaRepository<TariffPlanCharacteristic, UUID> {
    List<TariffPlanCharacteristic> findByTariffPlanId(UUID tariffPlanId);
}
