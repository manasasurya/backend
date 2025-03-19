package com.example.tripadvisor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destinations")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private String imageUrl;

    private Double rating;

    @Column(name = "review_count")
    private Integer reviewCount;

    private String location;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
