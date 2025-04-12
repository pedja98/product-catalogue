package com.etf.pc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
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

    @Column(nullable = false, length = 30, unique = true)
    private String identifier;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "base_discount_percent")
    private BigDecimal baseDiscountPercent;

    @OneToMany(mappedBy = "tariffPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffPlanCharacteristic> characteristics = new ArrayList<>();

    @OneToMany(mappedBy = "tariffPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TariffPlanAddon> addons = new ArrayList<>();

    @Column(nullable = false, length = 20)
    private String createdByUser;

    @Column(length = 20)
    private String modifiedByUser;

    @CreationTimestamp
    @Column(name = "date_created")
    private Instant dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Instant dateModified;
}
