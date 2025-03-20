package com.example.tripadvisor.controller;

import com.example.tripadvisor.dto.DestinationDTO;
import com.example.tripadvisor.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DestinationController {
    private final DestinationService destinationService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<List<DestinationDTO>> getAllDestinations() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("User accessing destinations: " + auth.getName());
        System.out.println("User authorities: " + auth.getAuthorities());
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DestinationDTO> createDestination(@RequestBody DestinationDTO destinationDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Admin creating destination: " + auth.getName());
        System.out.println("Admin authorities: " + auth.getAuthorities());
        return ResponseEntity.ok(destinationService.createDestination(destinationDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DestinationDTO> updateDestination(
            @PathVariable Long id,
            @RequestBody DestinationDTO destinationDTO) {
        System.out.println("Updating destination with id: " + id);
        return ResponseEntity.ok(destinationService.updateDestination(id, destinationDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDestination(@PathVariable Long id) {
        try {
            System.out.println("Request to delete destination with id: " + id);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            System.out.println("User attempting delete: " + auth.getName());
            System.out.println("User authorities: " + auth.getAuthorities());
            
            destinationService.deleteDestination(id);
            System.out.println("Successfully deleted destination with id: " + id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error in delete endpoint: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to delete destination: " + e.getMessage());
        }
    }
}
