package com.etf.pc.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String name;

    @Enumerated(EnumType.STRING)
    private CharacteristicType type;

    public enum CharacteristicType {
        STRING, NUMBER, BOOLEAN
    }
}
