package com.etf.pc.controllers;

import com.etf.pc.entities.Characteristic;
import com.etf.pc.services.CharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characteristics")
@RequiredArgsConstructor
public class CharacteristicController {

    private final CharacteristicService service;

    @GetMapping
    public ResponseEntity<List<Characteristic>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Characteristic> getByIdentifier(@PathVariable String identifier) {
        return service.getByIdentifier(identifier)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Characteristic> create(@RequestBody Characteristic characteristic) {
        return ResponseEntity.ok(service.create(characteristic));
    }
}
