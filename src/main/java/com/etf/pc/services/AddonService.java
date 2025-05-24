package com.etf.pc.services;

import com.etf.pc.dtos.SaveAddonDto;
import com.etf.pc.entities.Addon;
import com.etf.pc.exceptions.DuplicateItemException;
import com.etf.pc.exceptions.ItemNotFoundException;
import com.etf.pc.filters.SetCurrentUserFilter;
import com.etf.pc.repositories.AddonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.etf.pc.common.PcConstants.ErrorCodes.CHAR_NOT_FOUND;
import static com.etf.pc.common.PcConstants.SuccessCodes.ADDON_CREATED;
import static com.etf.pc.common.PcConstants.SuccessCodes.ADDON_UPDATED;
import static com.etf.pc.common.PcConstants.ErrorCodes.DUPLICATE_IDENTIFIER;

@Service
@RequiredArgsConstructor
@Transactional
public class AddonService {

    private final AddonRepository addonRepository;

    public List<Addon> getAll() {
        return addonRepository.findAll();
    }

    public Addon getByIdentifier(String identifier) {
        return addonRepository.findByIdentifier(identifier).orElseThrow(() -> new ItemNotFoundException(CHAR_NOT_FOUND));
    }

    public String create(SaveAddonDto addonDetails) {
        if (addonRepository.findByIdentifier(addonDetails.getIdentifier()).isPresent()) {
            throw new DuplicateItemException(DUPLICATE_IDENTIFIER);
        }
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
        this.addonRepository.save(addon);
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
