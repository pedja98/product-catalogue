package com.etf.pc.services;

import com.etf.pc.entities.Addon;
import com.etf.pc.repositories.AddonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.etf.pc.common.PcConstants.SuccessCodes.ADDON_CREATED;
import static com.etf.pc.common.PcConstants.SuccessCodes.ADDON_UPDATED;

@Service
@RequiredArgsConstructor
@Transactional
public class AddonService {

    private final AddonRepository addonRepository;

    public List<Addon> getAll() {
        return addonRepository.findAll();
    }

    public Optional<Addon> getById(UUID id) {
        return addonRepository.findById(id);
    }

    public String create(Addon addon) {
        addonRepository.save(addon);
        return ADDON_CREATED;
    }

    public String update(UUID id, Addon updatedAddon) {
        return addonRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedAddon.getName());
                    existing.setIdentifier(updatedAddon.getIdentifier());
                    existing.setPrice(updatedAddon.getPrice());
                    existing.setValidFrom(updatedAddon.getValidFrom());
                    existing.setValidTo(updatedAddon.getValidTo());
                    existing.setModifiedByUser(updatedAddon.getModifiedByUser());
                    addonRepository.save(existing);
                    return ADDON_UPDATED;
                })
                .orElseThrow(() -> new IllegalArgumentException("Addon not found"));
    }
}
