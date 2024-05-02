//package com.example.springboot.services;
//
//import com.example.springboot.dtos.ProductsInSaleDTO;
//import com.example.springboot.dtos.SaleRequestDTO;
//import com.example.springboot.dtos.SaleRequestProductsObjectDTO;
//import com.example.springboot.exceptions.products.ProductNotFoundException;
//import com.example.springboot.exceptions.users.UserNotFoundException;
//import com.example.springboot.models.ProductModel;
//import com.example.springboot.models.SaleModel;
//import com.example.springboot.models.UserModel;
//import com.example.springboot.repositories.ProductRepository;
//import com.example.springboot.repositories.ProductsInSaleRepository;
//import com.example.springboot.repositories.SaleRepository;
//import com.example.springboot.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class SaleService {
//
//    @Autowired
//    private SaleRepository saleRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ProductsInSaleService productsInSaleService;
//
//
//    public SaleModel saveSale(SaleRequestDTO saleRequestDTO) {
//
//        var sale = new SaleModel();
//        sale.setOrder_value(saleRequestDTO.order_value());
//        sale.setOrder_date(new Date());
//
//        UserModel user = userRepository.findById(saleRequestDTO.id_user()).orElseThrow(UserNotFoundException::new);
//        sale.setUser(user);
//
//        List<SaleRequestProductsObjectDTO> saleRequestProductsObjectDTOS = saleRequestDTO.products();
//        List<ProductModel> products = new ArrayList<>();
//
//        for(SaleRequestProductsObjectDTO productModel: saleRequestProductsObjectDTOS) {
//            var dto = new ProductsInSaleDTO(productModel.id_product(), productModel.id_product(), productModel.quantity());
//            productsInSaleService.registerProductsInSale(dto);
//        }
//
//        return saleRepository.save(sale);
//    }
//
//}