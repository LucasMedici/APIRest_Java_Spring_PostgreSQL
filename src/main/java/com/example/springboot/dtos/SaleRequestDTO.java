package com.example.springboot.dtos;

import com.example.springboot.models.UserModel;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record SaleRequestDTO(@NotNull BigDecimal order_value,@NotNull UUID id_user, List<SaleRequestProductsObjectDTO> products) {
}
