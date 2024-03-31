package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(String email,String password) {
}
