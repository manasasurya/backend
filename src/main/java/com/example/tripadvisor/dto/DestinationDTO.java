package com.example.tripadvisor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private Double rating;
    private Integer reviewCount;
    private String location;
}
