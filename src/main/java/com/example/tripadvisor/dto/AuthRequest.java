package com.example.tripadvisor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@lombok.Getter
@lombok.Setter
public class AuthRequest {
    private String email;
    private String password;
}
