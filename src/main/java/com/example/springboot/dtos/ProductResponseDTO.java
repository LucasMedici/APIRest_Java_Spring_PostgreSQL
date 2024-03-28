package com.example.springboot.dtos;

import com.example.springboot.models.ProductModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;


public record ProductResponseDTO(UUID idProduct, @NotBlank String name, @NotNull BigDecimal value) {

    public ProductResponseDTO(ProductModel product){
        this(product.getIdProduct(), product.getName(), product.getValue());
    }
}
