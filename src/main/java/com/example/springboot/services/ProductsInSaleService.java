//package com.example.springboot.services;
//
//
//import com.example.springboot.dtos.ProductsInSaleDTO;
//import com.example.springboot.models.ProductsInSaleModel;
//import com.example.springboot.repositories.ProductsInSaleRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProductsInSaleService {
//
//    @Autowired
//    private ProductsInSaleRepository productsInSaleRepository;
//
//    public ProductsInSaleModel registerProductsInSale(ProductsInSaleDTO productsInSaleDTO) {
//        var obj = new ProductsInSaleModel();
//        BeanUtils.copyProperties(productsInSaleDTO, obj);
//        System.out.println(obj);
//        return obj;
//    }
//}
