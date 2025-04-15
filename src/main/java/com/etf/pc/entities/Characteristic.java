package com.etf.pc.entities;

import com.etf.pc.converters.JsonToNameMapConvertor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "characteristics")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Characteristic {

    @Id
    @GeneratedValue
    private UUID id;

    @Convert(converter = JsonToNameMapConvertor.class)
    @Column(columnDefinition = "VARCHAR", length = 255)
    private Map<String, String> name;

    @Column(nullable = false, length = 30, unique = true)
    private String identifier;

    private String description;

    @Column(nullable = false)
    private String value;

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
