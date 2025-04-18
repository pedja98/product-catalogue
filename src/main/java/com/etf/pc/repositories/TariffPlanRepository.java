package com.etf.pc.repositories;

import com.etf.pc.entities.TariffPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TariffPlanRepository extends JpaRepository<TariffPlan, UUID> {
}
