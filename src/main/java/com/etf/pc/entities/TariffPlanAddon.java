package com.etf.pc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tariff_plan_addons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TariffPlanAddon {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tariff_plan_id")
    private TariffPlan tariffPlan;

    @ManyToOne
    @JoinColumn(name = "addon_id")
    private Addon addon;
}
