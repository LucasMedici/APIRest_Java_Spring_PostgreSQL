package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRequestDTO;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import com.example.springboot.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {
        ProductModel savedProduct = productService.saveProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }


    @GetMapping
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> list_products = productRepository.findAll();

        if(!list_products.isEmpty()){
            for(ProductModel product : list_products){
                UUID id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(list_products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> product = productRepository.findById(id);
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        product.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.FOUND).body(product.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProductRequestDTO productRequestDTO){
        Optional<ProductModel> product = productRepository.findById(id);

        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRequestDTO, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> product = productRepository.findById(id);

        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
        }

        productRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }



}
