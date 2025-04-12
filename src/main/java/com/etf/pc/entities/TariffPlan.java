package com.etf.pc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tariff_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TariffPlan {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePrice;

    @Column(name = "base_discount_percent")
    private BigDecimal baseDiscountPercent;

    @OneToMany(mappedBy = "tariffPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffPlanCharacteristic> characteristics = new ArrayList<>();

    @OneToMany(mappedBy = "tariffPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffPlanAddon> addons = new ArrayList<>();

    @OneToMany(mappedBy = "tariffPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffPlanPrice> pricingHistory = new ArrayList<>();
}
