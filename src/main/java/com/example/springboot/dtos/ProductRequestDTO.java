package com.example.springboot.dtos;

import com.example.springboot.models.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank String name, @NotNull BigDecimal value) {
}
