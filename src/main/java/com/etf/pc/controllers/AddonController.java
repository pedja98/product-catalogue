package com.etf.pc.controllers;

import com.etf.pc.dtos.MessageResponse;
import com.etf.pc.dtos.SaveAddonDto;
import com.etf.pc.entities.Addon;
import com.etf.pc.services.AddonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/addons")
@RequiredArgsConstructor
public class AddonController {

    private final AddonService addonService;

    @GetMapping
    public ResponseEntity<List<Addon>> getAll() {
        return ResponseEntity.ok(addonService.getAll());
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Addon> getByIdentifier(@PathVariable String identifier) {
        return ResponseEntity.ok(addonService.getByIdentifier(identifier));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody SaveAddonDto addon) {
        return ResponseEntity.ok(new MessageResponse(addonService.create(addon)));
    }

    @PutMapping("/{identifier}")
    public ResponseEntity<MessageResponse> update(@PathVariable String identifier, @RequestBody SaveAddonDto addon) {
        return ResponseEntity.ok(new MessageResponse(addonService.update(identifier, addon)));
    }
}

