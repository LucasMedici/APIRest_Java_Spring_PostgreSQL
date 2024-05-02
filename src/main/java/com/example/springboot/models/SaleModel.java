//package com.example.springboot.models;
//
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.math.BigDecimal;
//import java.util.*;
//
//@Entity
//@Table(name = "TB_SALE")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(of = "id_sale")
//
//public class SaleModel {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id_sale;
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date order_date = new Date();
//    private BigDecimal order_value;
//
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private UserModel user;
//
//    @ManyToMany
//    @JoinTable(name = "productsInSales",
//                joinColumns = @JoinColumn(name = "sale_id"),
//                inverseJoinColumns = @JoinColumn(name = "product_id"))
//    List<ProductModel> products;
//
//
//}
