package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
