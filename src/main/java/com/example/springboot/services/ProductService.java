package com.example.springboot.services;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.dtos.ProductRequestDTO;
import com.example.springboot.exceptions.products.ProductExceptions;
import com.example.springboot.exceptions.products.ProductNotFoundException;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public ProductModel saveProduct(ProductRequestDTO productRequestDTO) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRequestDTO, productModel);
        return productRepository.save(productModel);
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> list_products = productRepository.findAll();
        if(!list_products.isEmpty()){
            for(ProductModel product : list_products){
                UUID id = product.getIdProduct();
                Link selfLink = linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel();
                product.add(selfLink);
            }
        }
        return list_products;
    }

    public ResponseEntity<Object> getOneProduct(UUID id) {
        ProductModel product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        Link productLink = linkTo(methodOn(ProductController.class).getAllProducts()).withRel("AllProducts");
        product.add(productLink);
        return ResponseEntity.status(HttpStatus.FOUND).body(product);
    }


    public ResponseEntity<Object> updateProduct(UUID id, ProductRequestDTO data) {
        ProductModel product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        BeanUtils.copyProperties(data, product);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));
    }

    public ResponseEntity<Object> deleteProduct(UUID id) {
        ProductModel product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }

}
