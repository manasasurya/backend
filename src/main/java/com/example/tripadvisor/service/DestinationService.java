package com.example.tripadvisor.service;

import com.example.tripadvisor.dto.DestinationDTO;
import com.example.tripadvisor.entity.Destination;
import com.example.tripadvisor.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinationService {
    private final DestinationRepository destinationRepository;

    public List<DestinationDTO> getAllDestinations() {
        return destinationRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<DestinationDTO> getTopDestinations() {
        return destinationRepository.findTop10ByOrderByRatingDesc().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DestinationDTO getDestinationById(Long id) {
        return destinationRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Destination not found"));
    }

    public List<DestinationDTO> searchDestinations(String query) {
        List<Destination> destinations = destinationRepository.findByNameContainingIgnoreCase(query);
        destinations.addAll(destinationRepository.findByLocationContainingIgnoreCase(query));
        return destinations.stream()
                .distinct()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteDestination(Long id) {
        try {
            if (!destinationRepository.existsById(id)) {
                throw new RuntimeException("Destination not found with id: " + id);
            }
            System.out.println("Deleting destination with id: " + id);
            destinationRepository.deleteById(id);
            System.out.println("Successfully deleted destination with id: " + id);
        } catch (Exception e) {
            System.err.println("Error deleting destination: " + e.getMessage());
            throw new RuntimeException("Failed to delete destination: " + e.getMessage());
        }
    }

    @Transactional
    public DestinationDTO updateDestination(Long id, DestinationDTO destinationDTO) {
        // Validate input
        if (destinationDTO.getName() == null || destinationDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Destination name cannot be empty");
        }
        if (destinationDTO.getLocation() == null || destinationDTO.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        // Check if destination exists
        Destination destination = destinationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destination not found with id: " + id));

        // Check if the new name is already taken by another destination
        if (!destination.getName().equals(destinationDTO.getName())) {
            List<Destination> existingDestinations = destinationRepository.findByNameIgnoreCase(destinationDTO.getName());
            if (!existingDestinations.isEmpty()) {
                boolean nameExists = existingDestinations.stream()
                    .anyMatch(existingDest -> !existingDest.getId().equals(id));
                if (nameExists) {
                    throw new IllegalArgumentException("A destination with this name already exists");
                }
            }
        }

        try {
            // Update fields
            destination.setName(destinationDTO.getName().trim());
            destination.setLocation(destinationDTO.getLocation().trim());
            destination.setDescription(destinationDTO.getDescription() != null ? 
                destinationDTO.getDescription().trim() : null);
            
            // Update image URL (can be null or empty)
            destination.setImageUrl(destinationDTO.getImageUrl() != null ? 
                destinationDTO.getImageUrl().trim().isEmpty() ? null : destinationDTO.getImageUrl().trim() : null);
            
            if (destinationDTO.getRating() != null) {
                if (destinationDTO.getRating() < 0 || destinationDTO.getRating() > 5) {
                    throw new IllegalArgumentException("Rating must be between 0 and 5");
                }
                destination.setRating(destinationDTO.getRating());
            }

            return convertToDTO(destinationRepository.save(destination));
        } catch (Exception e) {
            throw new RuntimeException("Failed to update destination: " + e.getMessage());
        }
    }

    @Transactional
    public DestinationDTO createDestination(DestinationDTO destinationDTO) {
        System.out.println("Creating destination: " + destinationDTO);
        
        // Validate required fields
        if (destinationDTO.getName() == null || destinationDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Destination name is required");
        }
        if (destinationDTO.getLocation() == null || destinationDTO.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location is required");
        }
        
        Destination destination = Destination.builder()
                .name(destinationDTO.getName())
                .description(destinationDTO.getDescription())
                .imageUrl(destinationDTO.getImageUrl())
                .location(destinationDTO.getLocation())
                .rating(destinationDTO.getRating() != null ? destinationDTO.getRating() : 0.0)
                .reviewCount(1) // Since we're setting an initial rating
                .build();
        
        return convertToDTO(destinationRepository.save(destination));
    }

    private DestinationDTO convertToDTO(Destination destination) {
        return DestinationDTO.builder()
                .id(destination.getId())
                .name(destination.getName())
                .description(destination.getDescription())
                .imageUrl(destination.getImageUrl())
                .rating(destination.getRating())
                .reviewCount(destination.getReviewCount())
                .location(destination.getLocation())
                .build();
    }
}
