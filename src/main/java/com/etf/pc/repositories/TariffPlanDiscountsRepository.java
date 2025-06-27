package com.etf.pc.repositories;

import com.etf.pc.entities.TariffPlanDiscounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TariffPlanDiscountsRepository extends JpaRepository<TariffPlanDiscounts, UUID> {
    List<TariffPlanDiscounts> findByTariffPlanId(UUID tariffPlanId);

    @Query(value = """
            SELECT d.id,
                   d.discount,
                   d.min_amount_of_tariff_plans,
                   d.max_amount_of_tariff_plans,
                   d.tariff_plan_id,
                   d.created_by_user,
                   d.modified_by_user,
                   d.date_created,
                   d.date_modified
            FROM tariff_plan_discounts d
            JOIN tariff_plans tp ON d.tariff_plan_id = tp.id
            WHERE tp.identifier = :identifier
              AND :amount BETWEEN d.min_amount_of_tariff_plans AND d.max_amount_of_tariff_plans
            ORDER BY d.date_created ASC
            LIMIT 1
            """, nativeQuery = true)
    TariffPlanDiscounts findFirstFitDiscountByIdentifierAndAmount(@Param("identifier") String identifier, @Param("amount") int amount);


}

