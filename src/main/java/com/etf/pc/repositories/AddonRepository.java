package com.etf.pc.repositories;

import com.etf.pc.entities.Addon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddonRepository extends JpaRepository<Addon, UUID> {
}
