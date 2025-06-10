package com.etf.pc.entities;

import com.etf.pc.converters.JsonToNameMapConvertor;
import com.etf.pc.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
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

    @Convert(converter = JsonToNameMapConvertor.class)
    @Column(columnDefinition = "VARCHAR", length = 255)
    private Map<String, String> name;

    @Column(nullable = false, length = 30, unique = true)
    private String identifier;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ItemStatus status = ItemStatus.ACTIVE;

    @Column(nullable = false, length = 20, name = "created_by_user")
    private String createdByUser;

    @Column(length = 20, name = "modified_by_user")
    private String modifiedByUser;

    @CreationTimestamp
    @Column(name = "date_created")
    private Instant dateCreated;

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Instant dateModified;
}