package com.example.springboot.services;

import com.example.springboot.dtos.ProductRequestDTO;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductModel saveProduct(ProductRequestDTO productRequestDTO) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRequestDTO, productModel);
        return productRepository.save(productModel);
    }

}
