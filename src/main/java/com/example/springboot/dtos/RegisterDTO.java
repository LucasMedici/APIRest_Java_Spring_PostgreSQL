package com.example.springboot.dtos;

import com.example.springboot.infra.security.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(String name,String email,String password,UserRole role) {
}
