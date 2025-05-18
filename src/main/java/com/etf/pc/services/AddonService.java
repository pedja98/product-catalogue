package com.etf.pc.services;

import com.etf.pc.dtos.SaveAddonDto;
import com.etf.pc.entities.Addon;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.AddonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public String create(SaveAddonDto addonDetails) {
        Map<String, String> nameMap = new HashMap<>();
        nameMap.put("sr", addonDetails.getName().getSr());
        nameMap.put("en", addonDetails.getName().getEn());
        Addon addon = Addon.builder()
                .name(nameMap)
                .description(addonDetails.getDescription())
                .price(addonDetails.getPrice())
                .identifier(addonDetails.getIdentifier())
                .validTo(addonDetails.getValidTo())
                .validFrom(addonDetails.getValidFrom())
                .createdByUser(SetCurrentUserFilter.getCurrentUsername())
                .build();
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
