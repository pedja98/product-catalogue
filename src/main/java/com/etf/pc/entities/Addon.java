package com.etf.pc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "addons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Addon {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "is_optional")
    private boolean isOptional;
}