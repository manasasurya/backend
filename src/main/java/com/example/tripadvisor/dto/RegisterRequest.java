package com.example.tripadvisor.dto;

import com.example.tripadvisor.model.Role;
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
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}
