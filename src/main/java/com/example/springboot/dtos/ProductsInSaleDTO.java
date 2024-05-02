package com.example.springboot.dtos;

import java.util.UUID;

public record ProductsInSaleDTO (UUID sale_id, UUID product_id, Integer quantity_products){
}
