package com.example.tripadvisor.service;

import com.example.tripadvisor.dto.DestinationDTO;
import com.example.tripadvisor.model.Destination;
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
    public DestinationDTO createDestination(DestinationDTO destinationDTO) {
        Destination destination = Destination.builder()
                .name(destinationDTO.getName())
                .description(destinationDTO.getDescription())
                .imageUrl(destinationDTO.getImageUrl())
                .location(destinationDTO.getLocation())
                .rating(0.0)
                .reviewCount(0)
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
