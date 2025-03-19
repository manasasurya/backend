package com.example.tripadvisor.repository;

import com.example.tripadvisor.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    List<Destination> findByNameContainingIgnoreCase(String name);
    List<Destination> findByLocationContainingIgnoreCase(String location);
    List<Destination> findTop10ByOrderByRatingDesc();
}
