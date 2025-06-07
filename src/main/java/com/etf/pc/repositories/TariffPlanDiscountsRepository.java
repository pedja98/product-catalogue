package com.etf.pc.repositories;

import com.etf.pc.entities.TariffPlanDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TariffPlanDiscountsRepository extends JpaRepository<TariffPlanDiscounts, UUID> {
    List<TariffPlanDiscounts> findByTariffPlanId(UUID tariffPlanId);
}

