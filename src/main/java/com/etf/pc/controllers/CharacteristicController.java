package com.etf.pc.controllers;

import com.etf.pc.dtos.MessageResponse;
import com.etf.pc.dtos.SaveCharacteristicDto;
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

    private final CharacteristicService characteristicService;

    @GetMapping
    public ResponseEntity<List<Characteristic>> getAll() {
        return ResponseEntity.ok(characteristicService.getAll());
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Characteristic> getByIdentifier(@PathVariable String identifier) {
        return  ResponseEntity.ok(characteristicService.getByIdentifier(identifier));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> create(@RequestBody SaveCharacteristicDto characteristic) {
        return ResponseEntity.ok(new MessageResponse(characteristicService.create(characteristic)));
    }

    @PutMapping("/{identifier}")
    public ResponseEntity<MessageResponse> update(
            @PathVariable String identifier,
            @RequestBody SaveCharacteristicDto updatedDto
    ) {
        return ResponseEntity.ok(new MessageResponse(characteristicService.update(identifier, updatedDto)));
    }
}
