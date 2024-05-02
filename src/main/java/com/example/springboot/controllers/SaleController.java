//package com.example.springboot.controllers;
//
//import com.example.springboot.dtos.SaleRequestDTO;
//import com.example.springboot.models.SaleModel;
//import com.example.springboot.services.SaleService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/sale")
//public class SaleController {
//
//    @Autowired
//    private SaleService saleService;
//
//    @PostMapping
//    public ResponseEntity<String> saveSale(@RequestBody @Valid SaleRequestDTO saleRequestDTO){
//        SaleModel saleModel = saleService.saveSale(saleRequestDTO);
//        return ResponseEntity.status(HttpStatus.OK).body("Sale has been registred");
//    }
//}
