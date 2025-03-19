package com.example.tripadvisor.controller;

import com.example.tripadvisor.dto.DestinationDTO;
import com.example.tripadvisor.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping
    public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
        return ResponseEntity.ok(destinationService.getAllDestinations());
    }

    @GetMapping("/top")
    public ResponseEntity<List<DestinationDTO>> getTopDestinations() {
        return ResponseEntity.ok(destinationService.getTopDestinations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinationDTO> getDestinationById(@PathVariable Long id) {
        return ResponseEntity.ok(destinationService.getDestinationById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<DestinationDTO>> searchDestinations(@RequestParam String query) {
        return ResponseEntity.ok(destinationService.searchDestinations(query));
    }

    @PostMapping
    public ResponseEntity<DestinationDTO> createDestination(@RequestBody DestinationDTO destinationDTO) {
        return ResponseEntity.ok(destinationService.createDestination(destinationDTO));
    }
}
